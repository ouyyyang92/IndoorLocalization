package com.example.myapplication.ui.main.home;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cilent.Client;
import com.example.myapplication.R;

public class ModifyActivity extends AppCompatActivity {

    private Button button_back1;
    private Button btn_define;
    private TextView et_year,et_month,et_day,et_location,et_email,et_name;
    private RadioGroup radioGroup_gender_select1;
    private Drawable[] image = new Drawable[6];
    private ImageView iv_head_icon;

    private int birth_year;
    private int birth_month;
    private int birth_day;
    private int gender;
    private int icon = 0;
    private String beforeName = "";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        Bundle bundle = getIntent().getExtras();
        FindView();
        SetListener();
        birth_year = 2000;
        birth_month = 01;
        birth_day = 01;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void FindView() {
        button_back1=findViewById(R.id.button_back1);
        btn_define=findViewById(R.id.btn_define);
        et_year=findViewById(R.id.eT_year);
        et_month=findViewById(R.id.eT_month);
        et_day=findViewById(R.id.eT_day);
        et_name=findViewById(R.id.eT_name);
        et_email=findViewById(R.id.editText_email);
        et_location=findViewById(R.id.eT_location);
        iv_head_icon=findViewById(R.id.iv_head_icon);
        image[0] = getDrawable(R.drawable.ic_headimage_1);
        image[1] = getDrawable(R.drawable.ic_headimage_2);
        image[2] = getDrawable(R.drawable.ic_headimage_3);
        image[3] = getDrawable(R.drawable.ic_headimage_4);
        image[4] = getDrawable(R.drawable.ic_headimage_5);
        image[5] = getDrawable(R.drawable.ic_headimage_6);
        iv_head_icon.setImageDrawable(image[icon]);
        radioGroup_gender_select1=findViewById(R.id.radioGroup_gender_select1);
        beforeName = getIntent().getStringExtra("username");
    }

    private void SetListener() {
        button_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_define.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearFocusOfEditText();
                Intent intent = new Intent(ModifyActivity.this, MyPageActivity.class);
                String str1 = "12 "+ beforeName+" " +et_name.getText().toString() + " " +icon +" " + gender +" " +
                        Birth_toString() + " " + et_location.getText().toString() + " " +et_email.getText().toString();
                Client.send(str1);
                intent.putExtras(GetInformationBundle());
                startActivity(intent);
            }
        });
        iv_head_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearFocusOfEditText();
                icon = (icon + 1) % 6;
                iv_head_icon.setImageDrawable(image[icon]);
                iv_head_icon.bringToFront();
            }
        });

        radioGroup_gender_select1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                ClearFocusOfEditText();
                RadioButton rb = findViewById(i);
                switch (i % 3) {
                    case 0:
                        gender = 1;
                        break;
                    case 1:
                        gender = 0;
                        break;
                    case 2:
                        gender = 2;
                        break;
                }
            }
        });

        et_year.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    if (TextUtils.isEmpty(et_year.getText()))
                        et_year.setText(R.string.min_year);
                    birth_year = Integer.parseInt(et_year.getText().toString());
                    AdjustYear();
                }
            }
        });
        et_month.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    if (TextUtils.isEmpty(et_month.getText()))
                        et_month.setText(R.string.min_month);
                    birth_month = Integer.parseInt(et_month.getText().toString());
                    AdjustMonth();
                }
            }
        });
        et_day.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    if (TextUtils.isEmpty(et_day.getText()))
                        et_day.setText(R.string.min_day);
                    birth_day = Integer.parseInt(et_day.getText().toString());
                    AdjustDay();
                }
            }
        });



    }

    private void AdjustYear() {
        if (birth_year < 1900) et_year.setText(R.string.min_year);
        else if (birth_year > 2020) et_year.setText(R.string.max_year);
        birth_year = Integer.parseInt(et_year.getText().toString());
        AdjustMonth();
    }

    private void AdjustMonth() {
        if (birth_year == 2020 && birth_month > 7) {
            et_month.setText(R.string.current_month);
            birth_month = Integer.parseInt( et_month.getText().toString());
            return;
        }
        if (birth_month < 0)  et_month.setText(R.string.min_month);
        else if (birth_month > 12)  et_month.setText(R.string.max_month);
        birth_month = Integer.parseInt( et_month.getText().toString());
        AdjustDay();
    }

    private void AdjustDay() {
        if (birth_day < 1) et_day.setText(R.string.min_day);
        if (birth_year == 2020 && birth_month == 7 && birth_day >= 15) {
            et_day.setText(R.string.current_day);
            birth_day = Integer.parseInt(et_day.getText().toString());
            return;
        }
        switch (birth_month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (birth_day > 31) et_day.setText(R.string.max_day1);
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (birth_day > 30) et_day.setText(R.string.max_day2);
                break;
            case 2:
                boolean leap = false;
                if ((birth_year % 4 == 0) && (birth_year % 100 != 0 || birth_year % 400 == 0))
                    leap = true;
                if (!leap) {
                    if (birth_day > 28) et_day.setText(R.string.max_day4);
                } else if (birth_day > 29) et_day.setText(R.string.max_day3);
                break;
        }
        birth_day = Integer.parseInt(et_day.getText().toString());
    }

    private String Birth_toString() {
        String birth;
        birth = birth_year + "-";

        if (birth_month < 10) birth += "0";
        birth += birth_month + "-";

        if (birth_day < 10) birth += "0";
        birth += String.valueOf(birth_day);

        return birth;
    }
    private Bundle GetInformationBundle(){
        Bundle bundle = getIntent().getExtras();

        assert bundle != null;
        bundle.putInt("username", Integer.parseInt(et_name.getText().toString()));
        bundle.putInt("icon", icon);
        bundle.putInt("gender", gender);
        bundle.putString("birth", Birth_toString());
        bundle.putString("address",et_location.getText().toString());
        bundle.putString("email",et_email.getText().toString());
        return bundle;
    }
    private void ClearFocusOfEditText(){

    }

}