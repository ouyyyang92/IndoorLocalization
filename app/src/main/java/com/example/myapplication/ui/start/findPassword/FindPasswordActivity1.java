package com.example.myapplication.ui.start.findPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;

public class FindPasswordActivity1 extends AppCompatActivity {
    private Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        FindView();
        Toast.makeText(FindPasswordActivity1.this,"敬请期待",Toast.LENGTH_LONG).show();
    }

    private void FindView(){
        btn_back = findViewById(R.id.button__previous);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}