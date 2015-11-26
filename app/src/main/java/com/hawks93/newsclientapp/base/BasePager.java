package com.hawks93.newsclientapp.base;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hawks93.newsclientapp.R;
import com.hawks93.newsclientapp.activity.MainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * 子页面下的5个基类
 * 作者hawks93
 * 版本1.0
 * 创建时间 2015/11/24 9:02
 */
public class BasePager {
    protected Activity mActivity;
    protected ImageButton ib_menu;
    protected TextView tv_title;
    protected FrameLayout fl_content;//protected只能在子类中被引用
    public View mView;//必须为public才能被在其他类中被引用

    public BasePager(Activity activity) {
        mActivity=activity;
        mView = initView();

    }
    //5个界面的共性界面
    protected View initView(){
        View view= View.inflate(mActivity, R.layout.pager_base, null);
        ib_menu = (ImageButton) view.findViewById(R.id.ib_menu);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        fl_content = (FrameLayout) view.findViewById(R.id.fl_content);
        ib_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSlidingMenu();
            }
        });
        return view;


    }
    /*
切换SlidingMenu的状态
 */
    private void toggleSlidingMenu() {
        MainActivity mainUi= (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUi.getSlidingMenu();
        slidingMenu.toggle();
    }

    public  void initData(){

    }
    public void  setSlidingMenuEnable(boolean enable){
        MainActivity slidingActivity=(MainActivity)mActivity;
        SlidingMenu slidingMenu = slidingActivity.getSlidingMenu();
        if (enable){
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        }else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }
}
