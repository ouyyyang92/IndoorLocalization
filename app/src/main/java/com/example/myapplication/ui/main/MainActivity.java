package com.example.myapplication.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.print.PageRange;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.myapplication.R;
import com.example.myapplication.ui.main.contacts.ContactsFragment;
import com.example.myapplication.ui.main.home.HomeFragment;
import com.example.myapplication.ui.main.map.MapFragment;

public class MainActivity extends AppCompatActivity {
    private Button btn_map, btn_contacts, btn_home;
    private MapFragment fra_map;
    private ContactsFragment fra_contacts;
    private HomeFragment fra_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fra_map = new MapFragment();
        fra_contacts = new ContactsFragment();
        fra_home = new HomeFragment();
        FindView();
        SetListener();
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