package com.example.myapplication.ui.main.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.cilent.Client;
import com.example.data.Person;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class RequestActivity extends AppCompatActivity {
    private Button btn_back;
    private ListView lv_request;
    private String friendrequest;
    private List<Person> personList;
    private Button[] btn_accept;
    private Button[] btn_reject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personList = new ArrayList<Person>();
        setContentView(R.layout.activity_request);
        SplitInformation();
        FindView();
        lv_request.setAdapter(new RequestAdapter(this, personList, getIntent().getStringExtra("username")));
        SetListeners();
        FindItemButton();
    }

    private void FindView() {
        btn_back = findViewById(R.id.button_back);
        lv_request = findViewById(R.id.listView_request);
    }

    private void SetListeners() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void SplitInformation() {
        friendrequest = getIntent().getStringExtra("friendrequest");
        String[] strings = friendrequest.split("/");
        if (strings.length > 1) {
            for (int i = 1; i < strings.length; i++) {
                String strings1 = strings[i].substring(1, strings[i].length() - 1);
                String[] strings2 = strings1.split(" ");
                Person person = new Person(strings2[0], Integer.parseInt(strings2[1]));
                personList.add(person);
            }
        }
    }

    private void FindItemButton() {
        int itemAmount = lv_request.getAdapter().getCount();
        btn_accept = new Button[itemAmount];
        btn_reject = new Button[itemAmount];
        for (int i = 0; i < itemAmount; i++) {
            btn_accept[i] = lv_request.getChildAt(i).findViewById(R.id.button_accept);
            btn_reject[i] = lv_request.getChildAt(i).findViewById(R.id.button_reject);
            btn_accept[i].setOnClickListener(new OnClickedListener(9, i));
            btn_accept[i].setOnClickListener(new OnClickedListener(10, i));
        }
    }

    private class OnClickedListener implements View.OnClickListener {
        private int code;
        private int index;

        public OnClickedListener(int code, int index) {
            this.code = code;
            this.index = index;
        }

        @Override
        public void onClick(View view) {
            String str1 = code + " " + personList.get(index).getName() + " " + getIntent().getStringExtra("username");
            Client.send(str1);
        }
    }
}