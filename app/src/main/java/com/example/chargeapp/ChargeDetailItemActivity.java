package com.example.chargeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChargeDetailItemActivity extends AppCompatActivity {
    private static final String TAG = "DetailItemActivity";
    private String date;
    private String type;
    private String detail;
    private String money;
    private String year,month,day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_detail_item);

        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        type = intent.getStringExtra("type");
        detail = intent.getStringExtra("detail");
        money = intent.getStringExtra("money");

        //获取年月日
        String[] todayList = date.split("-");
        year = todayList[0];
        month = todayList[1];
        day = todayList[2];
        TextView tv_Date = findViewById(R.id.textView_chargeitem_time);
        TextView tv_Type = findViewById(R.id.textView_chargeitem_type);
        TextView tv_Detail = findViewById(R.id.textView_chargeitem_detail);
        TextView tv_Money = findViewById(R.id.textView_chargeitem_money);
        tv_Date.setText(year + " 年 " + month + " 月 " + day + " 日 ");
        tv_Detail.setText(detail);
        tv_Money.setText(money + "元");
        if(type.equals("1")){
            tv_Type.setText("吃饱了，才有力气学习！");
        }else if(type.equals("2")){
            tv_Type.setText("买买衣服，打扮一下！");
        }else{
            tv_Type.setText("实用才是硬道理！");
        }
    }
}
