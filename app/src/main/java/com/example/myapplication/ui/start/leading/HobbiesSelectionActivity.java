package com.example.myapplication.ui.start.leading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.data.DateUtils;
import com.example.data.Person;
import com.example.myapplication.R;
import com.example.myapplication.ui.main.MainActivity;

import java.util.Arrays;
import java.util.List;

public class HobbiesSelectionActivity extends AppCompatActivity {
    private static final String TAG = "LeadingActivity2";
    private static final List<String> hobbyList = Arrays.asList("爬山", "烹饪", "健身", "游戏", "音乐", "阅读", "跑步", "游泳");

    private Button btn_pre;
    private Button btn_next;
    private CheckBox cb_hobbies_climbing, cb_hobbies_cooking, cb_hobbies_exercise,
            cb_hobbies_game, cb_hobbies_music, cb_hobbies_reading,
            cb_hobbies_running, cb_hobbies_swimming;
    private boolean[] hobbySelected = new boolean[8]; //用于记录某个爱好是否被选择

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobbies_selection);
        FindView();
        SetListeners();
        for (int i = 0; i < 8; i++) hobbySelected[i] = false;
    }

    private void FindView() {
        //找到对应控件
        btn_pre = findViewById(R.id.button__previous);
        btn_next = findViewById(R.id.button_next);
        cb_hobbies_climbing = findViewById(R.id.checkBox_1);
        cb_hobbies_cooking = findViewById(R.id.checkBox_2);
        cb_hobbies_exercise = findViewById(R.id.checkBox_3);
        cb_hobbies_game = findViewById(R.id.checkBox_4);
        cb_hobbies_music = findViewById(R.id.checkBox_5);
        cb_hobbies_reading = findViewById(R.id.checkBox_6);
        cb_hobbies_running = findViewById(R.id.checkBox_7);
        cb_hobbies_swimming = findViewById(R.id.checkBox_8);
    }

    private void SetListeners() {
        btn_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CompleteInformation();
                Intent intent = new Intent();
                intent.putExtras(CompleteInformation());
                intent.setClass(HobbiesSelectionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //为兴趣爱好按钮添加监听
        OnCheckedChange onCheckedChange = new OnCheckedChange();
        cb_hobbies_climbing.setOnCheckedChangeListener(onCheckedChange);
        cb_hobbies_cooking.setOnCheckedChangeListener(onCheckedChange);
        cb_hobbies_exercise.setOnCheckedChangeListener(onCheckedChange);
        cb_hobbies_game.setOnCheckedChangeListener(onCheckedChange);
        cb_hobbies_music.setOnCheckedChangeListener(onCheckedChange);
        cb_hobbies_reading.setOnCheckedChangeListener(onCheckedChange);
        cb_hobbies_running.setOnCheckedChangeListener(onCheckedChange);
        cb_hobbies_swimming.setOnCheckedChangeListener(onCheckedChange);
    }

    private Bundle CompleteInformation() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        Log.d("icon", bundle.getString("icon"));
        Log.d("gender", bundle.getInt("gender") + "");
        Log.d("birth", bundle.getString("birth"));
        Log.d("location", bundle.getString("address"));
        Log.d("hobbies", Hobbies_toString());
        bundle.putString("hobbies", Hobbies_toString());
        bundle.putInt("age", 0);
        return bundle;
    }

    private String Hobbies_toString() {
        StringBuilder hobbies = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            if (hobbySelected[i]) {
                hobbies.append(hobbyList.get(i)).append(',');
            }
        }
        if (hobbies.length() != 0)
            hobbies.deleteCharAt(hobbies.length() - 1);//删除最后一个逗号
        return hobbies.toString();
    }

    private class OnCheckedChange implements CheckBox.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            //控件id为2131165269-2131165276,全部-5然后%8后可得到0-7的数字
            //由此便可将按钮id和布尔数组联系起来
            int buttonId = compoundButton.getId();
            int code = (buttonId - 5) % 8;
            hobbySelected[code] = b;
        }
    }
}