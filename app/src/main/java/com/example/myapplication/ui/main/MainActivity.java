package com.example.myapplication.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.print.PageRange;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.cilent.Client;
import com.example.data.DateUtils;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fra_map = new MapFragment();
        fra_contacts = new ContactsFragment();
        fra_home = new HomeFragment();

        FindView();

        SetListener();
        try {
            GetMyData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main, fra_map).commitAllowingStateLoss();

    }

    private void FindView() {
        btn_map = findViewById(R.id.button_map);
        btn_contacts = findViewById(R.id.button_contacts);
        btn_home = findViewById(R.id.button_me);

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
        int icon = bundle.getInt("icon");
        int age = bundle.getInt("age");
        String bornstring = bundle.getString("birth");
        String hobbies = bundle.getString("hobbies");
        String friends = "";
        Log.d("错误","sss");
        me = new Person(phone,username,address,email,gender, DateUtils.stringToUDate(bornstring),age,hobbies,icon);
        Log.d("错误","sss");

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

    public Person GetMe(){
        return this.me;
    }

}