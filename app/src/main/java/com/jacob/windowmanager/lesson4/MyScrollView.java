package com.jacob.windowmanager.lesson4;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by jacob-wj on 2015/4/24.
 */
public class MyScrollView extends ScrollView{

    private OnScrollerListener mScrollerListener;

    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    private float mLastY;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mScrollerListener != null){
            mLastY  = getScrollY();
            mScrollerListener.onScroll(mLastY);
        }
        return super.onTouchEvent(ev);
    }


    public void setOnScrollerListener(OnScrollerListener listener){
        this.mScrollerListener = listener;
    }

    public interface OnScrollerListener{
        void onScroll(float y);
    }
}
