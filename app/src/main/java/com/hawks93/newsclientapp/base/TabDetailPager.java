package com.hawks93.newsclientapp.base;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.hawks93.newsclientapp.domain.NewsData;

/**
 * 作者hawks93
 * 版本1.0
 * 创建时间 2015/11/26 8:47
 */
public class TabDetailPager extends  BaseMenuDetailPager {

    private final NewsData.NewsTabData mTabDatas;
    private TextView tv_content;

    public TabDetailPager(Activity activity,NewsData.NewsTabData newsTabData) {
        super(activity);
        mTabDatas = newsTabData;
    }

    @Override
    protected View initView() {
        tv_content = new TextView(mActivity);
        tv_content.setText("页签详情页");
        tv_content.setTextColor(Color.RED);
        tv_content.setTextSize(25);
        tv_content.setGravity(Gravity.CENTER);
        return tv_content;
    }

    @Override
    public void initData() {
        tv_content.setText(mTabDatas.title);

    }
}
