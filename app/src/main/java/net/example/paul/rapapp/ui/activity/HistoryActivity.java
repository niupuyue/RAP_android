package net.example.paul.rapapp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import net.example.paul.rapapp.R;

/**
 * Created by 牛谱乐
 * Timer 2016/12/28 11:37
 * E-mail niupuyue@togogo.net
 * 浏览足迹页面
 */
public class HistoryActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_history);
    }
}
