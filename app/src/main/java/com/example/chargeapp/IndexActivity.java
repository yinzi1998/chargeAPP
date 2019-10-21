package com.example.chargeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class IndexActivity extends AppCompatActivity {
    private static final String TAG = "IndexActivity";
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

    //记录你的支出按钮的事件
    public void jilu(View view){
        Intent write = new Intent(this,WriteChargeActivity.class);
        Log.i(TAG, "jilu: 点击记录你的支出，跳转记录界面");
        startActivity(write);
    }
}
