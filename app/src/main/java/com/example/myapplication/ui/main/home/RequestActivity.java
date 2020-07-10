package com.example.myapplication.ui.main.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.R;

public class RequestActivity extends AppCompatActivity {
    private Button btn_back;
    private ListView lv_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        FindView();
//        lv_request.setAdapter(new RequestAdapter(this,,getIntent().getStringExtra("username");));
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
}