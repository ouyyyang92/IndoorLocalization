package com.example.myapplication.ui.main.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ui.start.LoginActivity;

public class MyPageActivity extends AppCompatActivity {
    private Button btn_back;
    private TextView tv_username;
    private TextView tv_phone;
    private TextView tv_birth;
    private TextView tv_email;
    private TextView tv_hobbies;
    private TextView tv_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        FindView();
        SetListeners();
        ChangeInfo();
    }

    private void FindView() {
        btn_back = findViewById(R.id.button_back);
        tv_username = findViewById(R.id.tv_username);
        tv_phone = findViewById(R.id.tv_phone);
        tv_birth = findViewById(R.id.tv_birth);
        tv_email = findViewById(R.id.tv_email);
        tv_hobbies = findViewById(R.id.tv_hobbies);
        tv_location = findViewById(R.id.tv_location);
        tv_username = findViewById(R.id.tv_username);
    }

    private void SetListeners() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });
    }

    private void ChangeInfo(){
        Bundle bundle = getIntent().getExtras();
        tv_username.setText(bundle.getString("username"));
        tv_phone.setText(bundle.getString("phone"));
        tv_birth.setText(bundle.getString("birth"));
        tv_email.setText(bundle.getString("email"));
        tv_hobbies.setText(bundle.getString("hobbies"));
        tv_location.setText(bundle.getString("address"));
    }
}