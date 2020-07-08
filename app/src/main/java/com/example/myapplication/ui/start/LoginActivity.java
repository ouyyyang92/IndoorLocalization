package com.example.myapplication.ui.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cilent.Client;
import com.example.myapplication.R;
import com.example.myapplication.ui.main.MainActivity;

import org.json.JSONObject;

import java.io.IOException;


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

    private String personinformation = "";
    private String phone = "";
    private String password = "";
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
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
    private boolean Login() throws IOException, ClassNotFoundException {
        //获取手机号、密码文本
        phone = ed_phone.getText().toString();
        password = ed_password.getText().toString();

        Client client = new Client();
        String str = "1 " + phone + " " + password;
        String str1 = client.send(str);
        String[] strings = str1.split(",");
        String message = "";
        if (strings[0].equals("100")) {
            message = "登录成功";
            ShowInfoByToast(message);
            personinformation = strings[1];
            System.out.println(personinformation);
//            GetInformationBundle(personString);
            return true;
        } else if (strings[0].equals("101")) {
            message = "用户名不存在";
        } else if (strings[0].equals("102")) {
            message = "密码错误";
        }
        ShowInfoByToast(message);
        return false;
//        if(client.send(str).equals("1"))
//            return true;
//        else return false;
    }

    private void ShowInfoByToast(String info) {
        Toast.makeText(LoginActivity.this, info, Toast.LENGTH_SHORT).show();
    }

    private void toAnotherActivity(String destination) {
        Intent intent = new Intent();
        intent.setClassName(LoginActivity.this, destination);
        startActivity(intent);
    }

    private Bundle GetInformationBundle(String info) {
        //将字符串拆分并按键-值形式放入bundle
        String[] infos = info.split(" ");
        Bundle bundle = new Bundle();
        for (int i = 0;i<8;i++){
            System.out.println(infos[i]);
        }
        bundle.putString("phone", infos[0]);
        bundle.putString("username", infos[1]);
        bundle.putString("address", infos[2]);
        bundle.putString("email", infos[3]);
        bundle.putInt("gender", Integer.parseInt(infos[4]));
        bundle.putString("birth", infos[5]);
        bundle.putInt("age",Integer.parseInt(infos[6]));
        bundle.putString("hobbies", infos[7]);
        bundle.putInt("icon", Integer.parseInt(infos[8]));

        return bundle;
    }

    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_login:
                    //跳转到主页
                    try {
                        if (Login()) {
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                            Intent intent1 = new Intent();
                            String information = "";    //服务器发送来的消息
                            intent1.putExtras(GetInformationBundle(personinformation));
                            intent1.setClass(LoginActivity.this, MainActivity.class);
                            startActivity(intent1);
                            finish();   //结束掉该页面
                        } else {
                            ed_password.setText("");
//                          Toast.makeText(LoginActivity.this, "手机号或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.button_signIn:
                    //跳转注册界面
                    toAnotherActivity("com.example.myapplication.ui.start.SignInActivity");
                    break;
                case R.id.button_view:
                    //查看/隐藏密码
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
                    //清空手机号
                    ed_phone.setText("");
                    break;
                case R.id.button_findPassword:
                    toAnotherActivity("com.example.myapplication.ui.start.findPassword.FindPasswordActivity1");
                    break;
            }
        }
    }

}