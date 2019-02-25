package com.example.a0222.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.a0222.fragment.BaseFragment;
import com.example.a0222.fragment.RecommendFragment;


public class HomePageAdapter extends FragmentPagerAdapter {

    private String[] pageNames = new String[]{"推荐", "小视频", "视频", "热点", "北京", "娱乐", "财经", "军事"};

    public HomePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new RecommendFragment();
            default:
                return new BaseFragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pageNames[position];
    }

    @Override
    public int getCount() {
        return pageNames.length;
    }
}
