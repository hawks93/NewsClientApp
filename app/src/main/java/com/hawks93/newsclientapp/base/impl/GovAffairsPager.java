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
public class GovAffairsPager extends BasePager {

    public GovAffairsPager(Activity activity) {
        super(activity);
    }


    @Override
    public void initData() {
//        ib_menu.setVisibility(View.VISIBLE);//菜单按钮
        setSlidingMenuEnable(true);//可划出侧栏
        tv_title.setText("人口管理");
        TextView tv_content = new TextView(mActivity);
        tv_content.setText("政务");
        tv_content.setTextSize(10);
        fl_content.addView(tv_content);

    }
}
