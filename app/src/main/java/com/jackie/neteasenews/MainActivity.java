package com.jackie.neteasenews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.viewpagerindicator.TabPageIndicator;

import java.util.LinkedList;

public class MainActivity extends FragmentActivity {
    private TabPageIndicator mTabPageIndicator;
    private ViewPager mViewPager;
    private ViewPagerIndicatorAdapter mAdapter;
    private ImageButton mArrowButton;

    private LayoutInflater mInflater;
    private View mIndicatorView;
    private IndicatorPopupWindow mIndicatorPopupWindow;
    private FlowLayout mFlowLayout;

    private HeadlineFragment mHeadlineFragment;
    private EnjoyFragment mEnjoyFragment;
    private HotspotFragment mHotspotFragment;
    private SportFragment mSportFragment;
    private HouseFragment mHouseFragment;
    private NBAFragment mNBAFragment;
    private CBAFragment mCBAFragment;

    private OtherFragment mOtherFragment;
    private LinkedList<Fragment> mFragmentList;

    private static final String[] INDICATOR_APPEND_TITLES = { "杭州", "财经", "科技", "跟帖", "直播", "时尚", "轻松一刻", "汽车", "段子", "移动互联" };

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
        mFlowLayout = (FlowLayout) mIndicatorView.findViewById(R.id.flow_layout);
    }

    private void initData() {
        mHeadlineFragment = new HeadlineFragment();
        mEnjoyFragment = new EnjoyFragment();
        mHotspotFragment = new HotspotFragment();
        mSportFragment = new SportFragment();
        mHouseFragment = new HouseFragment();
        mNBAFragment = new NBAFragment();
        mCBAFragment = new CBAFragment();

        mFragmentList = new LinkedList<>();
        mFragmentList.add(mHeadlineFragment);
        mFragmentList.add(mEnjoyFragment);
        mFragmentList.add(mHotspotFragment);
        mFragmentList.add(mSportFragment);
        mFragmentList.add(mHouseFragment);
        mFragmentList.add(mNBAFragment);
        mFragmentList.add(mCBAFragment);

        for (int i = 0; i < INDICATOR_APPEND_TITLES.length; i++) {
            final TextView textView = (TextView) mInflater.inflate(R.layout.indicator_item_textview, mFlowLayout, false);
            textView.setText(INDICATOR_APPEND_TITLES[i]);
            mFlowLayout.addView(textView);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //创建一个新的Fragment
                    mOtherFragment = new OtherFragment(textView.getText().toString());
                    mFragmentList.add(mOtherFragment);
                    mAdapter.notifyDataSetChanged();
                    mTabPageIndicator.setViewPager(mViewPager, mFragmentList.size() - 1);

                    mIndicatorPopupWindow.dismiss();
                }
            });
        }
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

                mArrowButton.setBackgroundResource(R.drawable.arrow_up);
            }
        });

        mIndicatorPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mArrowButton.setBackgroundResource(R.drawable.arrow_down);
            }
        });
    }
}
