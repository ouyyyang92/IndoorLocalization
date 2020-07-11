package com.example.myapplication.ui.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cilent.Client;
import com.example.data.MyData;
import com.example.myapplication.ui.main.MainActivity;
import com.example.myapplication.ui.start.leading.BasicInfoActivity;
import com.example.myapplication.R;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class SignInActivity extends AppCompatActivity {
    private Button btn_send_code;
    private Button btn_sign_in;
    private Button btn_back;    //按钮
    private EditText ed_phone;
    private EditText ed_username;
    private EditText ed_password;
    private EditText ed_password2;
    private EditText ed_code;   //输入框
    private ImageView[] iv_right = new ImageView[4];
    private ImageView[] iv_error = new ImageView[4];//对错图标
    private boolean flag = true;
    private EventHandler eventHandler;
    private boolean flag2 = true;

    private String phone = "";
    private String username = "";
    private String password = "";
    private String password2 = "1";
    private String code = "";           //输入框的内容
    private boolean[] correct = new boolean[4]; //每栏信息是否正确

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        FindView();
        SetListeners();
        for (int i = 0; i < 4; i++) correct[i] = false;
        eventHandler = new EventHandler(){
            public void afterEvent(int event,int result,Object data){
                Message msg = new Message();
                msg.arg1=event;
                msg.arg2=result;
                msg.obj=data;
                handler.sendMessage(msg);
            }
        };

        SMSSDK.registerEventHandler(eventHandler);
    }

    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    //寻找对应控件
    private void FindView() {
        btn_send_code = findViewById(R.id.button_send_code);
        btn_sign_in = findViewById(R.id.button_sign_in);
        btn_back = findViewById(R.id.button__previous);
        ed_phone = findViewById(R.id.editText_phone);
        ed_username = findViewById(R.id.editText_username);
        ed_password = findViewById(R.id.editText_password);
        ed_password2 = findViewById(R.id.editText_password2);
        ed_code = findViewById(R.id.editText_code);
        iv_right[0] = findViewById(R.id.imageView_right_1);
        iv_right[1] = findViewById(R.id.imageView_right_2);
        iv_right[2] = findViewById(R.id.imageView_right_3);
        iv_right[3] = findViewById(R.id.imageView_right_4);
        iv_error[0] = findViewById(R.id.imageView_error_1);
        iv_error[1] = findViewById(R.id.imageView_error_2);
        iv_error[2] = findViewById(R.id.imageView_error_3);
        iv_error[3] = findViewById(R.id.imageView_error_4);

    }

    private void SetListeners() {
        btn_send_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearFocus();
                if (CheckRegistrable()){
                    if (judPhone())//去掉左右空格获取字符串
                    {
                        SMSSDK.getVerificationCode("86", phone);
                        ed_code.requestFocus();
                    }
                    ShowInfoByToast("验证码已发送！");
                }

            }
        });

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearFocus();
                if(CheckRegistrable()) {
                    SignIn();
                }
                else {
                    ShowInfoByToast("注册失败");
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                //startActivity(intent);
                finish();//关闭当前页面
            }
        });

        ed_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    phone = ed_phone.getText().toString();
                    ChangeIcon(0, CheckPhone());
                }
            }
        });
        ed_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    username = ed_username.getText().toString();
                    ChangeIcon(1, CheckUserName());

                }
            }
        });
        ed_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    password = ed_password.getText().toString();
                    ChangeIcon(2, CheckPassword());
                    if (iv_right[3].getVisibility() != iv_error[3].getVisibility())
                        ChangeIcon(3, CheckSecondPassword());
                }
            }
        });
        ed_password2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    password2 = ed_password2.getText().toString();
                    ChangeIcon(3, CheckSecondPassword());
                    ChangeIcon(2, CheckPassword());
                }
            }
        });
    }

    private void SignIn() {
        if(judCord()){
            SMSSDK.submitVerificationCode("86",phone,code);
            String string = "2 "+ phone + " " + password + " " + username;
            String string1 = Client.send(string);
            String[] strings = string1.split("/");
            Log.d("错误信息",strings[0]);
            if (strings[0].equals("202") ){
                flag2 = false;
            }
        }
        flag=false;
    }

    private Bundle CollectData(){
        Bundle bundle = new Bundle();
        bundle.putInt("userId", 0);
        bundle.putString("username", username);
        bundle.putString("phone",phone);
        return bundle;
    }

    //检查是否可注册
    private boolean CheckRegistrable() {
        //有信息未完善
        for (int i = 0; i < 4; i++) {
            if (!correct[i]) {
                ShowInfoByToast("请完善信息");
                return false;
            }
        }
        return true;
    }

    private boolean CheckPhone() {
        if (phone.length() != 11) {
            ShowInfoByToast("请输入正确的手机号");
            correct[0] = false;
        } else {
            correct[0] = true;
        }
        return correct[0];
    }

    private boolean CheckUserName() {
        if (username.length() > 20 || username.length() < 1) {
            ShowInfoByToast("用户名长度为1-20个字符");
            correct[1] = false;
        } else {
            correct[1] = true;
        }
        return correct[1];
    }

    private boolean CheckPassword() {
        boolean hasLetter = false;
        boolean hasNumber = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) hasNumber = true;
            else if (Character.isLetter(password.charAt(i))) hasLetter = true;
            if (hasNumber && hasLetter) break;
        }
        if (!hasLetter || !hasNumber) {
            ShowInfoByToast("密码必须同时包含字母和数字");
            correct[2] = false;
        } else {
            correct[2] = true;
        }
        return correct[2];
    }

    private boolean CheckSecondPassword() {
        if (password2.equals(password) && iv_right[2].getVisibility() == View.VISIBLE) {
            correct[3] = true;
        } else {
            correct[3] = false;
            ShowInfoByToast("两次密码需一致");
        }
        return correct[3];
    }

    private void ChangeIcon(int index, boolean right) {
        if (right) {
            iv_right[index].setVisibility(View.VISIBLE);
            iv_error[index].setVisibility(View.INVISIBLE);
        } else {
            iv_right[index].setVisibility(View.INVISIBLE);
            iv_error[index].setVisibility(View.VISIBLE);
        }
    }

    private void ShowInfoByToast(String info) {
        Toast.makeText(SignInActivity.this, info, Toast.LENGTH_SHORT).show();
    }

    private void ClearFocus(){
        ed_phone.clearFocus();
        ed_username.clearFocus();
        ed_password.clearFocus();
        ed_password2.clearFocus();
        ed_code.clearFocus();
    }

    private boolean judPhone()
    {
        if(TextUtils.isEmpty(ed_phone.getText().toString().trim()))
        {
            Toast.makeText(this,"请输入您的电话号码",Toast.LENGTH_LONG).show();
            ed_phone.requestFocus();
            return false;
        }
        else if(ed_phone.getText().toString().trim().length()!=11)
        {

            Toast.makeText(this,"您的电话号码位数不正确",Toast.LENGTH_LONG).show();
            ed_phone.requestFocus();
            return false;
        }
        else
        {
            phone=ed_phone.getText().toString().trim();
            String num="[1][345678]\\d{9}";
            if(phone.matches(num))
                return true;
            else
            {
                Toast.makeText(this,"请输入正确的手机号码",Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }

    private boolean judCord()
    {
        judPhone();
        if(TextUtils.isEmpty(ed_code.getText().toString().trim()))
        {
            Toast.makeText(this,"请输入您的验证码",Toast.LENGTH_LONG).show();
            ed_code.requestFocus();
            return false;
        }
        else if(ed_code.getText().toString().trim().length()!=6)
        {
            Toast.makeText(this,"您的验证码位数不正确",Toast.LENGTH_LONG).show();
            ed_code.requestFocus();

            return false;
        }
        else
        {
            code=ed_code.getText().toString().trim();
            return true;
        }

    }

    Handler handler  = new Handler()
    {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event=msg.arg1;
            int result=msg.arg2;
            String string = "ss";
            if (result==SMSSDK.RESULT_COMPLETE){
                string = "qqq";
            }
            Log.d("错误信息",string);
            if(result==SMSSDK.RESULT_COMPLETE)
            {
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    if (flag2){
                        ShowInfoByToast("注册成功！");
                        MyData.setDengluzhuangtai(1);
                        Intent intent = new Intent(SignInActivity.this, BasicInfoActivity.class);
                        //创建新Activity的同时清空站内所有Activities
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtras(CollectData());
                        startActivity(intent);
                    }
                    else {
                        ShowInfoByToast("用户名已注册");
                    }

                }
            }
            else
            {
                if(flag)
                {
                    btn_send_code.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"验证码获取失败请重新获取", Toast.LENGTH_LONG).show();
                    ed_phone.requestFocus();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"验证码输入错误", Toast.LENGTH_LONG).show();
                }
            }
        }
    };
}