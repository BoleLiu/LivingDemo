package com.bignerdranch.android.boboyo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bignerdranch.android.boboyo.R;
import com.bignerdranch.android.boboyo.adapter.LivingItemAdapter;
import com.bignerdranch.android.boboyo.bean.LivingItemBean;

import java.util.ArrayList;

/**
 * Created by liujingbo on 17/3/8.
 */

public class LivingFragment extends Fragment {
    private static final String TAG = "LivingFragment";

    private ListView mLivingListView;
    private ArrayList<LivingItemBean> mLivingList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_living_layout, container, false);
        if (view != null) {
            mLivingListView = (ListView) view.findViewById(R.id.list_view);
            mLivingList = new ArrayList<LivingItemBean>();
            LivingItemBean livingItemBean = new LivingItemBean(R.drawable.welcome, R.drawable.welcome, "title");
            for (int i = 0; i < 10; i++) {
                mLivingList.add(livingItemBean);
            }
            mLivingListView.setAdapter(new LivingItemAdapter(getActivity(), mLivingList));
            return view;
        }
        return null;
    }

}
