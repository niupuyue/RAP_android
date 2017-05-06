package net.example.paul.rapapp.widget;

import android.view.View;
import android.view.animation.Animation;

/**
 * Created by 牛谱乐
 * Timer 2017/2/11 13:59
 * E-mail niupuyue@togogo.net
 */
public interface IPullDownElastic {


    public final static int RELEASE_To_REFRESH = 0;
    public final static int PULL_To_REFRESH = 1;
    public final static int REFRESHING = 2;
    public final static int DONE = 3;

    public View getElasticLayout();

    public int getElasticHeight();

    public void showArrow(int visibility);

    public void startAnimation(Animation animation);

    public void clearAnimation();

    public void showProgressBar(int visibility);

    public void setTips(String tips);

    public void showLastUpdate(int visibility);

    public void setLastUpdateText(String text);

    public void showPacman(int vasibility);

    /**
     * 可以不用实现此方法，PullDownScrollView会处理ElasticLayout布局中的状态
     * 如果需要特殊处理，可以实现此方法进行处理
     *
     * @param state  @see RELEASE_To_REFRESH
     * @param isBack 是否是松开回退
     */
    public void changeElasticState(int state, boolean isBack);



}
