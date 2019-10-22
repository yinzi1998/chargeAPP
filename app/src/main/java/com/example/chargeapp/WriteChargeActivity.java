package com.example.chargeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class WriteChargeActivity extends AppCompatActivity {
    private static final String TAG = "WriteChargeActivity";
    private String todayStr;
    private String year;
    private String month;
    private String day;
    //type为1是吃，2是穿，3是用
    String type = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_charge);

        //获取年月日
        //直接获取是美国时间，会晚八小时，要设置时区加上八小时
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Date today = Calendar.getInstance(TimeZone.getTimeZone("GMT+8")).getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        todayStr = sdf.format(today);
        Log.i(TAG, "onCreate: 今天的日期是= " + todayStr);
        String[] todayList = todayStr.split("-");
        year = todayList[0];
        month = todayList[1];
        day = todayList[2];
        TextView tv_Date = findViewById(R.id.textView_write_date);
        tv_Date.setText(year + " 年 " + month + " 月 " + day + " 日 ");
    }

    //吃穿用按钮事件
    public void type(View view){
        ImageButton chi = findViewById(R.id.imageButton_write_chi);
        ImageButton chuan = findViewById(R.id.imageButton_write_chuan);
        ImageButton yong = findViewById(R.id.imageButton_write_yong);
        if(view.getId() == chi.getId()){
            type = "1";
            chi.setBackgroundColor(Color.parseColor("#00FF0000"));
            chuan.setBackgroundColor(Color.parseColor("#00FF0000"));
            yong.setBackgroundColor(Color.parseColor("#00FF0000"));
            chi.setBackgroundColor(Color.parseColor("#8e24aa"));
        }
        if(view.getId() == chuan.getId()){
            type = "2";
            chi.setBackgroundColor(Color.parseColor("#00FF0000"));
            chuan.setBackgroundColor(Color.parseColor("#00FF0000"));
            yong.setBackgroundColor(Color.parseColor("#00FF0000"));
            chuan.setBackgroundColor(Color.parseColor("#039be5"));
        }
        if(view.getId() == yong.getId()){
            type = "3";
            chi.setBackgroundColor(Color.parseColor("#00FF0000"));
            chuan.setBackgroundColor(Color.parseColor("#00FF0000"));
            yong.setBackgroundColor(Color.parseColor("#00FF0000"));
            yong.setBackgroundColor(Color.parseColor("#43a047"));
        }
    }

    //完成按钮的事件
    public void done(View view){
        DBManager manager = new DBManager(WriteChargeActivity.this);
        EditText et_detail = findViewById(R.id.editText_write_detail);
        EditText et_money = findViewById(R.id.editText_write_money);
        String detail = et_detail.getText().toString();
        String money = et_money.getText().toString();

        if(type.equals("0")){
            Toast.makeText(WriteChargeActivity.this,"别忘了选择类型哦",Toast.LENGTH_SHORT).show();
        }else if(detail==null || detail.equals("")){
            Toast.makeText(WriteChargeActivity.this,"别忘了记录你做了什么哦",Toast.LENGTH_SHORT).show();
        }else if(money==null || money.equals("")){
            Toast.makeText(WriteChargeActivity.this,"别忘了记录花了多少钱哦",Toast.LENGTH_SHORT).show();
        }else{
            DBChargeItem charge = new DBChargeItem(todayStr, type, detail, money);
            manager.add_charge(charge);
            Log.i(TAG, "done: 插入一条记录到数据库记账表中");
            Toast.makeText(WriteChargeActivity.this,"记账成功啦",Toast.LENGTH_SHORT).show();
            Intent index = new Intent(this,IndexActivity.class);
            Log.i(TAG, "done: 完成记账，返回index界面");
            startActivity(index);
        }

    }
}
