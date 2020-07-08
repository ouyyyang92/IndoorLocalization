package com.example.myapplication.ui.start.leading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.myapplication.R;

public class BasicInfoActivity extends AppCompatActivity {
    private static final String TAG = "LeadingActivity1";
    private Button btn_next;
    private Button btn_icon;
    private RadioGroup rg_gender_selection;
    private EditText ed_birth_year;
    private EditText ed_birth_month;
    private EditText ed_birth_day;
    private TextView tv_welcome;
    private View lose_focus;

    private int birth_year;
    private int birth_month;
    private int birth_day;
    private int gender;
    private String location = "还不知道怎么写";
    private String username;
    private String phone;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_info);
        birth_year = 2000;
        birth_month = 1;
        birth_day = 1;
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        username = bundle.getString("username");
        FindView();
        SetListeners();

    }

    private void FindView() {
        btn_next = findViewById(R.id.button_next);
        btn_icon = findViewById(R.id.button_head_icon);
        rg_gender_selection = findViewById(R.id.radioGroup_gender_select);
        ed_birth_year = findViewById(R.id.editText_year);
        ed_birth_month = findViewById(R.id.editText_month);
        ed_birth_day = findViewById(R.id.editText_day);
        tv_welcome = findViewById(R.id.textView_welcome);
        tv_welcome.setText("欢迎 " + username + "!");
    }

    private void SetListeners() {
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearFocusOfEditText();
                Intent intent = new Intent(BasicInfoActivity.this, HobbiesSelectionActivity.class);
                //放置数据
                intent.putExtras(GetInformationBundle());
                startActivity(intent);
            }
        });
        btn_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearFocusOfEditText();
            }
        });
        rg_gender_selection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                ClearFocusOfEditText();
                RadioButton rb = findViewById(i);
                switch (i % 3) {
                    case 0:
                        gender = 0;
                        break;
                    case 1:
                        gender = 1;
                        break;
                    case 2:
                        gender = 2;
                        break;
                }
            }
        });
        ed_birth_year.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    if (TextUtils.isEmpty(ed_birth_year.getText()))
                        ed_birth_year.setText(R.string.min_year);
                    birth_year = Integer.parseInt(ed_birth_year.getText().toString());
                    AdjustYear();
                }
            }
        });
        ed_birth_month.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    if (TextUtils.isEmpty(ed_birth_month.getText()))
                        ed_birth_month.setText(R.string.min_month);
                    birth_month = Integer.parseInt(ed_birth_month.getText().toString());
                    AdjustMonth();
                }
            }
        });
        ed_birth_day.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    if (TextUtils.isEmpty(ed_birth_day.getText()))
                        ed_birth_day.setText(R.string.min_day);
                    birth_day = Integer.parseInt(ed_birth_day.getText().toString());
                    AdjustDay();
                }
            }
        });
        /*
        OnFocusChangeListener onFocusChangeListener = new OnFocusChangeListener();
        ed_birth_year.setOnFocusChangeListener(onFocusChangeListener);
        ed_birth_month.setOnFocusChangeListener(onFocusChangeListener);
        ed_birth_day.setOnFocusChangeListener(onFocusChangeListener);
        */

    }

    private Bundle GetInformationBundle(){
        Bundle bundle = getIntent().getExtras();

        assert bundle != null;
        username = bundle.getString("username");
        bundle.putInt("icon", 0);
        bundle.putInt("gender", gender);
        bundle.putString("birth", Birth_toString());
        bundle.putString("address",location);
        bundle.putString("email","11");
        return bundle;
    }

    private void AdjustYear() {
        if (birth_year < 1900) ed_birth_year.setText(R.string.min_year);
        else if (birth_year > 2020) ed_birth_year.setText(R.string.max_year);
        birth_year = Integer.parseInt(ed_birth_year.getText().toString());
        AdjustMonth();
    }

    private void AdjustMonth() {
        if (birth_year == 2020 && birth_month > 7) {
            ed_birth_month.setText(R.string.current_month);
            birth_month = Integer.parseInt(ed_birth_month.getText().toString());
            return;
        }
        if (birth_month < 0) ed_birth_month.setText(R.string.min_month);
        else if (birth_month > 12) ed_birth_month.setText(R.string.max_month);
        birth_month = Integer.parseInt(ed_birth_month.getText().toString());
        AdjustDay();
    }

    private void AdjustDay() {
        if (birth_day < 1) ed_birth_day.setText(R.string.min_day);
        if (birth_year == 2020 && birth_month == 7 && birth_day >= 15) {
            ed_birth_day.setText(R.string.current_day);
            birth_day = Integer.parseInt(ed_birth_day.getText().toString());
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
                if (birth_day > 31) ed_birth_day.setText(R.string.max_day1);
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (birth_day > 30) ed_birth_day.setText(R.string.max_day2);
                break;
            case 2:
                boolean leap = false;
                if ((birth_year % 4 == 0) && (birth_year % 100 != 0 || birth_year % 400 == 0))
                    leap = true;
                if (!leap) {
                    if (birth_day > 28) ed_birth_day.setText(R.string.max_day4);
                } else if (birth_day > 29) ed_birth_day.setText(R.string.max_day3);
                break;
        }
        birth_day = Integer.parseInt(ed_birth_day.getText().toString());
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

    private void ClearFocusOfEditText(){
        ed_birth_day.clearFocus();
        ed_birth_month.clearFocus();
        ed_birth_year.clearFocus();
    }
}