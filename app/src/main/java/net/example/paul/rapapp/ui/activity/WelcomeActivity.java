package net.example.paul.rapapp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import net.example.paul.rapapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 牛谱乐
 * Timer 2016/12/28 11:39
 * E-mail niupuyue@togogo.net
 * 第一次登陆欢迎页面
 */
public class WelcomeActivity extends Activity{
    private ViewPager welcome_viewpage;
    private int[] images = new int[]{R.mipmap.welcome_1,R.mipmap.welcome_2,R.mipmap.welcome_3};
    private List<ImageView> imageViews = new ArrayList<>();
    private TextView welcome_jump;

    private int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initData();

        welcome_viewpage = (ViewPager) findViewById(R.id.welcome_viewpage);
        welcome_viewpage.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return images.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(imageViews.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                container.addView(imageViews.get(position));

                return imageViews.get(position);
            }
        });


        welcome_viewpage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentItem = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        welcome_viewpage.setOnTouchListener(new View.OnTouchListener() {
            float startX = 0,startY = 0;
            float endX = 0,endY = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX = motionEvent.getX();
                        startY = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        endX = motionEvent.getX();
                        endY = motionEvent.getY();
                        //获取当前屏幕的宽度(后面可以把这一部分提取出来放到utils中)
                        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
                        Point size = new Point();
                        windowManager.getDefaultDisplay().getSize(size);
                        int width = size.x;
                        if(currentItem == (images.length-1) && (startX - endX) >= width/5){
                            //进入主页面
                            Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
//                        overridePendingTransition(R.anim.welcome_slide_right,R.anim.welcome_slide_left);

                        break;
                }

                return false;
            }
        });

        welcome_jump = (TextView) findViewById(R.id.welcome_jump);
        welcome_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击跳过按钮，直接跳转到主页面
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void initData(){

        for (int imgId:images){
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageResource(imgId);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
        }

    }
}
