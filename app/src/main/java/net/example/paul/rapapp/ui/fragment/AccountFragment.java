package net.example.paul.rapapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import net.example.paul.rapapp.R;
import net.example.paul.rapapp.ui.activity.MessageActivity;
import net.example.paul.rapapp.ui.activity.SettingActivity;
import net.example.paul.rapapp.ui.view.SharePopwindow;
import net.example.paul.rapapp.widget.PullDownElasticImp;
import net.example.paul.rapapp.widget.PullDownScrollView;

/**
 * Created by 牛谱乐
 * Timer 2016/12/28 11:43
 * E-mail niupuyue@togogo.net
 */
public class AccountFragment extends Fragment implements PullDownScrollView.RefreshListener{

    private ImageView account_setting,account_message;

    private ImageView account_unpay,account_unsend,account_untake,account_unpingjia,account_orders;

    private ImageView account_history,account_like,account_score,account_discount,account_address,account_comment,account_complain,account_manager;

    private RelativeLayout comments,good_comments,share,contact,aboutus;

    //滑动
    private ScrollView fragment_account_scrollview;

    private PullDownScrollView mPullDownScrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_account,null);

        //跳转到设置页面
        account_setting = (ImageView) view.findViewById(R.id.fragment_account_top_settting);
        account_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
        //跳转到消息页面
        account_message = (ImageView) view.findViewById(R.id.fragment_account_top_message);
        account_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
            }
        });

        fragment_account_scrollview = (ScrollView) view.findViewById(R.id.fragment_account_scrollview);

        //关于我们
        aboutus = (RelativeLayout) view.findViewById(R.id.aboutus);
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //分享给好友
        share = (RelativeLayout) view.findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePopwindow sharePopwindow = new SharePopwindow(getActivity());
                sharePopwindow.showPopWindow(aboutus);
                sharePopwindow.setFocusable(true);
                sharePopwindow.setOutsideTouchable(false);
                final WindowManager.LayoutParams wlBackground = getActivity().getWindow().getAttributes();
                wlBackground.alpha = 0.5f;      // 0.0 完全不透明,1.0完全透明
                getActivity().getWindow().setAttributes(wlBackground);
                // 当PopupWindow消失时,恢复其为原来的颜色
                sharePopwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        wlBackground.alpha = 1.0f;
                        getActivity().getWindow().setAttributes(wlBackground);
                    }
                });
            }
        });

        mPullDownScrollView = (PullDownScrollView) view.findViewById(R.id.account_refresh_root);
        mPullDownScrollView.setRefreshListener(this);
        mPullDownScrollView.setPullDownElastic(new PullDownElasticImp(getContext()));



        return view;
    }

    @Override
    public void onRefresh(PullDownScrollView view) {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                mPullDownScrollView.finishRefresh("上次刷新时间:12:23");
            }
        }, 2000);
    }
}
