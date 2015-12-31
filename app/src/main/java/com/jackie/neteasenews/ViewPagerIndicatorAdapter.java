package com.jackie.neteasenews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewPagerIndicatorAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;
    private List<String> mCurrentTitleList;

    private static String[] DEFAULT_INDICATOR_ITEM = new String[] { "头条", "娱乐", "热点", "体育", "房产" , "NBA", "CBA" };

    public List<String> getCurrentTitleList() {
        return mCurrentTitleList;
    }

    public ViewPagerIndicatorAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.mFragmentList = fragmentList;

        mCurrentTitleList = new ArrayList<>();
        mCurrentTitleList.addAll(Arrays.asList(DEFAULT_INDICATOR_ITEM));
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCurrentTitleList.get(position);
    }
}