package com.jackie.neteasenews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private TabPageIndicator mTabPageIndicator;
    private ViewPager mViewPager;
    private ViewPagerIndicatorAdapter mAdapter;

    private HeadlineFragment mHeadlineFragment;
    private EnjoyFragment mEnjoyFragment;
    private HotspotFragment mHotspotFragment;
    private SportFragment mSportFragment;
    private HouseFragment mHouseFragment;
    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mTabPageIndicator = (TabPageIndicator) findViewById(R.id.indicator);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mHeadlineFragment = new HeadlineFragment();
        mEnjoyFragment = new EnjoyFragment();
        mHotspotFragment = new HotspotFragment();
        mSportFragment = new SportFragment();
        mHouseFragment = new HouseFragment();

        mFragmentList = new ArrayList<>();
        mFragmentList.add(mHeadlineFragment);
        mFragmentList.add(mEnjoyFragment);
        mFragmentList.add(mHotspotFragment);
        mFragmentList.add(mSportFragment);
        mFragmentList.add(mHouseFragment);

        mAdapter = new ViewPagerIndicatorAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mAdapter);

        //实例化ViewPagerIndicatorAdapter然后设置ViewPager与之关联
        mTabPageIndicator.setViewPager(mViewPager, 0);
    }
}
