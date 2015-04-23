package com.jacob.windowmanager.lesson1;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.jacob.windowmanager.R;

import java.util.Random;


public class LessonOneActivity extends FragmentActivity {


    private WindowManager mWindowManager;
    private ImageView mImageView;

    WindowManager.LayoutParams mLayoutParams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_one);

        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        mLayoutParams.format = PixelFormat.OPAQUE;
        mLayoutParams.alpha = 0.5f;
        mLayoutParams.windowAnimations = 0;

        mImageView =new ImageView(this);
        mImageView.setPadding(0,0,0,0);
        mImageView.setImageResource(R.drawable.pintu);

        mWindowManager.addView(mImageView, mLayoutParams);

    }


    public void show(View view){
        mImageView.setPadding(0,0,0,0);
        mWindowManager.addView(mImageView, mLayoutParams);
    }

    public void remove(View view){
        mWindowManager.removeView(mImageView);
    }
}
