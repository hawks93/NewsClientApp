package com.hawks93.newsclientapp.base.impl;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.hawks93.newsclientapp.base.BasePager;

/**
 * 作者hawks93
 * 版本1.0
 * 创建时间 2015/11/24 9:55
 */
public class SmartServerPager extends BasePager {

    public SmartServerPager(Activity activity) {

        super(activity);
    }
    @Override
    public void initData() {
//        ib_menu.setVisibility(View.VISIBLE);//显示菜单按钮
        setSlidingMenuEnable(true);//可划出侧栏
        tv_title.setText("生活");
        TextView tv_content=new TextView(mActivity);
        tv_content.setText("智慧服务");
        tv_content.setTextSize(10);
        fl_content.addView(tv_content);

    }
}
