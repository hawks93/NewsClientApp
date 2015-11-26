package com.hawks93.newsclientapp.fragment;


import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hawks93.newsclientapp.R;
import com.hawks93.newsclientapp.activity.MainActivity;
import com.hawks93.newsclientapp.base.impl.NewsCenterPager;
import com.hawks93.newsclientapp.domain.NewsData;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * 左侧滑动菜单碎片
 */
public class LeftMenuFragment extends BaseFragment {
    @ViewInject(R.id.lv_menu)
    private ListView lv_menu;
    private ArrayList<NewsData.NewsMenuData> mMenuDatas;
    private int mCurrentPosition;//当前被点击的菜单项
    private MenuAdapter menuAdapter;

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentPosition = position;
                menuAdapter.notifyDataSetChanged();//每点击一次getview（）调用一次
                setCurrentMenuDetailPager(position);
                toggleSlidingMenu();//隐藏侧边栏


            }
        });
    }
/*
切换SlidingMenu的状态
 */
    private void toggleSlidingMenu() {
        MainActivity mainUi= (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUi.getSlidingMenu();
        slidingMenu.toggle();
    }


    /*
    设置当前菜单详情页
     */
    private void setCurrentMenuDetailPager(int position) {
        MainActivity mainUi= (MainActivity) mActivity;
        ContentFragment contentFragment = mainUi.getContentFragment();
        NewsCenterPager newsCenterPager = contentFragment.getNewsCenterPager();
        newsCenterPager.setCurrentMenuDetailPager(position);//设置当前菜单详情页

    }

    //设置网络数据
    public void setMenuData(NewsData mNewsData) {
        System.out.println("侧边栏拿到数据：" + mNewsData);
        mMenuDatas = mNewsData.data;
        menuAdapter = new MenuAdapter();
        lv_menu.setAdapter(menuAdapter);

    }

    private class MenuAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mMenuDatas.size();
        }

        @Override
        public NewsData.NewsMenuData getItem(int position) {
            return mMenuDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = View.inflate(mActivity, R.layout.list_menu_item, null);
            TextView tv_title = (TextView) v.findViewById(R.id.tv_title);
            NewsData.NewsMenuData newsMenuData = getItem(position);
            tv_title.setText(newsMenuData.title);
            if (mCurrentPosition == position) {// 判断当前绘制的view是否被选中
                tv_title.setEnabled(true);//显示红色
            } else {
                tv_title.setEnabled(false);//显示白色
            }

            return v;
        }
    }
}
