package com.example.myapplication.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.text.ParseException;
import java.util.ArrayList;

import com.example.cilent.Client;
import com.example.data.ClientUtils;
import com.example.data.DateUtils;
import com.example.data.Person;
import com.example.myapplication.R;
import com.example.myapplication.ui.main.contacts.ContactsFragment;
import com.example.myapplication.ui.main.home.HomeFragment;
import com.example.myapplication.ui.main.map.MapFragment;

import java.util.List;



public class MainActivity extends AppCompatActivity {
    private Button btn_map, btn_contacts, btn_home,button_dingwei;
    private MapFragment fra_map;
    private ContactsFragment fra_contacts;
    private HomeFragment fra_home;
    private Person me;
    private List<Person> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fra_map = new MapFragment();
        fra_contacts = new ContactsFragment();
        fra_home = new HomeFragment();

        FindView();
        try {
            GetMyData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
        btn_home.setOnClickListener(new OnClicked(fra_home));
        btn_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = "5 "+me.getName();
                String str1 = Client.send(str);
                String[] strings = str1.split("/");
                list = new ArrayList<Person>();
                if (strings.length>1){
                    for (int i = 1;i<strings.length;i++){
                        Person person = ClientUtils.stringToPerson(strings[i]);
                        list.add(person);
                    }
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, fra_contacts).commitAllowingStateLoss();
            }
        });
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
    public List<Person> GetFriend(){return this.list;}

}