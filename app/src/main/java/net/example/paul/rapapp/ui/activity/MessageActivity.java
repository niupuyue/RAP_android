package net.example.paul.rapapp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import net.example.paul.rapapp.R;

/**
 * Created by 牛谱乐
 * Timer 2016/12/28 11:38
 * E-mail niupuyue@togogo.net
 * 这个页面主要是一些推送过来的消息内容
 */
public class MessageActivity extends Activity{

    private ImageView message_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        message_back = (ImageView) findViewById(R.id.message_back);
        message_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
