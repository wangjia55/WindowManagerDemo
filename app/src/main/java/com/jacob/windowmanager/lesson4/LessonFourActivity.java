package com.jacob.windowmanager.lesson4;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.jacob.windowmanager.R;


public class LessonFourActivity extends FragmentActivity
        implements MyScrollView.OnScrollerListener {

    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;

    private View mLayoutBuy;
    private MyScrollView myScrollView;

    private View mPopupView;

    private int mLayoutBuyTop;
    private int mLayoutBuyHeight;

    private int mScrollViewTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_four);

        mLayoutBuy = findViewById(R.id.layout_buy);

        myScrollView = (MyScrollView) findViewById(R.id.scroll_view);
        myScrollView.setOnScrollerListener(this);

    }

    private void initWindowManager() {
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams();

        //窗口大小，默认全屏
        mLayoutParams.height =WindowManager.LayoutParams.MATCH_PARENT;
        mLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        mLayoutParams.format = PixelFormat.TRANSPARENT;
//        mLayoutParams.dimAmount=0.8f;
        //对整个窗口实施动画
        mLayoutParams.windowAnimations = 0;

        //x.y都是相对整个屏幕的坐标
        mLayoutParams.x = 0;
        mLayoutParams.y = mScrollViewTop;
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            mLayoutBuyTop = mLayoutBuy.getTop();
            mLayoutBuyHeight = mLayoutBuy.getHeight();
            mScrollViewTop = myScrollView.getTop();
            Log.e("TAG", "mScrollViewTop:" + mScrollViewTop);
        }
    }

    @Override
    public void onScroll(float scrollY) {
        Log.e("TAG", "scrollY:" + scrollY);

        if (scrollY >= mLayoutBuyTop) {
            if (mPopupView == null) {
                showSuspend();
            }
        } else if (scrollY <= mLayoutBuyTop + mLayoutBuyHeight) {
            if (myScrollView != null) {
                removeSuspend();
            }
        }
    }

    private void removeSuspend() {
        if (mWindowManager != null && mPopupView != null) {
            mWindowManager.removeView(mPopupView);
        }
        mPopupView = null;
    }

    private void showSuspend() {
        if (mPopupView == null) {
            mPopupView = LayoutInflater.from(this).inflate(R.layout.layout_buy, null);
            if (mWindowManager == null) {
                initWindowManager();
            }
        }
        mWindowManager.addView(mPopupView, mLayoutParams);
    }
}
