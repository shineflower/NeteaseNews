package com.jackie.neteasenews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private TabPageIndicator mTabPageIndicator;
    private ViewPager mViewPager;
    private ViewPagerIndicatorAdapter mAdapter;
    private ImageButton mArrowButton;

    private LayoutInflater mInflater;
    private View mIndicatorView;
    private IndicatorPopupWindow mIndicatorPopupWindow;
    private FlowLayout mCurrentFlowLayout;
    private FlowLayout mAllFlowLayout;

    private HeadlineFragment mHeadlineFragment;
    private EnjoyFragment mEnjoyFragment;
    private HotspotFragment mHotspotFragment;
    private SportFragment mSportFragment;
    private HouseFragment mHouseFragment;
    private NBAFragment mNBAFragment;
    private CBAFragment mCBAFragment;

    private MoreFragment mMoreFragment;
    private List<Fragment> mFragmentList;
    private List<String> mCurrentItemList;
    private List<String> mAllItemList;

    private static final String[] INDICATOR_CURRENT_ITEM = new String[] { "头条", "娱乐", "热点", "体育", "房产", "NBA", "CBA" };
    private static final String[] INDICATOR_ALL_ITEM = { "杭州", "财经", "科技", "跟帖", "直播", "时尚", "轻松一刻", "汽车", "段子", "军事",
                                                         "历史", "家居", "原创", "游戏", "健康", "政务", "漫画", "哒哒","彩票", "手机",
                                                         "移动互联", "中国足球", "社会", "影视", "国际足球", "跑步", "数码", "云课堂", "旅游", "读书",
                                                         "酒香", "教育", "亲子", "暴雪游戏", "情感", "艺术", "值得买", "图片", "博客", "论坛", "订阅" };
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
        mInflater = LayoutInflater.from(this);

        mTabPageIndicator = (TabPageIndicator) findViewById(R.id.indicator);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mArrowButton = (ImageButton) findViewById(R.id.indicator_arrow) ;

        mIndicatorView = mInflater.inflate(R.layout.activity_indicator, null);
        mIndicatorPopupWindow = new IndicatorPopupWindow(this, mIndicatorView);
        mCurrentFlowLayout = (FlowLayout) mIndicatorView.findViewById(R.id.current_flow_layout);
        mAllFlowLayout = (FlowLayout) mIndicatorView.findViewById(R.id.all_flow_layout);
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

        mCurrentItemList = new ArrayList<>();
        mCurrentItemList.addAll(Arrays.asList(INDICATOR_CURRENT_ITEM));

        mAllItemList = new ArrayList<>();
        mAllItemList.addAll(Arrays.asList(INDICATOR_ALL_ITEM));

        //初始化当前条目
        for (int i = 0; i < mCurrentItemList.size(); i++) {
            final int position = i;
            final IndicatorTextView indicatorTextView = (IndicatorTextView) mInflater.inflate(R.layout.indicator_item_textview, mCurrentFlowLayout, false);
            indicatorTextView.setText(mCurrentItemList.get(i));
            mCurrentFlowLayout.addView(indicatorTextView);

            indicatorTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIndicatorPopupWindow.dismiss();
                    //直接显示
                    mTabPageIndicator.setViewPager(mViewPager, position);
                }
            });
        }

        //初始化候选条目
        for (int i = 0; i < mAllItemList.size(); i++) {
            final int position = i;
            final IndicatorTextView indicatorTextView = (IndicatorTextView) mInflater.inflate(R.layout.indicator_item_textview, mAllFlowLayout, false);
            indicatorTextView.setText(mAllItemList.get(i));
            mAllFlowLayout.addView(indicatorTextView);

            indicatorTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //如果已经从候选列表中移除，则已经添加到了当前列表，点击的时候，直接显示当前页面
                    if (indicatorTextView.isRemovedFromAllItemList()) {
                        mIndicatorPopupWindow.dismiss();
                        //直接显示
                        mTabPageIndicator.setViewPager(mViewPager, mCurrentItemList.indexOf(indicatorTextView.getText().toString()));
                    } else {  //如果没有从候选列表中移除，将该TextView标记为已经移除
                        //添加新的Fragment
                        mMoreFragment = new MoreFragment(indicatorTextView.getText().toString());
                        mFragmentList.add(mMoreFragment);

                        //添加相应的标题
                        mCurrentItemList.add(indicatorTextView.getText().toString());
                        mAdapter.notifyDataSetChanged();

                        mAllItemList.remove(position);
                        mAllFlowLayout.removeView(indicatorTextView);
                        indicatorTextView.setIsRemovedFromAllItemList(true);

                        mCurrentFlowLayout.addView(indicatorTextView);
                    }
                }
            });
        }
    }

    private void data2View() {
        mAdapter = new ViewPagerIndicatorAdapter(getSupportFragmentManager(), mFragmentList, mCurrentItemList);
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

                mArrowButton.setBackgroundResource(R.drawable.arrow_up);
            }
        });

        mIndicatorPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mArrowButton.setBackgroundResource(R.drawable.arrow_down);
            }
        });

        //更新指示器，会调用getPageTitle方法
        mTabPageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabPageIndicator.notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
