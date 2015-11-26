package com.hawks93.newsclientapp.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hawks93.newsclientapp.R;
import com.hawks93.newsclientapp.utils.PrefUtil;

public class GuideActivity extends Activity {
    int[] pics = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
    private ViewPager vp_guide;
    private View view_red_dot;
    private LinearLayout ll_gray_dots;
    private int mDistance;
    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();
        initData();
        initListener();


    }


    private void initView() {
        setContentView(R.layout.activity_guide);
        vp_guide = (ViewPager) findViewById(R.id.vp_guide);
        ll_gray_dots = (LinearLayout) findViewById(R.id.ll_gray_dots);
        view_red_dot = findViewById(R.id.view_red_dot);
        btn_start = (Button) findViewById(R.id.btn_start);

        //初始化灰色小点
        for (int i = 0; i <pics.length; i++) {
            View grayDot=new View(this);
            grayDot.setBackgroundResource(R.drawable.shape_gray_dot);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(10,10);
            if (i>0){
            layoutParams.leftMargin=5;
            }
            grayDot.setLayoutParams(layoutParams);
            ll_gray_dots.addView(grayDot);


        }
    }

    private void initData() {
        vp_guide.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return pics.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }

            @Override//因为ViewPager只缓存三个对象，当尾部进来一个新对象，头部的老对象就要被销毁
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override//创建下一个对象
            public Object instantiateItem(ViewGroup container, int position) {

                ImageView child = new ImageView(GuideActivity.this);
                child.setBackgroundResource(pics[position]);
                container.addView(child);
                return child;

            }
        });


    }

    private void initListener() {
        //获取两个灰点之间的距离
        ll_gray_dots.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                mDistance = ll_gray_dots.getChildAt(1).getLeft()-ll_gray_dots.getChildAt(0).getLeft();
                ll_gray_dots.getViewTreeObserver().removeOnGlobalLayoutListener(this);//防止重复观察
            }
        });
        //实现灰色小点你跟随页面移动
        vp_guide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // System.out.println("当前位置:" + position + ";百分比:" + positionOffset
                // + ";移动距离:" + positionOffsetPixels);

                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(10, 10);
                params.leftMargin = (int) (mDistance*positionOffset+position*mDistance);
                view_red_dot.setLayoutParams(params);


            }

            @Override
            public void onPageSelected(int position) {
                if (position == pics.length - 1) {
                    btn_start.setVisibility(View.VISIBLE);

                } else {
                    btn_start.setVisibility(View.INVISIBLE);
                }

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(GuideActivity.this,MainActivity.class);
                startActivity(i);
                PrefUtil.setBoolean(GuideActivity.this, "guide_completed", true);
                finish();
            }
        });
    }

}
