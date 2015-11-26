package com.hawks93.newsclientapp.base.menudetail;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.hawks93.newsclientapp.base.BaseMenuDetailPager;

/**
 * 作者hawks93
 * 版本1.0
 * 创建时间 2015/11/25 20:36
 */
public class InteractMenuDetailPager extends BaseMenuDetailPager{
    public InteractMenuDetailPager(Activity activity) {
        super(activity);
    }

    @Override
    protected View initView() {

            TextView tv_content=new TextView(mActivity);
            tv_content.setText("菜单详情-互动");//设置内容
            tv_content.setTextColor(Color.RED);
            tv_content.setTextSize(25);
            tv_content.setGravity(Gravity.CENTER);
            return tv_content;

    }
}
