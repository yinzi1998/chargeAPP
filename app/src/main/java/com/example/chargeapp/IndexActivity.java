package com.example.chargeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class IndexActivity extends AppCompatActivity {
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        //接受传过来的用户名
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        TextView tv_name = findViewById(R.id.textView_index_name);
        tv_name.setText("欢迎您，" + name);
    }
}
