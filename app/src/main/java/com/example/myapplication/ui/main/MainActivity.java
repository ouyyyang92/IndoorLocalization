package com.example.myapplication.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.print.PageRange;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.data.Person;
import com.example.myapplication.R;
import com.example.myapplication.ui.main.contacts.ContactsFragment;
import com.example.myapplication.ui.main.home.HomeFragment;
import com.example.myapplication.ui.main.map.MapFragment;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Button btn_map, btn_contacts, btn_home;
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