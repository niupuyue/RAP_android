package net.example.paul.rapapp.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者: paul niu
 * 时间:2016/9/8 16:49
 * 邮箱:niupuyue@togogo.net
 */
public class T {

    public T() {
        //无参构造
    }

    public static void showShort(Context context, CharSequence charSequence) {
        Toast.makeText(context, charSequence, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(Context context, CharSequence charSequence, int time) {
        Toast.makeText(context, charSequence, time).show();
    }

    public static void showShort(Context context, int resId, int time) {
        Toast.makeText(context, resId, time).show();
    }

    public static void showLong(Context context, CharSequence charSequence) {
        Toast.makeText(context, charSequence, Toast.LENGTH_LONG).show();
    }

    public static void showLong(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

    public static void showLong(Context context, CharSequence charSequence, int time) {
        Toast.makeText(context, charSequence, time).show();
    }

    public static void showLong(Context context, int resId, int time) {
        Toast.makeText(context, resId, time).show();
    }


}
