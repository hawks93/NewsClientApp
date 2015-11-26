package com.hawks93.newsclientapp.base.menudetail;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hawks93.newsclientapp.R;
import com.hawks93.newsclientapp.base.BaseMenuDetailPager;
import com.hawks93.newsclientapp.base.TabDetailPager;
import com.hawks93.newsclientapp.domain.NewsData;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * 作者hawks93
 * 版本1.0
 * 创建时间 2015/11/25 20:30
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager {
    @ViewInject(R.id.vp_menu_detail)
    private ViewPager vp_menu_detail;
    private ArrayList<TabDetailPager> mPagerList;
    private ArrayList<NewsData.NewsTabData> mNewsTabData;//页签的网络数据

    public NewsMenuDetailPager(Activity activity, ArrayList<NewsData.NewsTabData> children) {
        super(activity);
        mNewsTabData = children;
    }

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.news_menu_detail, null);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        mPagerList = new ArrayList<TabDetailPager>();
        //初始化页签数据
        for (int i = 0; i <mNewsTabData.size(); i++) {
            TabDetailPager tabDetailPager = new TabDetailPager(mActivity,mNewsTabData.get(i));

            mPagerList.add(tabDetailPager);

        }
        vp_menu_detail.setAdapter(new MenuDetailAdapter());

    }

    private class MenuDetailAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mPagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPager tabDetailPager = mPagerList.get(position);
            container.addView(tabDetailPager.mRootView);
            tabDetailPager.initData();
            return tabDetailPager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
