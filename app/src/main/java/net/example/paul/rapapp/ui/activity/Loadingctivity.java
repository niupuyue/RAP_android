package net.example.paul.rapapp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import com.baidu.mapapi.SDKInitializer;

import net.example.paul.rapapp.R;
import net.example.paul.rapapp.domain.NewsInfo;
import net.example.paul.rapapp.model.interfaces.GetNewsCallbackBYString;
import net.example.paul.rapapp.utils.Constants;
import net.example.paul.rapapp.utils.Logger;
import net.example.paul.rapapp.utils.OKManager;

import java.io.IOException;
import java.util.List;

/**
 * Created by 牛谱乐
 * Timer 2016/12/28 11:34
 * E-mail niupuyue@togogo.net
 * 引导页面
 */
public class Loadingctivity extends Activity{
    private SharedPreferences preferences = null;
    private SharedPreferences.Editor editor = null;
    private Handler handler = null;
    private int LAST_TIME = 1000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_load);

        preferences = getSharedPreferences("isFirstLoad",MODE_PRIVATE);
        handler = new Handler(getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(preferences.getBoolean("isFirstLoad",true)){
                    editor = preferences.edit();
                    editor.putBoolean("isFirstLoad",false);
                    editor.commit();
                    Intent intent = new Intent(Loadingctivity.this,WelcomeActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(Loadingctivity.this,MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        },LAST_TIME);

    }
}
