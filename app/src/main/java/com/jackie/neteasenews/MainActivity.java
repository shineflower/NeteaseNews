package com.jackie.neteasenews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private TabPageIndicator mTabPageIndicator;
    private ViewPager mViewPager;
    private ViewPagerIndicatorAdapter mAdapter;
    private ImageButton mArrowButton;

    private View mIndicatorView;
    private IndicatorPopupWindow mIndicatorPopupWindow;

    private HeadlineFragment mHeadlineFragment;
    private EnjoyFragment mEnjoyFragment;
    private HotspotFragment mHotspotFragment;
    private SportFragment mSportFragment;
    private HouseFragment mHouseFragment;
    private NBAFragment mNBAFragment;
    private CBAFragment mCBAFragment;
    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        data2View();
        initEvent();
    }

    private void initView() {
        mTabPageIndicator = (TabPageIndicator) findViewById(R.id.indicator);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mArrowButton = (ImageButton) findViewById(R.id.indicator_arrow) ;

        mIndicatorView = LayoutInflater.from(this).inflate(R.layout.activity_indicator, null);
        mIndicatorPopupWindow = new IndicatorPopupWindow(this, mIndicatorView);
    }

    private void initData() {
        mHeadlineFragment = new HeadlineFragment();
        mEnjoyFragment = new EnjoyFragment();
        mHotspotFragment = new HotspotFragment();
        mSportFragment = new SportFragment();
        mHouseFragment = new HouseFragment();
        mNBAFragment = new NBAFragment();
        mCBAFragment = new CBAFragment();

        mFragmentList = new ArrayList<>();
        mFragmentList.add(mHeadlineFragment);
        mFragmentList.add(mEnjoyFragment);
        mFragmentList.add(mHotspotFragment);
        mFragmentList.add(mSportFragment);
        mFragmentList.add(mHouseFragment);
        mFragmentList.add(mNBAFragment);
        mFragmentList.add(mCBAFragment);
    }

    private void data2View() {
        mAdapter = new ViewPagerIndicatorAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mAdapter);

        //实例化ViewPagerIndicatorAdapter然后设置ViewPager与之关联
        mTabPageIndicator.setViewPager(mViewPager, 0);
    }

    private void initEvent() {
        mArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出
                mIndicatorPopupWindow.setAnimationStyle(R.style.popup_window_anim);
                mIndicatorPopupWindow.showAsDropDown(mArrowButton, 0, mTabPageIndicator.getHeight() / 2 - mArrowButton.getHeight());
            }
        });
    }
}
