package com.example.chargeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
        write.putExtra("name", name);
        Log.i(TAG, "jilu: 点击记录你的支出，跳转记录界面");
        startActivityForResult(write,1);
    }

    //详细支出按钮的事件
    public void charge_detail(View view){
        Intent charge_detail = new Intent(this,ChargeDetailListActivity.class);
        charge_detail.putExtra("name", name);
        Log.i(TAG, "jilu: 点击记录你的支出，跳转记录界面");
        startActivity(charge_detail);
    }

    //日账单按钮的事件
    public void charge_day(View view){
        Intent charge_day = new Intent(this,ChargeDayListActivity.class);
        charge_day.putExtra("name", name);
        Log.i(TAG, "jilu: 点击日账单，跳转日账单界面");
        startActivity(charge_day);
    }

    //月账单按钮的事件
    public void charge_month(View view){
        Intent charge_month = new Intent(this,ChargeMonthListActivity.class);
        charge_month.putExtra("name", name);
        Log.i(TAG, "jilu: 点击月账单，跳转月账单界面");
        startActivity(charge_month);
    }

    //接受跳转界面的finish()数据的方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == 1){
            Intent intent = getIntent();
            name = intent.getStringExtra("name");
        }
    }
}
