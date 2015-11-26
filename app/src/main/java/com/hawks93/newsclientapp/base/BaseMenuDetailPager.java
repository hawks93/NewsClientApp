package com.hawks93.newsclientapp.base;

import android.app.Activity;
import android.view.View;

/**
 * 作者hawks93
 * 版本1.0
 * 创建时间 2015/11/25 20:20
 */
public abstract class BaseMenuDetailPager {
    protected Activity mActivity;
    public View mRootView;

    public BaseMenuDetailPager(Activity activity) {
        mActivity = activity;
        mRootView = initView();
    }

    protected abstract View initView();
    public void initData(){

    }

}
