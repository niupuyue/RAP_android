package net.example.paul.rapapp.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 牛谱乐
 * Timer 2017/1/4 16:15
 * E-mail niupuyue@togogo.net
 */
public class MyMainViewPager extends ViewPager{

    private boolean isSlipping = true ;//是否可以滑动

    public MyMainViewPager(Context context) {
        super(context);
    }

    public MyMainViewPager(Context context,AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {

        if(!isSlipping){
            return false;
        }
        return super.onInterceptTouchEvent(arg0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (!isSlipping) {
            return false;
        }
        return super.onTouchEvent(arg0);
    }
    /**
     *@Title: setSlipping
     *@Description: TODO设置ViewPager是否可滑动
     *@param isSlipping
     */
    public void setSlipping(boolean isSlipping) {
        this.isSlipping = isSlipping;
    }

}
