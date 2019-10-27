package com.example.chargeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class ChargeMonthItemActivity extends AppCompatActivity {
    private static final String TAG = "ChargeMonthItemActivity";
    private String name;
    //存放每一天的金额
    private ArrayList<HashMap<String,String>> dayMoneyList = new ArrayList<>();
    private String date;
    private String mostday_date = "";
    private String leastday_date = "";
    private float money_total = 0.0f;
    private float money_chi = 0.0f;
    private float money_chuan = 0.0f;
    private float money_yong = 0.0f;
    private float money_most = 0.0f;
    private float mostday_money = 0.0f;
    private float leastday_money = 0.0f;
    private int most_type = 0;
    DBManager manager = new DBManager(ChargeMonthItemActivity.this);
    private int tab = 0;
    private String temp_day = "";
    private String temp_month = "";
    private String temp_year = "";
    private float temp_dayMoney = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_month_item);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        date = intent.getStringExtra("date");

        for(DBChargeItem i : manager.showListAll_charge()){
            if(name.equals(i.getName())){
                if(date.split("-")[0].equals(i.getDate().split("-")[0]) && date.split("-")[1].equals(i.getDate().split("-")[1])){
                    money_total += Float.parseFloat(i.getMoney());
                    if(i.getType().equals("1")){
                        money_chi += Float.parseFloat(i.getMoney());
                    }else if(i.getType().equals("2")){
                        money_chuan += Float.parseFloat(i.getMoney());
                    }else{
                        money_yong += Float.parseFloat(i.getMoney());
                    }
                    //计算每一天的花销
                    if(tab == 0){
                        temp_day = i.getDate().split("-")[2];
                        temp_dayMoney += Float.parseFloat(i.getMoney());
                        tab++;
                    }else{
                        if(temp_day.equals(i.getDate().split("-")[2])){
                            temp_dayMoney += Float.parseFloat(i.getMoney());
                            Log.i(TAG, "onCreate: 同一天加金额= " + temp_dayMoney);
                        }else{
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("date" ,temp_day);
                            map.put("money" , String.valueOf(temp_dayMoney));
                            dayMoneyList.add(map);
                            Log.i(TAG, "onCreate: 不同天加map= " + temp_day + "天花" + temp_dayMoney);
                            temp_dayMoney = Float.parseFloat(i.getMoney());
                            temp_day = i.getDate().split("-")[2];
                        }
                    }
                }
            }
        }
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("date" , temp_day);
        map.put("money" , String.valueOf(temp_dayMoney));
        dayMoneyList.add(map);
        Log.i(TAG, "onCreate: 不同天加map= " + temp_day + "天花" + temp_dayMoney);

        //计算哪一个类型花费最多
        if(money_chi >= money_chuan){
            money_most = money_chi;
            most_type = 1;
        }else{
            money_most = money_chuan;
            most_type = 2;
        }
        if(money_yong > money_most){
            money_most = money_yong;
            most_type = 3;
        }

        //计算哪一天花费最多最少
        float max = Float.parseFloat(dayMoneyList.get(0).get("money"));
        float min = Float.parseFloat(dayMoneyList.get(0).get("money"));
        mostday_date = dayMoneyList.get(0).get("date");
        mostday_money = Float.parseFloat(dayMoneyList.get(0).get("money"));
        leastday_date = dayMoneyList.get(0).get("date");
        leastday_money = Float.parseFloat(dayMoneyList.get(0).get("money"));
        Log.i(TAG, "onCreate: maplist里面第一个钱也就是maxmin= " + max);
        for(HashMap<String, String> i : dayMoneyList){
            Log.i(TAG, "onCreate: mapList循环到了= " + i.get("date") + "天花" + i.get("money"));
            if(Float.parseFloat(i.get("money")) > max){
                mostday_date = i.get("date");
                mostday_money = Float.parseFloat(i.get("money"));
                max = Float.parseFloat(i.get("money"));
            }
            if(Float.parseFloat(i.get("money")) < min){
                leastday_date = i.get("date");
                leastday_money = Float.parseFloat(i.get("money"));
                min = Float.parseFloat(i.get("money"));
            }
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
        ed.putString("mostDayDate",mostday_date);
        Log.i(TAG, "onCreate: 写入的最多花费日期= " + mostday_date);
        ed.putFloat("mostDayMoney",mostday_money);
        Log.i(TAG, "onCreate: 写入的最多花费日期金额= " + mostday_money);
        ed.putString("leastDayDate",leastday_date);
        Log.i(TAG, "onCreate: 写入的最少花费日期= " + mostday_date);
        ed.putFloat("leastDayMoney",leastday_money);
        Log.i(TAG, "onCreate: 写入的最少花费日期金额= " + mostday_money);
        Log.i(TAG, "onCreate: 向SharedPreferences中写数据");
        ed.apply();

        //滑动窗口
        ViewPager viewPager = findViewById(R.id.viewpager_monthitem);
        //Adapter管理数据
        AdapterMonthViewPg adapterViewPg = new AdapterMonthViewPg(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPg);
        //滑动窗口上方标题
        TabLayout tabLayout = findViewById(R.id.slidingtabs_monthitem);
        //将上方标题与滑动窗口绑定
        tabLayout.setupWithViewPager(viewPager);
    }
}
