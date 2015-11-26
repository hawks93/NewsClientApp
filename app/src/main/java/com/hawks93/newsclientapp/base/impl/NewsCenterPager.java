package com.hawks93.newsclientapp.base.impl;

import android.app.Activity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hawks93.newsclientapp.activity.MainActivity;
import com.hawks93.newsclientapp.base.BaseMenuDetailPager;
import com.hawks93.newsclientapp.base.BasePager;
import com.hawks93.newsclientapp.base.menudetail.InteractMenuDetailPager;
import com.hawks93.newsclientapp.base.menudetail.NewsMenuDetailPager;
import com.hawks93.newsclientapp.base.menudetail.PhotoMenuDetailPager;
import com.hawks93.newsclientapp.base.menudetail.TopicMenuDetailPager;
import com.hawks93.newsclientapp.domain.NewsData;
import com.hawks93.newsclientapp.fragment.LeftMenuFragment;
import com.hawks93.newsclientapp.global.GlobalContants;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;

/**
 * 作者hawks93
 * 版本1.0
 * 创建时间 2015/11/24 9:55
 */
public class NewsCenterPager extends BasePager {
private ArrayList<BaseMenuDetailPager> mMenuDetailPager;// 4个菜单详情页的集合
    private NewsData mNewsData;

    public NewsCenterPager(Activity activity) {
        super(activity);
    }
    @Override
    public void initData() {
//        ib_menu.setVisibility(View.VISIBLE);//显示菜单按钮
        setSlidingMenuEnable(true);//可划出侧栏
        tv_title.setText("新闻");
        getDataFromServer();

    }
/*
从服务器获取数据
 */
    private void getDataFromServer() {
        HttpUtils httpUtils = new HttpUtils();
        //使用xutils发送请求
        httpUtils.send(HttpRequest.HttpMethod.GET, GlobalContants.CATEGORIES_URL, new RequestCallBack<String>() {
            @Override//访问成功
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                System.out.println("返回结果：" + result);
                parseData(result);
            }

            @Override//访问失败
            public void onFailure(HttpException e, String s) {
                Toast.makeText(mActivity, s, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }
/*
解析网络数据
 */
    private void parseData(String result) {
        Gson gson = new Gson();
        mNewsData = gson.fromJson(result, NewsData.class);
        System.out.println("解析数据："+ mNewsData);

        //刷新侧边栏的数据
        MainActivity mainUI= (MainActivity) mActivity;
        LeftMenuFragment leftMenuFragment = mainUI.getLeftMenuFragment();
        leftMenuFragment.setMenuData(mNewsData);

        // 准备4个菜单详情页
        mMenuDetailPager = new ArrayList<BaseMenuDetailPager>();
        mMenuDetailPager.add(new NewsMenuDetailPager(mActivity,mNewsData.data.get(0).children));
        mMenuDetailPager.add(new TopicMenuDetailPager(mActivity));
        mMenuDetailPager.add(new PhotoMenuDetailPager(mActivity));
        mMenuDetailPager.add(new InteractMenuDetailPager(mActivity));

        setCurrentMenuDetailPager(0);// 设置菜单详情页-新闻为默认当前页


    }
    /*
     * 设置当前菜单详情页
     */
    public void setCurrentMenuDetailPager(int position) {
        BaseMenuDetailPager baseMenuDetailPager = mMenuDetailPager.get(position);//获取当前要显示的菜单详情页
        fl_content.removeAllViews();//清除之前的布局
        fl_content.addView(baseMenuDetailPager.mRootView);//将菜单详情页的布局设置给帧布局
        NewsData.NewsMenuData menuData = mNewsData.data.get(position);
        tv_title.setText(menuData.title);

        baseMenuDetailPager.initData();//初始化当前页面的数据



    }
}
