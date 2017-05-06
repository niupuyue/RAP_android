package net.example.paul.rapapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.example.paul.rapapp.R;
import net.example.paul.rapapp.ui.fragment.AccountFragment;
import net.example.paul.rapapp.ui.fragment.CartFragment;
import net.example.paul.rapapp.ui.fragment.HomeFragment;
import net.example.paul.rapapp.ui.fragment.MedicFragment;
import net.example.paul.rapapp.utils.T;
import net.example.paul.rapapp.widget.MyMainViewPager;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 牛谱乐
 * Timer 2016/12/28 11:35
 * E-mail niupuyue@togogo.net
 * 主页面
 */
public class MainActivity extends FragmentActivity{

    private boolean isExit;
    private boolean hasTask;


    //主页的viewpager
    private MyMainViewPager main_viewpager;
    //fragments集合
    private List<Fragment> fragments = new ArrayList<>();
    //当前的坐标
    private int index_point = 0;
    private MainAdapter adapter;
    private LinearLayout main_bottom_home_ll,main_bottom_medic_ll,main_bottom_cart_ll,main_bottom_account_ll;
    private ImageView main_bottom_home_iv,main_bottom_medic_iv,main_bottom_cart_iv,main_bottom_account_iv;
    private TextView main_bottom_home_tv,main_bottom_medic_tv,main_bottom_cart_tv,main_bottom_account_tv;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        fragments.add(new HomeFragment());
        fragments.add(new MedicFragment());
        fragments.add(new CartFragment());
        fragments.add(new AccountFragment());

