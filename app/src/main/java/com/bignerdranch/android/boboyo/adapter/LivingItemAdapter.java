package com.bignerdranch.android.boboyo.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.boboyo.R;
import com.bignerdranch.android.boboyo.bean.LivingItemBean;

import java.util.List;

/**
 * Created by liujingbo on 17/3/8.
 */

public class LivingItemAdapter extends BaseAdapter{

    private static final String TAG = "LivingItemAdapter";

    private List<LivingItemBean> mList;
    private LivingItemBean mLivingItemBean;
    private Activity activity;


    public LivingItemAdapter(Activity activity, List<LivingItemBean> dataList) {
        this.activity = activity;
        mList = dataList;
    }

    @Override
    public int getCount() {
        return (mList == null || mList.isEmpty()) ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return (mList == null || mList.isEmpty()) ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        mLivingItemBean = mList.get(position);
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.living_item_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.iconImage = (ImageView) convertView.findViewById(R.id.living_icon);
            viewHolder.titleText = (TextView) convertView.findViewById(R.id.living_title);
            viewHolder.previewImage = (ImageView) convertView.findViewById(R.id.living_preview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.iconImage.setImageResource(mLivingItemBean.mLivingIconid);
        viewHolder.titleText.setText(mLivingItemBean.mTitle);
        viewHolder.previewImage.setImageResource(mLivingItemBean.mPreviewImageid);
        return convertView;
    }

    private class ViewHolder {
        private ImageView iconImage;
        private TextView titleText;
        private ImageView previewImage;
    }
}
