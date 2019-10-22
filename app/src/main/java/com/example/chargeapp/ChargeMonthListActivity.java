package com.example.chargeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class ChargeMonthListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private static final String TAG = "MonthListActivity";
    //用于存放月账单列表的数据
    private ArrayList<HashMap<String,String>> ChargeMonthListItems;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DBManager manager = new DBManager(ChargeMonthListActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_month_list);
        int tab = 0;
        String temp_month = "";
        String temp_year = "";
        String temp_date = "";

        ChargeMonthListItems = new ArrayList<HashMap<String, String>>();
        for(DBChargeItem i : manager.showListAll_charge()){
            if(tab == 0){
                temp_month = i.getDate().split("-")[1];
                temp_year = i.getDate().split("-")[0];
                temp_date = i.getDate();
                tab++;
            }else{
                if((!temp_month.equals(i.getDate().split("-")[1])) || (!temp_year.equals(i.getDate().split("-")[0]))){
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("date" , temp_date);
                    ChargeMonthListItems.add(map);
                    temp_month = i.getDate().split("-")[1];
                    temp_year = i.getDate().split("-")[0];
                    temp_date = i.getDate();
                }
            }
        }
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("date" , temp_date);
        ChargeMonthListItems.add(map);

        listView = findViewById(R.id.ListView_charge_month);
        AdapterListChargeMonth adapterChargeMonth = new AdapterListChargeMonth(ChargeMonthListActivity.this, R.layout.list_item_charge_month, ChargeMonthListItems);
        listView.setAdapter(adapterChargeMonth);
        //没有数据的时候显示nodata的TextView
        listView.setEmptyView(findViewById(R.id.TextView_chargemonth_nodata));
        //添加列表短按事件监听
        listView.setOnItemClickListener(ChargeMonthListActivity.this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Log.i(TAG, "onItemClick: 点击的id= " + id);

        //通过position获得数据
        HashMap<String, String> map = (HashMap<String, String>) listView.getItemAtPosition(position);
        String date = map.get("date");
        Log.i(TAG, "onItemClick: 日账单点击的列表项位置为= " + position);


        //打开新的界面 activity_rate_calc.xml，传入参数
        Intent chargeItem = new Intent(this , ChargeMonthItemActivity.class);
        chargeItem.putExtra("date", date);
        startActivity(chargeItem);
    }
}
