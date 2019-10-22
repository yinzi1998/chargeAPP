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
public class AdapterListChargeDetail extends ArrayAdapter {
    private static final String TAG = "MyAdapter";

    //构造函数
    public AdapterListChargeDetail(Context context , int resource , ArrayList<HashMap<String , String>> list){
        super(context , resource , list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_charge_detail , parent , false);
        }

        Map<String , String> map = (Map<String , String>)getItem(position);
        TextView detail = convertView.findViewById(R.id.textView_detailitem_detail);
        TextView date = convertView.findViewById(R.id.textView_detailitem_date);
        ImageView type = convertView.findViewById(R.id.imageView_detailitem_type);

        detail.setText(map.get("detail"));
        date.setText(map.get("date"));
        String typeStr = map.get("type");
        if(typeStr.equals("1")){
            type.setImageResource(R.mipmap.chi);
        }else if(typeStr.equals("2")){
            type.setImageResource(R.mipmap.chuan);
        }else{
            type.setImageResource(R.mipmap.yong);
        }

        return convertView;
    }
}

