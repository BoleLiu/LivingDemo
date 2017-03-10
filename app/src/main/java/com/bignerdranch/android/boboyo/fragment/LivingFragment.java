package com.bignerdranch.android.boboyo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
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

public class LivingFragment extends ListFragment {
    private static final String TAG = "LivingFragment";

    private ListView mLivingListView;
    private ArrayList<LivingItemBean> mLivingList;
    private String flag;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        flag = this.getArguments().get("flag").toString();
        Log.d("liujingbo", flag + " is onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("liujingbo", flag + " is onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_living_layout, container, false);
        if(view != null) {
            Log.d("liujingbo", flag + " is onCreateView");
            return view;
        }
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("liujingbo", flag + " is onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("liujingbo", flag + " is onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("liujingbo", flag + " is onResume");
        mLivingList = new ArrayList<LivingItemBean>();
        LivingItemBean livingItemBean= new LivingItemBean(R.drawable.welcome, R.drawable.welcome, flag);
        for(int i = 0; i < 10; i++) {
            mLivingList.add(livingItemBean);
        }
        mLivingListView = (ListView) getActivity().findViewById(android.R.id.list);
        mLivingListView.setAdapter(new LivingItemAdapter(getActivity(), mLivingList, flag));
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("liujingbo", flag + " is onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("liujingbo", flag + " is onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("liujingbo", flag + " is onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("liujingbo", flag + " is onDetach");
    }
}
