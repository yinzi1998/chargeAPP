package com.example.chargeapp;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPg4Fragment extends Fragment {


    public ViewPg4Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pg4, container, false);
    }

    //Activity调用时自动创建执行的方法
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        SharedPreferences sp = getActivity().getSharedPreferences("viewPager", Activity.MODE_PRIVATE);
        String date = sp.getString("date","");
        String mostDayDate = sp.getString("mostDayDate","");
        String leastDayDate = sp.getString("leastDayDate","");
        float totalMoney = sp.getFloat("totalMoney",0.0f);
        float chiMoney = sp.getFloat("chiMoney",0.0f);
        float chuanMoney = sp.getFloat("chuanMoney",0.0f);
        float yongMoney = sp.getFloat("yongMoney",0.0f);
        float mostDayMoney = sp.getFloat("mostDayMoney", 0.0f);
        float leastDayMoney = sp.getFloat("leastDayMoney", 0.0f);
        int mostType = sp.getInt("mostType", 0);

        String[] todayList = date.split("-");
        String year = todayList[0];
        String month = todayList[1];
        String day = todayList[2];

        TextView tv_date = getView().findViewById(R.id.textView_viewpg4_date);
        TextView tv_chi3 = getView().findViewById(R.id.textView_viewpg4_chi3);
        TextView tv_chuan3 = getView().findViewById(R.id.textView_viewpg4_chuan3);
        TextView tv_yong3 = getView().findViewById(R.id.textView_viewpg4_yong3);
        TextView tv_mostday2 = getView().findViewById(R.id.textView_viewpg4_mostday2);
        TextView tv_mostday4 = getView().findViewById(R.id.textView_viewpg4_mostday4);
        TextView tv_leastday2 = getView().findViewById(R.id.textView_viewpg4_leastday2);
        TextView tv_leastday4 = getView().findViewById(R.id.textView_viewpg4_leastday4);
        ImageView iv_most = getView().findViewById(R.id.imageView_viewpg4_most);

        tv_mostday4.setText(String.valueOf(mostDayMoney));
        tv_leastday4.setText(String.valueOf(leastDayMoney));
        tv_mostday2.setText("本月" + mostDayDate + "日");
        tv_leastday2.setText("本月" + leastDayDate + "日");
        tv_date.setText(year + " 年 " + month + " 月的账单 ");
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        tv_chi3.setText(decimalFormat.format(chiMoney/totalMoney));
        tv_chuan3.setText(decimalFormat.format(chuanMoney/totalMoney));
        tv_yong3.setText(decimalFormat.format(yongMoney/totalMoney));
        if(mostType == 1){
            iv_most.setImageResource(R.mipmap.chi);
        }else if(mostType == 2){
            iv_most.setImageResource(R.mipmap.chuan);
        }else{
            iv_most.setImageResource(R.mipmap.yong);
        }
    }

}
