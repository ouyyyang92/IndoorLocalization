package com.example.myapplication.ui.main.home;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    private ImageView iv_icon;
    private ImageView iv_gender;
    private Drawable[] image = new Drawable[6];
    private Drawable[] genderImage = new Drawable[2];
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void ChangeInfo(){
        Bundle bundle = getIntent().getExtras();
        LoadImage();
        tv_username.setText(bundle.getString("username"));
        tv_phone.setText(bundle.getString("phone"));
        tv_birth.setText(bundle.getString("birth"));
        tv_email.setText(bundle.getString("email"));
        tv_hobbies.setText(bundle.getString("hobbies"));
        tv_location.setText(bundle.getString("address"));
        iv_icon.setImageDrawable(image[bundle.getInt("icon")]);
        if(bundle.getInt("gender") != 2 ){
            iv_gender.setImageDrawable(genderImage[bundle.getInt("gender")]);
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
}