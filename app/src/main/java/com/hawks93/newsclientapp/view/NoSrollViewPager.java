package com.hawks93.newsclientapp.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 作者hawks93
 * 版本1.0
 * 创建时间 2015/11/24 20:41
 */
public class NoSrollViewPager extends ViewPager{
    public NoSrollViewPager(Context context) {
        super(context);
    }

    public NoSrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    // 表示事件是否拦截, 返回false表示不拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
