package com.bignerdranch.android.boboyo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujingbo on 17/3/8.
 */

public class LivingPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> fragmentList;
    private List<String> titleList;

    public LivingPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentList, ArrayList<String> titleList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("liujingbo", "" + position);
        return (fragmentList == null || fragmentList.isEmpty()) ? null : fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return (fragmentList == null) ? 0 :fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.size() > 0 ? titleList.get(position ): "";
    }
}
