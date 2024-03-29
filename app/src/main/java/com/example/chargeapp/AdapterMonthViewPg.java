package com.example.chargeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class AdapterMonthViewPg extends FragmentPagerAdapter {

    private String[] title = {"消费记录", "消费分析"};

    //构造方法
    public AdapterMonthViewPg(FragmentManager manager){
        super(manager);
    }

    //管理显示滑动窗口下显示哪个页面
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new ViewPg3Fragment();
        }else{
            return new ViewPg4Fragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return 2;
    }
}
