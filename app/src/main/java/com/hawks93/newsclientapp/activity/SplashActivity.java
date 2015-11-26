package com.hawks93.newsclientapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.hawks93.newsclientapp.R;
import com.hawks93.newsclientapp.utils.PrefUtil;

public class SplashActivity extends Activity {

    private RelativeLayout rl_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        rl_root = (RelativeLayout) findViewById(R.id.rl_root);

        startAnim();
    }

    /*
    开启闪屏动画
     */
    private void startAnim() {
        RotateAnimation rotateAnim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(2000);
        rotateAnim.setFillAfter(true);
        ScaleAnimation scaleAnim = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnim.setDuration(1000);
        scaleAnim.setFillAfter(true);
        AlphaAnimation alphaAnim = new AlphaAnimation(0.5f, 1f);
        alphaAnim.setDuration(2000);
        alphaAnim.setFillAfter(true);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(rotateAnim);
        animationSet.addAnimation(scaleAnim);
        animationSet.addAnimation(alphaAnim);
        rl_root.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override//执行完动画后调用
            public void onAnimationEnd(Animation animation) {
                boolean guide_completed = PrefUtil.getBoolean(SplashActivity.this, "guide_completed", false);
                if (guide_completed) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));

                } else {
                    startActivity(new Intent(SplashActivity.this, GuideActivity.class));

                }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
