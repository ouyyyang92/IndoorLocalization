package com.example.myapplication.ui.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.ui.main.MainActivity;


public class LoginActivity extends AppCompatActivity {
    private Button btn_login;
    private Button btn_findPassword;
    private Button btn_signIn;
    private Button btn_view;
    private Button btn_clean;   //按钮
    private EditText ed_phone;
    private EditText ed_password; //输入框
    private ImageView iv_eye_closed;
    private ImageView iv_eye_open;  //图片

    private String phone = "";
    private String password = "";
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FindView();
        SetListeners();
    }

    private void FindView() {
        btn_login = findViewById(R.id.button_login);
        btn_findPassword = findViewById(R.id.button_findPassword);
        btn_signIn = findViewById(R.id.button_signIn);
        btn_view = findViewById(R.id.button_view);
        btn_clean = findViewById(R.id.button_clean);
        ed_phone = findViewById(R.id.editText_phone);
        ed_password = findViewById(R.id.editText_password);
        iv_eye_closed = findViewById(R.id.imageView_eye_closed);
        iv_eye_open = findViewById(R.id.imageView_eye_open);

        btn_findPassword.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        btn_signIn.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    private void SetListeners() {
        OnClickListener onClickListener = new OnClickListener();
        btn_login.setOnClickListener(onClickListener);
        btn_findPassword.setOnClickListener(onClickListener);
        btn_clean.setOnClickListener(onClickListener);
        btn_view.setOnClickListener(onClickListener);
        btn_signIn.setOnClickListener(onClickListener);
    }

    private boolean Login() {
        phone = ed_phone.getText().toString();
        password = ed_password.getText().toString();
        if (phone.equals("123456")) {
            if (password.equals("123456")) {
                return true;
            }
        }
        return true;
    }

    private void toAnotherActivity(String destination) {
        Intent intent = new Intent();
        intent.setClassName(LoginActivity.this, destination);
        startActivity(intent);
    }

    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_login:
                    //跳转到主页
                    if (Login()) {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                        Intent intent1 = new Intent();
                        intent1.putExtra("userId", userId);
                        intent1.setClass(LoginActivity.this, MainActivity.class);
                        startActivity(intent1);
                        finish();
                    } else {
                        ed_password.setText("");
                        Toast.makeText(LoginActivity.this, "手机号或密码错误", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.button_signIn:
                    //跳转注册界面
                    toAnotherActivity("com.example.myapplication.ui.start.SignInActivity");
                    break;
                case R.id.button_view:
                    if (iv_eye_closed.getVisibility() == View.VISIBLE) {//当密码不可见时
                        iv_eye_closed.setVisibility(View.INVISIBLE);
                        iv_eye_open.setVisibility(View.VISIBLE);    //变换图片
                        ed_password.setInputType(0x90);       //输入类型可见
                    } else {
                        iv_eye_closed.setVisibility(View.VISIBLE);
                        iv_eye_open.setVisibility(View.INVISIBLE);
                        ed_password.setInputType(0x81);
                    }
                    break;
                case R.id.button_clean:
                    ed_phone.setText("");
                    break;
                case R.id.button_findPassword:
                    toAnotherActivity("com.example.myapplication.ui.start.findPassword.FindPasswordActivity1");
                    break;
            }
        }
    }
}