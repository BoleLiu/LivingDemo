package com.bignerdranch.android.boboyo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.bignerdranch.android.boboyo.R;
import com.bignerdranch.android.boboyo.adapter.LivingPagerAdapter;
import com.bignerdranch.android.boboyo.fragment.LivingFragment;

import java.util.ArrayList;

import me.majiajie.pagerbottomtabstrip.MaterialMode;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;


public class LiveActivity extends FragmentActivity {
    private static final String TAG = "LiveActivity";
    private String mTest = "test";
    private PagerTabStrip mPagerTabStrip;
    private PageBottomTabLayout mPageBottomTabLayout;
    private NavigationController mNavigationController;
    private ViewPager mLivingViewPager;
    private ArrayList<String> mTitleList;
    private ArrayList<Fragment> mFragmentList;
    private FragmentManager mFragmentManager;

    private static final String[] DEFAULT_PLAYBACK_DOMAIN_ARRAY = {
            "pili-live-rtmp.pilitest.qiniucdn.com",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_living_layout);
        findById();
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void findById() {
        mPagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);
        mLivingViewPager = (ViewPager) findViewById(R.id.view_pager);
        mPageBottomTabLayout = (PageBottomTabLayout) findViewById(R.id.bottom_tab);
    }

    private void init() {
//        mFragmentManager = getSupportFragmentManager();
        mPagerTabStrip.setTabIndicatorColor(Color.BLUE);

        mFragmentList = new ArrayList<Fragment>();
        Fragment fragment1 = new LivingFragment();
        Fragment fragment2 = new LivingFragment();
        Fragment fragment3 = new LivingFragment();
        mFragmentList.add(fragment1);
        mFragmentList.add(fragment2);
        mFragmentList.add(fragment3);
        mTitleList = new ArrayList<String>();
        mTitleList.add("关注");
        mTitleList.add("热门");
        mTitleList.add("附近");
        mLivingViewPager.setAdapter(new LivingPagerAdapter(getSupportFragmentManager(), mFragmentList, mTitleList));
        mLivingViewPager.setCurrentItem(0);
//        mPageBottomTabLayout.setupWithViewPager(mLivingViewPager);
        mNavigationController = mPageBottomTabLayout.material().setMode(MaterialMode.HIDE_TEXT)
                .addItem(R.mipmap.ic_visibility_white_48dp, null)
                .addItem(R.mipmap.ic_video_call_white_48dp, null)
                .addItem(R.mipmap.ic_account_circle_white_48dp, null)
                .build();
        mNavigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                switch (index) {
                    case 0:
                        break;
                    case 1:

                        break;
                    case 2:
                        Intent intent = new Intent(LiveActivity.this, AboutMeActivity.class);
                        startActivity(intent);
                        break;
                }
            }

            @Override
            public void onRepeat(int index) {

            }
        });
    }

}
