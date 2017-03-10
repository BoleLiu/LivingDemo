package com.bignerdranch.android.boboyo.bean;

/**
 * Created by liujingbo on 17/3/8.
 */

public class LivingItemBean {

    public int mLivingIconid;
    public String mTitle;
    public int mPreviewImageid;

    public LivingItemBean(int iconImageId, int previewImageId, String title){
        mLivingIconid = iconImageId;
        mPreviewImageid = previewImageId;
        mTitle = title;
    }
}
