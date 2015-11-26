package com.hawks93.newsclientapp.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.hawks93.newsclientapp.base.BasePager;

/**
 * 作者hawks93
 * 版本1.0
 * 创建时间 2015/11/24 9:55
 */
public class HomePager extends BasePager {

    public HomePager(Activity activity) {
        super(activity);
    }
    @Override
    public void initData() {
        ib_menu.setVisibility(View.INVISIBLE);//隐藏菜单按钮
        setSlidingMenuEnable(false);//取消划出侧栏
        tv_title.setText("智慧北京");//设置标题
        TextView tv_content=new TextView(mActivity);//设置内容
        tv_content.setText("首页");
        tv_content.setTextColor(Color.RED);
        tv_content.setTextSize(25);
        tv_content.setGravity(Gravity.CENTER);
        fl_content.addView(tv_content);

    }
}
