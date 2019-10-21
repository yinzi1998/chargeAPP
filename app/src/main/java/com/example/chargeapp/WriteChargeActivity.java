package com.example.chargeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WriteChargeActivity extends AppCompatActivity {
    private String year;
    private String month;
    private String day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_charge);

        //获取年月日
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = sdf.format(today);
        String[] todayList = todayStr.split("-");
        year = todayList[0];
        month = todayList[1];
        day = todayList[2];
        TextView tv_Date = findViewById(R.id.textView_write_date);
        tv_Date.setText(year + " 年 " + month + " 月 " + day + " 日 ");
    }
}
