package net.example.paul.rapapp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import net.example.paul.rapapp.R;
import net.example.paul.rapapp.domain.NewsInfo;
import net.example.paul.rapapp.utils.Constants;
import net.example.paul.rapapp.utils.Logger;
import net.example.paul.rapapp.utils.OKManager;

import java.io.IOException;
import java.util.List;

/**
 * Created by 牛谱乐
 * Timer 2016/12/28 11:36
 * E-mail niupuyue@togogo.net
 * 个人设置页面
 */
public class SettingActivity extends Activity{

    private ImageView setting_back;
    private RelativeLayout cache_ll,update_ll,service_ll,check_ll,reback_ll;
    private Button loginout_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        setting_back = (ImageView) findViewById(R.id.setting_back);

        setting_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cache_ll = (RelativeLayout) findViewById(R.id.cache_ll);
        update_ll = (RelativeLayout) findViewById(R.id.update_ll);
        service_ll = (RelativeLayout) findViewById(R.id.service_ll);
        check_ll = (RelativeLayout) findViewById(R.id.check_ll);
        reback_ll = (RelativeLayout) findViewById(R.id.reback_ll);

        loginout_btn = (Button) findViewById(R.id.loginout_btn);

    }
}
