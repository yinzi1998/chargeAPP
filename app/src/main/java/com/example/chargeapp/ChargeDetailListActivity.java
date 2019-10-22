package com.example.chargeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

//详细支出的列表显示ListView
public class ChargeDetailListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{
    private static final String TAG = "DetailListActivity";
    //用于存放详细支出列表的数据，包括date,detail,type,money
    private ArrayList<HashMap<String,String>> ChargeDetailListItems;
    private ListView listView;
    AdapterListChargeDetail adapterChargeDetail;
    DBManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        manager = new DBManager(ChargeDetailListActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_detail_list);

        ChargeDetailListItems = new ArrayList<HashMap<String, String>>();
        for(DBChargeItem i : manager.showListAll_charge()){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("date" , i.getDate());
            map.put("type" , i.getType());
            map.put("detail" , i.getDetail());
            map.put("money" , i.getMoney());
            ChargeDetailListItems.add(map);
        }

        listView = findViewById(R.id.ListView_charge_detail);
        adapterChargeDetail = new AdapterListChargeDetail(ChargeDetailListActivity.this, R.layout.list_item_charge_detail, ChargeDetailListItems);
        listView.setAdapter(adapterChargeDetail);
        //没有数据的时候显示nodata的TextView
        listView.setEmptyView(findViewById(R.id.TextView_chargedetail_nodata));
        //添加列表短按事件的监听
        listView.setOnItemClickListener(ChargeDetailListActivity.this);
        //添加列表长按事件的监听
        listView.setOnItemLongClickListener(ChargeDetailListActivity.this);
    }

    //短按列表，显示详细账单信息
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Log.i(TAG, "onItemClick: 点击的id= " + id);

        //通过position获得数据
        HashMap<String, String> map = (HashMap<String, String>) listView.getItemAtPosition(position);
        String date = map.get("date");
        String type = map.get("type");
        String detail = map.get("detail");
        String money = map.get("money");
        Log.i(TAG, "onItemClick: 详细支出点击的列表项位置为= " + position);


        //打开新的界面 activity_rate_calc.xml，传入参数
        Intent chargeItem = new Intent(this , ChargeDetailItemActivity.class);
        chargeItem.putExtra("date", date);
        chargeItem.putExtra("type", type);
        chargeItem.putExtra("detail", detail);
        chargeItem.putExtra("money", money);
        startActivity(chargeItem);
    }

    //列表长按的触发事件，删除对应的事项
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long id) {
        Log.i(TAG, "onItemLongClick: 长按删除的id= " + id);

        //构造对话框来删除item
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("请确认是否删除当前数据").setPositiveButton("是", new DialogInterface.OnClickListener() {
            //“是”按钮的点击事件
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i(TAG, "onClick: 对话框确定按钮事件处理");
                Log.i(TAG, "onClick: 长按删除后的list长度= " + ChargeDetailListItems.size());
                manager.delete_charge(position+1);
                Log.i(TAG, "onClick: 长按删除数据库的position= " + position+1);
                ChargeDetailListItems.remove(position);
                adapterChargeDetail.notifyDataSetChanged();
            }
        })
                .setNegativeButton("否", null);
        builder.create().show();

        //false的时候，依旧触发点击事件；true的时候，不会触发点击事件
        return true;
    }
}
