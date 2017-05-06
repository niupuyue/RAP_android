package net.example.paul.rapapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import net.example.paul.rapapp.R;
import net.example.paul.rapapp.domain.DataMidInfo;
import net.example.paul.rapapp.domain.DataOutInfo;
import net.example.paul.rapapp.domain.NewsInfo;
import net.example.paul.rapapp.model.interfaces.GetNewsCallbackBYString;
import net.example.paul.rapapp.ui.activity.AddressActivity;
import net.example.paul.rapapp.ui.activity.MessageActivity;
import net.example.paul.rapapp.ui.view.AllWebView;
import net.example.paul.rapapp.utils.Constants;
import net.example.paul.rapapp.utils.Logger;
import net.example.paul.rapapp.utils.OKManager;
import net.example.paul.rapapp.widget.ListViewForScrollView;
import net.example.paul.rapapp.widget.PullDownElasticImp;
import net.example.paul.rapapp.widget.PullDownScrollView;
import net.example.paul.rapapp.widget.adapter.HotAdapter;
import net.example.paul.rapapp.widget.adapter.NearByAdapter;
import net.example.paul.rapapp.widget.adapter.NewsAdapter;
import net.example.paul.rapapp.widget.adapter.PinpaiAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;

/**
 * Created by 牛谱乐
 * Timer 2016/12/28 11:42
 * E-mail niupuyue@togogo.net
 */
public class MedicFragment extends Fragment implements PullDownScrollView.RefreshListener {

    private TextView medic_top_position_tv;
    private ImageView medic_message_iv;
    private ImageView medic_saoyisao_iv;

    private ListView nearby_listview;
    private ListViewForScrollView news_listview;
    private GridView hot_gridview, pinpai_gridview;
    private ScrollView fragment_medic_scrollview;

    private NearByAdapter nearByAdapter;
    private NewsAdapter newsAdapter;
    private HotAdapter hotAdapter;
    private PinpaiAdapter pinpaiAdapter;

    //模拟数据
    private List<Map<String, Object>> list_data = new ArrayList<>();
    private String[] hot_datas = new String[12];
    private List<String> hot_data = new ArrayList<>();
    private List<Object> pinpai_data = new ArrayList<>();
    private List<Map<String, Object>> news_data = new ArrayList<>();

    private PullDownScrollView medic_refresh_root;

    /**
     * OKHttp网络请求
     */
    private OkHttpClient client;
    private OKManager manager;
    private List<NewsInfo> newsInfos;//新闻列表

    Handler handler2 = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_medic, null);
        fragment_medic_scrollview = (ScrollView) view.findViewById(R.id.fragment_medic_scrollview);
        fragment_medic_scrollview.smoothScrollTo(0,0);

        //获取当前对象实例
        manager = OKManager.getInstance();
        manager.asyncJsonStringByURL(Constants.MEDIC_GETNEWS, new GetNewsCallbackBYString() {
            @Override
            public void onResponse(String result) {
                //获取到的数据
                Logger.i(getActivity().getClass().getSimpleName(), result);
                DataOutInfo ss = JSON.parseObject(result, DataOutInfo.class);
                DataMidInfo sss = JSON.parseObject(ss.getResult(), DataMidInfo.class);
                newsInfos = JSON.parseArray(sss.getData(), NewsInfo.class);
                Logger.i("小牛大帝", newsInfos.toString());
                initListView(view);
            }
        });

        //下拉刷新
        medic_refresh_root = (PullDownScrollView) view.findViewById(R.id.medic_refresh_root);
        medic_refresh_root.setRefreshListener(this);
        medic_refresh_root.setPullDownElastic(new PullDownElasticImp(getContext()));

        medic_top_position_tv = (TextView) view.findViewById(R.id.medic_top_position_tv);
        medic_top_position_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddressActivity.class);
                startActivity(intent);
            }
        });

        medic_message_iv = (ImageView) view.findViewById(R.id.medic_message_iv);
        medic_message_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
            }
        });

        medic_saoyisao_iv = (ImageView) view.findViewById(R.id.medic_saoyisao_iv);
        medic_saoyisao_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 进入到扫一扫页面
                 */
            }
        });


        /**
         * 初始化gridview
         */
        initGridView(view);


        return view;
    }

    private void initListView(View view) {
        /**
         * 初始化listView
         */
        /**
         * 初始化数据
         */
        initListData();
        /**
         * 附近商店
         */
        nearby_listview = (ListView) view.findViewById(R.id.nearby_listview);
        nearByAdapter = new NearByAdapter(list_data, getActivity());
        nearby_listview.setAdapter(nearByAdapter);
        nearby_listview.setOnItemClickListener(new NearByItemClickListener());

        /**
         * 热门资讯
         */
        news_listview = (ListViewForScrollView) view.findViewById(R.id.news_listview);
        newsAdapter = new NewsAdapter(newsInfos, getActivity());
        news_listview.setAdapter(newsAdapter);
        news_listview.setOnItemClickListener(new NewsItemClickListener());

    }

    private void initListData() {
        for (int i = 0; i < 4; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("icon", R.mipmap.default_article_icon);
            map.put("name", "广州市白云区诺亚大药房");
            map.put("dis", "8.8Km");
            map.put("cope", "4.8");
            list_data.add(map);
        }
    }

    private void initGridView(View view) {
        /**
         * 初始化gridView
         */

        /*初始化数据*/
        initGridData();

        hot_gridview = (GridView) view.findViewById(R.id.hot_gridview);
        hotAdapter = new HotAdapter(hot_datas, getActivity());
        hot_gridview.setAdapter(hotAdapter);
        hot_gridview.setOnItemClickListener(new HotItemClickListener());

    }

    private void initGridData() {
        hot_datas = new String[]{"腹痛腹泻", "肺热咳嗽", "胃炎", "哮喘", "高血压", "糖尿病", "中风偏瘫", "风湿病", "乳腺癌", "肝癌", "痛风", "颈椎病"};

    }

    @Override
    public void onRefresh(PullDownScrollView view) {

        //下拉刷新
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                medic_refresh_root.finishRefresh("上次刷新时间:12:23");
            }
        }, 2000);
    }


    class NearByItemClickListener implements AdapterView.OnItemClickListener {

        /**
         * 附近商店点击事件
         */

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }

    }


    class NewsItemClickListener implements AdapterView.OnItemClickListener {
        /**
         * 热门资讯点击事件
         */

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            NewsInfo newsInfo = (NewsInfo) view.getTag();
            Intent intent = new Intent(getActivity(), AllWebView.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("newsInfo",newsInfo);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }

    class HotItemClickListener implements AdapterView.OnItemClickListener {

        /*
        * 高发疾病点击事件
        * */

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }
}
