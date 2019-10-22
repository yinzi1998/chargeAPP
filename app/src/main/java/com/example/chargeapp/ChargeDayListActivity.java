package com.example.chargeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class ChargeDayListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private static final String TAG = "DayListActivity";
    //用于存放日账单列表的数据
    private ArrayList<HashMap<String,String>> ChargeDayListItems;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DBManager manager = new DBManager(ChargeDayListActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_day_list);
        int tab = 0;
        String temp_date = "";

        ChargeDayListItems = new ArrayList<HashMap<String, String>>();
        for(DBChargeItem i : manager.showListAll_charge()){
            if(tab == 0){
                temp_date = i.getDate();
                tab++;
            }else{
                if(!temp_date.equals(i.getDate())){
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("date" , temp_date);
                    ChargeDayListItems.add(map);
                    temp_date = i.getDate();
                }
            }
        }
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("date" , temp_date);
        ChargeDayListItems.add(map);

        listView = findViewById(R.id.ListView_charge_day);
        AdapterListChargeDay adapterChargeDay = new AdapterListChargeDay(ChargeDayListActivity.this, R.layout.list_item_charge_day, ChargeDayListItems);
        listView.setAdapter(adapterChargeDay);
        //没有数据的时候显示nodata的TextView
        listView.setEmptyView(findViewById(R.id.TextView_chargeday_nodata));
        //添加列表短按事件监听
        listView.setOnItemClickListener(ChargeDayListActivity.this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Log.i(TAG, "onItemClick: 点击的id= " + id);

        //通过position获得数据
        HashMap<String, String> map = (HashMap<String, String>) listView.getItemAtPosition(position);
        String date = map.get("date");
        Log.i(TAG, "onItemClick: 日账单点击的列表项位置为= " + position);


        //打开新的界面 activity_rate_calc.xml，传入参数
        Intent chargeItem = new Intent(this , ChargeDayItemActivity.class);
        chargeItem.putExtra("date", date);
        startActivity(chargeItem);
    }
}
