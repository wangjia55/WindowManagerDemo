package com.jacob.windowmanager.lesson3;

import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jacob.windowmanager.R;


public class LessonThreeActivity extends FragmentActivity {
    private TextView mTextView;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        initWindowManager();

        if (mTextView!= null){
            mWindowManager.removeView(mTextView);
        }else{
            //准备实现复杂的图文混排操作
            mTextView = new TextView(getApplicationContext());
            SpannableStringBuilder sb = new SpannableStringBuilder();
            sb.append("I love ");
            int start = sb.length();
            sb.append("Android");
            int end = sb.length();
            //将android替换成图片
            ImageSpan imageSpan = new ImageSpan(this,R.drawable.ic_launcher);
            sb.setSpan(imageSpan,start,end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            mTextView.setText(sb);
            mTextView.setBackgroundColor(Color.BLUE);

            mWindowManager.addView(mTextView, mLayoutParams);

            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"onclick",Toast.LENGTH_SHORT).show();

                }
            });

            mTextView.setOnTouchListener(new View.OnTouchListener() {
                float lastX;
                float lastY;
                long start;
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    float rawX = event.getRawX();
                    float rawY = event.getRawY();

                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                             lastX = rawX;
                             lastY = rawY;
                            start = System.currentTimeMillis();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            float deltaX = rawX-lastX;
                            float deltaY = rawY-lastY;

                            mLayoutParams.x+=deltaX;
                            mLayoutParams.y+= deltaY;
                            lastX = rawX;
                            lastY = rawY;

                            mWindowManager.updateViewLayout(mTextView,mLayoutParams);
                            break;
                        case MotionEvent.ACTION_UP:
                            long duration = System.currentTimeMillis()-start;
                            if (duration<300){
                                mTextView.performClick();
                            }
                            break;
                    }
                    return true;
                }
            });
        }
    }

    private void initWindowManager() {
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams();

        //窗口大小，默认全屏
        mLayoutParams.height = dp2px(100);
        mLayoutParams.width = dp2px(100);
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        mLayoutParams.format = PixelFormat.TRANSPARENT;
//        mLayoutParams.dimAmount=0.8f;
        //对整个窗口实施动画
        mLayoutParams.windowAnimations = 0;

        //x.y都是相对整个屏幕的坐标
        mLayoutParams.x = 50;
        mLayoutParams.y = 50;
    }


    private int dp2px(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getResources().getDisplayMetrics());
    }
}
