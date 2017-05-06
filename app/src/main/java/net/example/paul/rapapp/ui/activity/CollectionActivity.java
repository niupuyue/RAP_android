package net.example.paul.rapapp.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;

import net.example.paul.rapapp.R;

/**
 * Created by 牛谱乐
 * Timer 2016/12/28 11:37
 * E-mail niupuyue@togogo.net
 * 个人收藏页面
 */
public class CollectionActivity extends FragmentActivity{


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_collection);
    }
}