        initLayout();
        initListener();

    }

    private void initLayout(){
        main_viewpager = (MyMainViewPager) findViewById(R.id.main_viewpager);

        main_bottom_home_ll = (LinearLayout) findViewById(R.id.main_bottom_home_ll);
        main_bottom_medic_ll = (LinearLayout) findViewById(R.id.main_bottom_medic_ll);
        main_bottom_cart_ll = (LinearLayout) findViewById(R.id.main_bottom_cart_ll);
        main_bottom_account_ll = (LinearLayout) findViewById(R.id.main_bottom_account_ll);

        main_bottom_home_iv = (ImageView) findViewById(R.id.main_bottom_home_iv);
        main_bottom_medic_iv = (ImageView) findViewById(R.id.main_bottom_medic_iv);
        main_bottom_cart_iv = (ImageView) findViewById(R.id.main_bottom_cart_iv);
        main_bottom_account_iv = (ImageView) findViewById(R.id.main_bottom_account_iv);

        main_bottom_home_tv = (TextView) findViewById(R.id.main_bottom_home_tv);
        main_bottom_medic_tv = (TextView) findViewById(R.id.main_bottom_meidc_tv);
        main_bottom_cart_tv = (TextView) findViewById(R.id.main_bottom_cart_tv);
        main_bottom_account_tv = (TextView) findViewById(R.id.main_bottom_account_tv);

    }

    private void initListener(){

        adapter = new MainAdapter();
        main_viewpager.setAdapter(adapter);
        main_viewpager.setCurrentItem(0);
        main_viewpager.setSlipping(false);
        main_viewpager.setOnPageChangeListener(new MainPageChangeListener());

        main_bottom_home_ll.setOnClickListener(new MainClickListener(0));
        main_bottom_medic_ll.setOnClickListener(new MainClickListener(1));
        main_bottom_cart_ll.setOnClickListener(new MainClickListener(2));
        main_bottom_account_ll.setOnClickListener(new MainClickListener(3));
    }


    class MainClickListener implements View.OnClickListener{
        private int click_point = -1;

        public MainClickListener(int point){
            click_point = point;
        }

        @Override
        public void onClick(View v) {
            main_viewpager.setCurrentItem(click_point);
        }
    }

    class MainPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0:
                    main_bottom_home_iv.setImageResource(R.mipmap.bottom_home_selected);
                    main_bottom_home_tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                    if(index_point == 1){
                        main_bottom_medic_iv.setImageResource(R.mipmap.bottom_list_normal);
                        main_bottom_medic_tv.setTextColor(getResources().getColor(R.color.font_block));
                    }else if(index_point == 2){
                        main_bottom_cart_iv.setImageResource(R.mipmap.bottom_cart_normal);
                        main_bottom_cart_tv.setTextColor(getResources().getColor(R.color.font_block));
                    }else if(index_point == 3){
                        main_bottom_account_iv.setImageResource(R.mipmap.bottom_user_normal);
                        main_bottom_account_tv.setTextColor(getResources().getColor(R.color.font_block));
                    }
                    break;
                case 1:
                    main_bottom_medic_iv.setImageResource(R.mipmap.bottom_list_selected);
                    main_bottom_medic_tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                    if(index_point == 0){
                        main_bottom_home_iv.setImageResource(R.mipmap.bottom_home_normal);
                        main_bottom_home_tv.setTextColor(getResources().getColor(R.color.font_block));
                    }else if(index_point == 2){
                        main_bottom_cart_iv.setImageResource(R.mipmap.bottom_cart_normal);
                        main_bottom_cart_tv.setTextColor(getResources().getColor(R.color.font_block));
                    }else if(index_point == 3){
                        main_bottom_account_iv.setImageResource(R.mipmap.bottom_user_normal);
                        main_bottom_account_tv.setTextColor(getResources().getColor(R.color.font_block));
                    }
                    break;
                case 2:
                    main_bottom_cart_iv.setImageResource(R.mipmap.bottom_cart_selected);
                    main_bottom_cart_tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                    if(index_point == 0){
                        main_bottom_home_iv.setImageResource(R.mipmap.bottom_home_normal);
                        main_bottom_home_tv.setTextColor(getResources().getColor(R.color.font_block));
                    }else if(index_point == 1){
                        main_bottom_medic_iv.setImageResource(R.mipmap.bottom_list_normal);
                        main_bottom_medic_tv.setTextColor(getResources().getColor(R.color.font_block));
                    }else if(index_point == 3){
                        main_bottom_account_iv.setImageResource(R.mipmap.bottom_user_normal);
                        main_bottom_account_tv.setTextColor(getResources().getColor(R.color.font_block));
                    }
                    break;
                case 3:
                    main_bottom_account_iv.setImageResource(R.mipmap.bottom_user_selected);
                    main_bottom_account_tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                    if(index_point == 0){
                        main_bottom_home_iv.setImageResource(R.mipmap.bottom_home_normal);
                        main_bottom_home_tv.setTextColor(getResources().getColor(R.color.font_block));
                    }else if(index_point == 1){
                        main_bottom_medic_iv.setImageResource(R.mipmap.bottom_list_normal);
                        main_bottom_medic_tv.setTextColor(getResources().getColor(R.color.font_block));
                    }else if(index_point == 2){
                        main_bottom_cart_iv.setImageResource(R.mipmap.bottom_cart_normal);
                        main_bottom_cart_tv.setTextColor(getResources().getColor(R.color.font_block));
                    }
                    break;

            }
            index_point = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class MainAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(fragments.get(position).getView());
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = fragments.get(position);
            if(!fragment.isAdded()){
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.add(fragment,fragment.getClass().getSimpleName());
                ft.commit();
                //提交事务
                fragmentManager.executePendingTransactions();
            }
            if(fragment.getView().getParent() == null){
                container.addView(fragment.getView());
            }
            return fragment.getView();
        }
    }


    //设置两次点击退出程序
    private Timer tExit = new Timer();
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            isExit = true;
            hasTask = true;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit == false) {
                isExit = true;
                T.showShort(MainActivity.this, "一二三四,再来一次", 3000);
                if (!hasTask) {
                    tExit.schedule(task, 2000);
                }
            } else {
                finish();
                System.exit(0);
            }
        }
        return false;

    }
}
