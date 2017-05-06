package net.example.paul.rapapp.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.example.paul.rapapp.R;
import net.example.paul.rapapp.widget.PullDownElasticImp;
import net.example.paul.rapapp.widget.PullDownScrollView;

/**
 * Created by 牛谱乐
 * Timer 2016/12/28 11:42
 * E-mail niupuyue@togogo.net
 */
public class CartFragment extends Fragment implements PullDownScrollView.RefreshListener {

    private PullDownScrollView cart_refresh_root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart,null);

        cart_refresh_root = (PullDownScrollView) view.findViewById(R.id.cart_refresh_root);
        cart_refresh_root.setRefreshListener(this);
        cart_refresh_root.setPullDownElastic(new PullDownElasticImp(getContext()));

        return view;
    }

    @Override
    public void onRefresh(PullDownScrollView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cart_refresh_root.finishRefresh("上次刷新时间:12:23");
            }
        },2000);
    }
}
