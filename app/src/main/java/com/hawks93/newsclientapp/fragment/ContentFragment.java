package com.hawks93.newsclientapp.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.hawks93.newsclientapp.R;
import com.hawks93.newsclientapp.base.BasePager;
import com.hawks93.newsclientapp.base.impl.GovAffairsPager;
import com.hawks93.newsclientapp.base.impl.HomePager;
import com.hawks93.newsclientapp.base.impl.NewsCenterPager;
import com.hawks93.newsclientapp.base.impl.SettingPager;
import com.hawks93.newsclientapp.base.impl.SmartServerPager;
import com.hawks93.newsclientapp.view.NoSrollViewPager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * 内容界面碎片
 */
public class ContentFragment extends BaseFragment {
    @ViewInject(R.id.vp_content)
    private NoSrollViewPager vp_content;
    @ViewInject(R.id.rg_tab)
    private RadioGroup rg_tab;
    private ArrayList<BasePager> mPagers;

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        ViewUtils.inject(this, view);//注入界面
        return view;
    }

    @Override
    public void initData() {
//        super.initData();
        //初始化5个pager
        mPagers = new ArrayList<BasePager>();
        mPagers.add(new HomePager(mActivity));
        mPagers.add(new NewsCenterPager(mActivity));
        mPagers.add(new SmartServerPager(mActivity));
        mPagers.add(new GovAffairsPager(mActivity));
        mPagers.add(new SettingPager(mActivity));
        vp_content.setAdapter(new ContentAdapter());
        rg_tab.check(R.id.rb_home);//默认首页勾选
        mPagers.get(0).initData();// 初始化首页数据
        //监听RadioGroup的选择事件
        rg_tab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        // mViewPager.setCurrentItem(0);// 设置当前页面
                        vp_content.setCurrentItem(0, false);
                        break;
                    case R.id.rb_news:
                        vp_content.setCurrentItem(1, false);//去掉切换页面的动画
                        break;
                    case R.id.rb_smart:
                        vp_content.setCurrentItem(2, false);
                        break;
                    case R.id.rb_gov:
                        vp_content.setCurrentItem(3, false);
                        break;
                    case R.id.rb_setting:
                        vp_content.setCurrentItem(4, false);
                        break;

                }
            }
        });


        vp_content.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                // 获取当前被选中的页面, 初始化该页面数据
                mPagers.get(i).initData();
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });


    }
/*
获取新闻中心页面
 */
public NewsCenterPager getNewsCenterPager() {
    return (NewsCenterPager) mPagers.get(1);
}

    private class ContentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager pager = mPagers.get(position);
// pager.initData();初始化数据.... 不要放在此处初始化数据, 否则会预加载下一个页面,导致刚开始的首页可拉出侧栏
            container.addView(pager.mView);

            return pager.mView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
