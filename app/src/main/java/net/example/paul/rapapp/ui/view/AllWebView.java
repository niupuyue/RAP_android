package net.example.paul.rapapp.ui.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import net.example.paul.rapapp.R;
import net.example.paul.rapapp.domain.NewsInfo;

/**
 * Created by 牛谱乐
 * Timer 2017/2/13 22:37
 * E-mail niupuyue@togogo.net
 */
public class AllWebView extends Activity {

    private WebView webView;
    private NewsInfo newsInfo;
    private ImageView webview_back;
    private TextView webview_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_webview);
        webview_back = (ImageView) findViewById(R.id.webview_back);
        webview_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webview_title = (TextView) findViewById(R.id.webview_title);
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        newsInfo = (NewsInfo) bundle.getSerializable("newsInfo");
        webview_title.setText(newsInfo.getTitle());
        init();
    }

    private void init(){
        webView = (WebView) findViewById(R.id.webview);
        //WebView加载web资源
        webView.loadUrl(newsInfo.getUrl());
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }
}
