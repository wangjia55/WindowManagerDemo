package com.jacob.windowmanager.lesson2;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.jacob.windowmanager.R;


public class LessonTwoActivity extends FragmentActivity {
    private WindowManager mWindowManager;
    private ImageView mImageView;

    WindowManager.LayoutParams mLayoutParams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.height = dp2px(100);
        mLayoutParams.width = dp2px(100);
//        mLayoutParams.dimAmount = 0.8f;

        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        mLayoutParams.format = PixelFormat.OPAQUE;
        //对整个窗口实施动画
        mLayoutParams.windowAnimations = R.style.window_anim;

        mImageView =new ImageView(this);
        mImageView.setPadding(0,0,0,0);
        mImageView.setImageResource(R.drawable.pic7);

        mWindowManager.addView(mImageView, mLayoutParams);
        mImageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mWindowManager.removeView(mImageView);
            }
        },3000);
    }


    private int dp2px(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getResources().getDisplayMetrics());
    }
}
