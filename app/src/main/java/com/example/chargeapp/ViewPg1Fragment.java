package com.example.chargeapp;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPg1Fragment extends Fragment {
    private static final String TAG = "ViewPg1Fragment";
    private String date;

    public ViewPg1Fragment() {
        // Required empty public constructor
    }


    //页面布局时调用，初始化
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_pg1, container, false);
    }


    //Activity调用时自动创建执行的方法
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        SharedPreferences sp = getActivity().getSharedPreferences("viewPager", Activity.MODE_PRIVATE);
        String date = sp.getString("date","");
        float totalMoney = sp.getFloat("totalMoney",0.0f);
        float chiMoney = sp.getFloat("chiMoney",0.0f);
        float chuanMoney = sp.getFloat("chuanMoney",0.0f);
        float yongMoney = sp.getFloat("yongMoney",0.0f);
        int mostType = sp.getInt("mostType", 0);

        String[] todayList = date.split("-");
        String year = todayList[0];
        String month = todayList[1];
        String day = todayList[2];

        TextView tv_date = getView().findViewById(R.id.textView_viewpg1_date);
        TextView tv_money2 = getView().findViewById(R.id.textView_viewpg1_money2);
        TextView tv_chi3 = getView().findViewById(R.id.textView_viewpg1_chi3);
        TextView tv_chuan3 = getView().findViewById(R.id.textView_viewpg1_chuan3);
        TextView tv_yong3 = getView().findViewById(R.id.textView_viewpg1_yong3);

        tv_date.setText(year + "年" + month + "月" + day + "日的账单 ");
        tv_money2.setText(String.valueOf(totalMoney));
        tv_chi3.setText(String.valueOf(chiMoney));
        tv_chuan3.setText(String.valueOf(chuanMoney));
        tv_yong3.setText(String.valueOf(yongMoney));
    }

}
