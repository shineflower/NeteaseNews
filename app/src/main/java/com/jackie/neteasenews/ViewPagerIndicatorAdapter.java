package com.jackie.neteasenews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ViewPagerIndicatorAdapter extends FragmentPagerAdapter {
    private LinkedList<Fragment> mFragmentList;
    private List<String> mCurrentTitleList;

    private static String[] DEFAULT_INDICATOR_CURRENT_TITLES = new String[] { "头条", "娱乐", "热点", "体育", "房产" , "NBA", "CBA"};

    public ViewPagerIndicatorAdapter(FragmentManager fm, LinkedList<Fragment> fragmentList) {
        super(fm);
        this.mFragmentList = fragmentList;

        mCurrentTitleList = new ArrayList<>();
        mCurrentTitleList.addAll(Arrays.asList(DEFAULT_INDICATOR_CURRENT_TITLES));

        if (fragmentList.getLast() instanceof  OtherFragment) {
            OtherFragment lastFragment = (OtherFragment) fragmentList.getLast();

            //通过反射获取最新添加Fragment的mTitle属性的值
            String title = getObjectFiledValue(lastFragment, "mTitle");
            mCurrentTitleList.add(title);
        }
    }

    /**
     * 通过反射获取object的某个属性值
     * @param object
     * @param fieldName
     * @return
     */
    private String getObjectFiledValue(Object object, String fieldName) {
        String value = null;
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            String fieldValue = (String) field.get(object);
            if (fieldName != null && !TextUtils.isEmpty(fieldValue)) {
                value = fieldValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
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