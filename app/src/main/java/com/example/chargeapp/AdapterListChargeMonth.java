package com.example.chargeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//详细支出的适配器
public class AdapterListChargeMonth extends ArrayAdapter {
    private static final String TAG = "MyAdapter";

    //构造函数
    public AdapterListChargeMonth(Context context , int resource , ArrayList<HashMap<String , String>> list){
        super(context , resource , list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_charge_month , parent , false);
        }

        Map<String , String> map = (Map<String , String>)getItem(position);
        TextView date = convertView.findViewById(R.id.textView_monthitem_date);

        String[] todayList = map.get("date").split("-");
        String year = todayList[0];
        String month = todayList[1];
        date.setText(year + " 年 " + month + " 月 ");

        return convertView;
    }
}
