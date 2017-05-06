package net.example.paul.rapapp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

import net.example.paul.rapapp.R;
import net.example.paul.rapapp.domain.BannerInfo;
import net.example.paul.rapapp.model.service.LocationService;
import net.example.paul.rapapp.ui.activity.AddressActivity;
import net.example.paul.rapapp.ui.activity.MessageActivity;
import net.example.paul.rapapp.widget.AutoBannerView;
import net.example.paul.rapapp.widget.HorizontalListView;
import net.example.paul.rapapp.widget.PullDownElasticImp;
import net.example.paul.rapapp.widget.PullDownScrollView;

import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 牛谱乐
 * Timer 2016/12/28 11:41
 * E-mail niupuyue@togogo.net
 */
public class HomeFragment extends Fragment implements PullDownScrollView.RefreshListener{

    private ImageView saoyisao_img;
    private ImageView message_img;
    private TextView home_top_position;

    private AutoBannerView autoBannerView;
    private MyAutoBannerAdapter autoBnnerAdapter;
    private TextView title;
    private List<BannerInfo> list;

    private GridView sort_gridview;
    private SortGridViewAdapter sortAdapter;
    private List<Map<String, Object>> data = new ArrayList<>();


    private HorizontalListView horListView01,horListView02;
    private HorListAdapter horListAdapter;
    private List<Map<String,Object>> horlist_data = new ArrayList<>();

    private PullDownScrollView home_refresh_root;

    //百度地图
    private LocationService locationService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment_home_view = inflater.inflate(R.layout.fragment_home, null);


        home_refresh_root = (PullDownScrollView) fragment_home_view.findViewById(R.id.home_refresh_root);
        home_refresh_root.setRefreshListener(this);
        home_refresh_root.setPullDownElastic(new PullDownElasticImp(getContext()));

