package com.example.chargeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

public class ChargeDayItemActivity extends AppCompatActivity {
    private static final String TAG = "ChargeDayItemActivity";
    private String name;
    private String date;
    private float money_total = 0.0f;
    private float money_chi = 0.0f;
    private float money_chuan = 0.0f;
    private float money_yong = 0.0f;
    private float money_most = 0.0f;
    private int most_type = 0;
    DBManager manager = new DBManager(ChargeDayItemActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_day_item);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        date = intent.getStringExtra("date");

        for(DBChargeItem i : manager.showListAll_charge()){
            if(name.equals(i.getName())){
                if(date.equals(i.getDate())){
                    money_total += Float.parseFloat(i.getMoney());
                    if(i.getType().equals("1")){
                        money_chi += Float.parseFloat(i.getMoney());
                    }else if(i.getType().equals("2")){
                        money_chuan += Float.parseFloat(i.getMoney());
                    }else{
                        money_yong += Float.parseFloat(i.getMoney());
                    }
                }
            }
        }

        if(money_chi >= money_chuan){
            money_most = money_chi;
            most_type = 1;
        }else{
            money_most = money_chuan;
            most_type = 2;
        }
        if(money_yong>money_most){
            money_most = money_yong;
            most_type = 3;
        }

        //在SharedPreferences文件中写入数据，以便向Fragment中传递数据
        SharedPreferences sp = getSharedPreferences("viewPager", Activity.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("date",date);
        Log.i(TAG, "onCreate: 写入的日期= " + date);
        ed.putFloat("totalMoney",money_total);
        Log.i(TAG, "onCreate: 写入的总花费= " + money_total);
        ed.putFloat("chiMoney",money_chi);
        Log.i(TAG, "onCreate: 写入的吃花费= " + money_chi);
        ed.putFloat("chuanMoney",money_chuan);
        Log.i(TAG, "onCreate: 写入的穿花费= " + money_chuan);
        ed.putFloat("yongMoney",money_yong);
        Log.i(TAG, "onCreate: 写入的用花费= " + money_yong);
        ed.putFloat("mostMoney",money_most);
        Log.i(TAG, "onCreate: 写入的最多花费= " + money_most);
        ed.putInt("mostType",most_type);
        Log.i(TAG, "onCreate: 写入的最多花费类型= " + most_type);
        Log.i(TAG, "onCreate: 向SharedPreferences中写数据");
        ed.apply();

        //滑动窗口
        ViewPager viewPager = findViewById(R.id.viewpager_dayitem);
        //Adapter管理数据
        AdapterDayViewPg adapterViewPg = new AdapterDayViewPg(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPg);
        //滑动窗口上方标题
        TabLayout tabLayout = findViewById(R.id.slidingtabs_dayitem);
        //将上方标题与滑动窗口绑定
        tabLayout.setupWithViewPager(viewPager);
    }
}
