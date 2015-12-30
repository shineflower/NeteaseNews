package com.jackie.neteasenews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPagerIndicatorAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;

    public static String[] TITLES = new String[] { "头条", "娱乐", "热点", "体育", "房产" };

    public ViewPagerIndicatorAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.mFragmentList = fragmentList;
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
        return TITLES[position];
    }
}