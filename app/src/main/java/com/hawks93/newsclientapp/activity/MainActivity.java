package com.hawks93.newsclientapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.hawks93.newsclientapp.R;
import com.hawks93.newsclientapp.fragment.ContentFragment;
import com.hawks93.newsclientapp.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.lidroid.xutils.HttpUtils;

/**
 * 作者hawks93
 * 版本1.0
 * 创建时间
 */
public class MainActivity extends SlidingFragmentActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_content);
        setBehindContentView(R.layout.activity_main_left_menu);
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setBehindOffset(200);
        initFragment();

    }

    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fl_left_menu, new LeftMenuFragment(), "FRAGMENT_LEFT_MENU");
        fragmentTransaction.replace(R.id.fl_content, new ContentFragment(), "FRAGMENT_CONTENT");
        fragmentTransaction.commit();



    }
//获取左侧边栏fragment
    public LeftMenuFragment getLeftMenuFragment() {
        FragmentManager fm = getSupportFragmentManager();
        LeftMenuFragment leftmenufragment = (LeftMenuFragment) fm.findFragmentByTag("FRAGMENT_LEFT_MENU");
        return leftmenufragment;

    }//获取主页fragment
    public ContentFragment getContentFragment() {
        FragmentManager fm = getSupportFragmentManager();
        ContentFragment contentfragment = (ContentFragment) fm.findFragmentByTag("FRAGMENT_CONTENT");
        return contentfragment;

    }
}
