package com.example.myapplication.ui.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.R;
//try commit
public class StartActivity extends AppCompatActivity {
    private Button btn_skip;
    private ImageView iv;
    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        iv = findViewById(R.id.imageView_app_icon);
        //Glide.with(this).load("http://t8.baidu.com/it/u=1484500186,1503043093&fm=79&app=86&f=JPEG?w=1280&h=853").into(iv);
        btn_skip = findViewById(R.id.button_skip);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginPage();
                mHandler.removeCallbacksAndMessages(null);  //关掉线程
            }
        });
        //开启一个自动计时的线程，三秒后自动跳转登录页面
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginPage();
            }
        }, 3000);

    }

    private void LoginPage() {
        Intent intent = new Intent(StartActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}