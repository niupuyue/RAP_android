package net.example.paul.rapapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by 牛谱乐
 * Timer 2017/2/13 22:30
 * E-mail niupuyue@togogo.net
 */
public class ListViewForScrollView extends ListView{
    public ListViewForScrollView(Context context) {
        super(context);
    }
    public ListViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public ListViewForScrollView(Context context, AttributeSet attrs,
                                 int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * =：赋值
         * ==：判断；相等
         * ===：绝对等于
         *
         * number 1  == "1"  true
         *        1 === "1"   false   java 比较内存地址
         *
         */

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
