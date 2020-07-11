package com.example.myapplication.ui.main.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personList = new ArrayList<Person>();
        setContentView(R.layout.activity_request);
        SplitInformation();
        FindView();
        lv_request.setAdapter(new RequestAdapter(this,personList,getIntent().getStringExtra("username")));
        SetListeners();
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
    private void SplitInformation(){
        friendrequest = getIntent().getStringExtra("friendrequest");
        String[] strings = friendrequest.split("/");
        if (strings.length>1){
            for (int i =1;i<strings.length;i++){
                String strings1 = strings[i].substring(1,strings[i].length()-1);
                String[] strings2 = strings1.split(" ");
                Person person = new Person(strings2[0], Integer.parseInt(strings2[1]));
                personList.add(person);
            }
        }
    }
}