package com.example.myapplication.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.print.PageRange;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.cilent.Client;
import com.example.data.Person;
import com.example.myapplication.R;
import com.example.myapplication.ui.main.contacts.ContactsFragment;
import com.example.myapplication.ui.main.home.HomeFragment;
import com.example.myapplication.ui.main.map.MapFragment;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btn_map, btn_contacts, btn_home,button_dingwei;
    private MapFragment fra_map;
    private ContactsFragment fra_contacts;
    private HomeFragment fra_home;
    private Person me;
    private int[] AP = new int[4];
    public int px,py;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fra_map = new MapFragment();
        fra_contacts = new ContactsFragment();
        fra_home = new HomeFragment();

        FindView();
        button_dingwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
                WifiManager wm = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wm.startScan();                                  //开始扫描AP
                int j =0;
                List<ScanResult> results = wm.getScanResults();  //拿到扫描的结果
                for (ScanResult result : results) {
                    if (result.SSID.equals("1604") && result.level > -100) {
                        AP[j] = result.level;
                        j++;
                    }

                    if (result.SSID.equals("TP-LINK_1704") && result.level > -100) {
                       AP[2] = result.level;
                    }
                    if (result.SSID.equals("1601T") && result.level > -100) {
                        AP[3] = result.level;
                    }
                }
                String str1 = "3";
                for (int i = 0;i < 4;i++){
                    str1 = str1 + " " + AP[i] ;
                }
                String str2 = Client.send(str1);
                String[] strings4 = str2.split(",");
                String[] strings5 = strings4[1].split(" ");
                px = Integer.parseInt(strings5[0])*29;
                py = (18-Integer.parseInt(strings5[1]))*29;
//                System.out.println(str2);

            }

        });
        SetListener();
        try {
            GetMyData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main, fra_map).commitAllowingStateLoss();
    }
    public void init() {                 //数据初始化
        for (int a = 0; a < 4; a++) {
            AP[a] = -200;
        }
    }
    private void FindView() {
        btn_map = findViewById(R.id.button_map);
        btn_contacts = findViewById(R.id.button_contacts);
        btn_home = findViewById(R.id.button_me);
        button_dingwei = findViewById(R.id.button_dingwei);
    }

    private void SetListener() {
        btn_map.setOnClickListener(new OnClicked(fra_map));
        btn_contacts.setOnClickListener(new OnClicked(fra_contacts));
        btn_home.setOnClickListener(new OnClicked(fra_home));

    }

    private void GetMyData() throws ParseException {
        Bundle bundle = getIntent().getExtras();
        String phone = bundle.getString("phone");
        String password = "";
        String username = bundle.getString("username");
        String address = bundle.getString("address");
        String email = bundle.getString("email");
        int gender = bundle.getInt("gender");
        String icon = bundle.getString("icon");

        SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
        Date createDate = fomat.parse("2020-07-07");
        Date birth = fomat.parse(bundle.getString("birth"));
        int age = 0;
        String hobbies = bundle.getString("hobbies");
        String friends = "";
        //实例化
        me = new Person(phone, password, username, address, email, gender, icon,
                createDate, birth, age, hobbies, friends);

    }

    private class OnClicked implements View.OnClickListener {
        private Fragment fragment;

        public OnClicked(Fragment fragment) {
            this.fragment = fragment;
        }

        @Override
        public void onClick(View view) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, fragment).commitAllowingStateLoss();
        }
    }

}