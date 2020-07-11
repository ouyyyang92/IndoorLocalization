package com.example.myapplication.ui.main.map;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cilent.Client;
import com.example.data.MyData;
import com.example.myapplication.R;

public class IntroduceActivity extends AppCompatActivity {
    private Button btn_back2,btn_navigation;
    private TextView place_name,tv_introduce1,tv_judge1;
    private ImageView iv_place_picture;
    private Drawable[] picture = new Drawable[9];
    private int bianhao ;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        Bundle bundle = getIntent().getExtras();
        FindView();
        getPicture();
        place_name.setText(bundle.getString("name"));
        SetListeners();
        tv_introduce1.setText(bundle.getString("introduce"));
        tv_judge1.setText(bundle.getString("pingjia"));
        iv_place_picture.setImageDrawable(picture[bundle.getInt("num")]);
    }

   private void FindView(){
        btn_back2=findViewById(R.id.button_back2);
        tv_introduce1=findViewById(R.id.tv_introduce1);
        tv_judge1=findViewById(R.id.tv_judge1);
        place_name=findViewById(R.id.place_name);
        btn_navigation=findViewById(R.id.btn_navigation);
        iv_place_picture=findViewById(R.id.iv_place_picture);

    }

    private void SetListeners(){
        btn_back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String str1 = "15 "+ place_name.getText().toString();
                Log.d("错误11",str1);
               String str2 = Client.send(str1);
               Log.d("错误22",str2);
               String[] strings = str2.split("/");
               bianhao = Integer.parseInt(strings[1]);
                MyData.setBiaohao(bianhao);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getPicture(){
        picture[1]=getDrawable(R.drawable.home_picture_1);
        picture[2]=getDrawable(R.drawable.home_picture_2);
        picture[3]=getDrawable(R.drawable.home_picture_3);
        picture[4]=getDrawable(R.drawable.home_picture_4);
        picture[5]=getDrawable(R.drawable.home_picture_5);
        picture[6]=getDrawable(R.drawable.home_picture_6);
        picture[7]=getDrawable(R.drawable.home_picture_7);
        picture[8]=getDrawable(R.drawable.home_picture_8);
    }


}