        message_img = (ImageView) fragment_home_view.findViewById(R.id.home_message_iv);
        message_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
            }
        });

        home_top_position = (TextView) fragment_home_view.findViewById(R.id.home_top_position_tv);

        //开启定位并显示
        locationService = new LocationService(getContext());
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        int type = getActivity().getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }
        locationService.start();// 定位SDK

        home_top_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddressActivity.class);
                startActivity(intent);
            }
        });
        /**
         * 广告轮播
         */
        initBanner(fragment_home_view);

        /**
         * 商品分类
         */
        initGridview(fragment_home_view);

        /**
         * 横向ListView
         */
        initHorListView(fragment_home_view);

        return fragment_home_view;
    }


    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                sb.append("\nCountry : ");// 国家名称
                sb.append(location.getCountry());
                sb.append("\ncitycode : ");// 城市编码
                sb.append(location.getCityCode());
                sb.append("\ncity : ");// 城市
                sb.append(location.getCity());
                sb.append("\nDistrict : ");// 区
                sb.append(location.getDistrict());
                sb.append("\nStreet : ");// 街道
                sb.append(location.getStreet());
                sb.append("\naddr : ");// 地址信息
                sb.append(location.getAddrStr());
                sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
                sb.append(location.getUserIndoorState());
                sb.append("\nDirection(not all devices have value): ");
                sb.append(location.getDirection());// 方向
                sb.append("\nlocationdescribe: ");
                sb.append(location.getLocationDescribe());// 位置语义化信息
                logMsg(location.getAddrStr());
            }
        }

        public void onConnectHotSpotMessage(String s, int i) {
        }
    };

    /**
     * 显示请求字符串
     *
     * @param str
     */
    public void logMsg(String str) {
        try {
            if (home_top_position != null)
                home_top_position.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initBanner(View view) {
        /**
         * 初始化广告轮播
         */
        autoBannerView = (AutoBannerView) view.findViewById(R.id.autoBannerView);
        title = (TextView) view.findViewById(R.id.bannerTitle);
        autoBannerView.setDotGravity(AutoBannerView.DotGravity.RIGHT);
        autoBannerView.setWaitMilliSceond(3000);
        autoBannerView.setDotMargin(4);
        autoBannerView.setOnBannerChangeListener(onBannerChangeListener);

        //初始化数据
        autoBnnerAdapter = new MyAutoBannerAdapter(getContext());
        list = new ArrayList<>();
        test();
        autoBnnerAdapter.changeItems(list);
        autoBannerView.setAdapter(autoBnnerAdapter);
    }

    private AutoBannerView.OnBannerChangeListener onBannerChangeListener = new AutoBannerView.OnBannerChangeListener() {
        @Override
        public void onCurrentItemChanged(int position) {
            title.setText(list.get(position).getName());
        }
    };

    @Override
    public void onRefresh(PullDownScrollView view) {
        //刷新
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                home_refresh_root.finishRefresh("上次刷新时间:12:23");
            }
        }, 2000);
    }

    private class MyAutoBannerAdapter implements AutoBannerView.AutoBannerAdapter {
        List<BannerInfo> urls;
        Context context;

        public MyAutoBannerAdapter(Context context) {
            this.context = context;
            this.urls = new ArrayList<>();
        }

        public void changeItems(@NonNull List<BannerInfo> urls) {
            this.urls.clear();
            this.urls.addAll(urls);
        }

        @Override
        public int getCount() {
            return urls.size();
        }

        @Override
        public View getView(View convertView, int position) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(R.mipmap.default_article_icon);
            return imageView;
        }
    }

    public void test() {
        BannerInfo info1 = new BannerInfo();
        info1.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1484117550&di=3b462612d1f2fe704343bfea0c999b2a&imgtype=jpg&er=1&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fitem%2F201411%2F08%2F20141108115926_HGRrA.thumb.700_0.jpeg");
        info1.setName("寒冰射手");
        list.add(info1);
        BannerInfo info2 = new BannerInfo();
        info2.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1483522867783&di=03c4d961fd922dcb84ad015fd27c08c3&imgtype=0&src=http%3A%2F%2Fupload.newhua.com%2F2012%2F0329%2F1332985457723.jpg");
        info2.setName("齐天大圣");
        list.add(info2);
        BannerInfo info3 = new BannerInfo();
        info3.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1483522905377&di=e2d6958eab1548e73843e97fa1c1872d&imgtype=0&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fitem%2F201503%2F31%2F20150331203011_yshBy.thumb.700_0.jpeg");
        info3.setName("放逐之刃");
        list.add(info3);
        BannerInfo info4 = new BannerInfo();
        info4.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1483522933159&di=daaac44bf667ebb74e9226f0052d8c88&imgtype=0&src=http%3A%2F%2Fupload.taihainet.com%2F2016%2F1001%2F1475323427223.jpeg");
        info4.setName("好多好多");
        list.add(info4);
    }

    /**
     * 初始化分类Gridview
     * @param view
     */
    private void initGridview(View view) {
        initData();
        sort_gridview = (GridView) view.findViewById(R.id.sort_gridview);
        sortAdapter = new SortGridViewAdapter();
        sort_gridview.setAdapter(sortAdapter);
        sort_gridview.setOnItemClickListener(new SortItemClick());

    }

    class SortItemClick implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //分類點擊之後的響應事件
        }
    }

    class SortGridViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (convertView == null) {
                holder = new Holder();
                convertView = getActivity().getLayoutInflater().inflate(R.layout.item_home_sort, null);
                holder.imageView = (ImageView) convertView.findViewById(R.id.sort_img);
                holder.textView1 = (TextView) convertView.findViewById(R.id.sort_title);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.imageView.setImageResource((Integer) data.get(position).get("icon"));
            holder.textView1.setText(data.get(position).get("title").toString());
            return convertView;

        }

        class Holder {
          ImageView imageView;
            TextView textView1;
        }

    }

    private void initData() {
        /**
         * 分类的数据初始化
         */
        HashMap<String, Object> hashData1 = new HashMap<>();
        hashData1.put("title", "商品分类");
        hashData1.put("icon", R.mipmap.main_subitem1);
        data.add(hashData1);
        HashMap<String, Object> hashData2 = new HashMap<>();
        hashData2.put("title", "附近药店");
        hashData2.put("icon", R.mipmap.main_subitem2);
        data.add(hashData2);
        HashMap<String, Object> hashData3 = new HashMap<>();
        hashData3.put("title", "我的收藏");
        hashData3.put("icon", R.mipmap.main_subitem3);
        data.add(hashData3);
        HashMap<String, Object> hashData4 = new HashMap<>();
        hashData4.put("title", "签到抽奖");
        hashData4.put("icon", R.mipmap.main_subitem4);
        data.add(hashData4);
        HashMap<String, Object> hashData5 = new HashMap<>();
        hashData5.put("title", "男性");
        hashData5.put("icon", R.mipmap.main_subitem5);
        data.add(hashData5);
        HashMap<String, Object> hashData6 = new HashMap<>();
        hashData6.put("title", "女性");
        hashData6.put("icon", R.mipmap.main_subitem6);
        data.add(hashData6);
        HashMap<String, Object> hashData7 = new HashMap<>();
        hashData7.put("title", "老人");
        hashData7.put("icon", R.mipmap.main_subitem7);
        data.add(hashData7);
        HashMap<String, Object> hashData8 = new HashMap<>();
        hashData8.put("title", "儿童");
        hashData8.put("icon", R.mipmap.main_subitem8);
        data.add(hashData8);
    }


    /**
     * 初始化横向ListView
     */
    public void initHorListView(View view){
        initData4hor();
        horListView01 = (HorizontalListView) view.findViewById(R.id.home_horizontalListview_01);
        horListAdapter = new HorListAdapter();
        horListView01.setAdapter(horListAdapter);

        horListView02 = (HorizontalListView) view.findViewById(R.id.home_horizontalListview_02);
        horListView02.setAdapter(horListAdapter);
    }

    private void initData4hor(){
        for(int i=0;i<8;i++){
            Map<String,Object> map1 = new HashMap<>();
            map1.put("name","阿卡波糖片");
            map1.put("icon",R.mipmap.product_01);
            map1.put("price","￥34.00");
            horlist_data.add(map1);
        }
    }

    class HorListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return horlist_data.size();
        }

        @Override
        public Object getItem(int position) {
            return horlist_data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Item item = null;
            if (convertView == null) {
                item = new Item();
                convertView = getActivity().getLayoutInflater().inflate(R.layout.item_home_sort, null);
                item.imageView = (ImageView) convertView.findViewById(R.id.sort_img);
                item.textView1 = (TextView) convertView.findViewById(R.id.sort_title);
                item.textView2 = (TextView) convertView.findViewById(R.id.sort_price);
                convertView.setTag(item);
            } else {
                item = (Item) convertView.getTag();
            }
            item.imageView.setImageResource((Integer) horlist_data.get(position).get("icon"));
            item.textView1.setText(horlist_data.get(position).get("name").toString());
            item.textView2.setText(horlist_data.get(position).get("price").toString());
            return convertView;
        }

        class Item {
            ImageView imageView;
            TextView textView1,textView2;
        }
    }


}
