package com.example.myapplication.ui.main.contacts;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cilent.Client;
import com.example.data.MyData;
import com.example.myapplication.R;

public class FriendPageActivity extends AppCompatActivity {
    private Button btn_back;
    private Button btn_add_friends;
    private Button btn_chat;
    private TextView tv_username;
    private TextView tv_phone;
    private TextView tv_birth;
    private TextView tv_email;
    private TextView tv_hobbies;
    private TextView tv_location;
    private ImageView iv_icon;
    private ImageView iv_gender;
    private Drawable[] image = new Drawable[6];
    private Drawable[] genderImage = new Drawable[2];
    private String myname ;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_page);
        FindView();
        LoadInfo();
        SetListeners();
        SwitchMode();
    }

    private void FindView() {
        btn_back = findViewById(R.id.button_back);
        btn_add_friends = findViewById(R.id.button_add_friend);
        btn_chat = findViewById(R.id.button_chat);
        tv_username = findViewById(R.id.tv_username);
        tv_phone = findViewById(R.id.tv_phone);
        tv_birth = findViewById(R.id.tv_birth);
        tv_email = findViewById(R.id.tv_email);
        tv_hobbies = findViewById(R.id.tv_hobbies);
        tv_location = findViewById(R.id.tv_location);
        tv_username = findViewById(R.id.tv_username);
        iv_icon = findViewById(R.id.head_image);
        iv_gender = findViewById(R.id.imageView_gender);
    }

    private void SetListeners() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_add_friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAsFriend();
                //

            }
        });
        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = tv_username.getText().toString();
                String str1 = "16 " + username;
                String str2 = Client.send(str1);
                String[] strings = str2.split("/");
                MyData.setId(Integer.parseInt(strings[1]));
//                Toast.makeText(FriendPageActivity.this,"敬请期待",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void LoadInfo(){
        Bundle bundle = getIntent().getExtras();
        LoadImage();
        myname = bundle.getString("myname");
        Log.d("错误信息333",bundle.getString("username"));
        tv_username.setText(bundle.getString("username"));
        tv_phone.setText(bundle.getString("phone"));
        tv_birth.setText(bundle.getString("birth"));
        tv_email.setText(bundle.getString("email"));
        tv_hobbies.setText(bundle.getString("hobbies"));
        tv_location.setText(bundle.getString("address"));
        iv_icon.setImageDrawable(image[bundle.getInt("icon")]);
        if(bundle.getInt("gender") != 2 ){
            iv_gender.setImageDrawable(genderImage[bundle.getInt("key")]);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void LoadImage(){
        image[0] = getDrawable(R.drawable.ic_headimage_1);
        image[1] = getDrawable(R.drawable.ic_headimage_2);
        image[2] = getDrawable(R.drawable.ic_headimage_3);
        image[3] = getDrawable(R.drawable.ic_headimage_4);
        image[4] = getDrawable(R.drawable.ic_headimage_5);
        image[5] = getDrawable(R.drawable.ic_headimage_6);
        genderImage[0] = getDrawable(R.drawable.ic_male_show);
        genderImage[1] = getDrawable(R.drawable.ic_female_show);
    }

    private void SwitchMode(){
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            switch (bundle.getString("state")){
                case "add a friend":
                    btn_chat.setVisibility(View.INVISIBLE);
                    break;
                case "my friend's page":
                    btn_add_friends.setVisibility(View.INVISIBLE);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + bundle.getString("state"));
            }
        }

    }

    private void AddAsFriend(){
        String username = tv_username.getText().toString();
        String str1 = "7 " + myname +" " + username;
        String str2 = Client.send(str1);
        String[] strings = str2.split("/");
        if (strings[0].equals("400")){
            Toast.makeText(FriendPageActivity.this,"已发送请求",Toast.LENGTH_SHORT).show();
        }
        else if (strings[0].equals("401")){
            Toast.makeText(FriendPageActivity.this,"该用户已是您的好友",Toast.LENGTH_SHORT).show();
        }
        else if (strings[0].equals("402")){
            Toast.makeText(FriendPageActivity.this,"已发出过申请",Toast.LENGTH_SHORT).show();
        }
        else if (strings[0].equals("403")){
            Toast.makeText(FriendPageActivity.this,"不能向自己发送好友请求",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(FriendPageActivity.this,"未知错误",Toast.LENGTH_SHORT).show();

        }
    }

}