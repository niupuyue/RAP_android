package net.example.paul.rapapp.widget.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import net.example.paul.rapapp.R;
import net.example.paul.rapapp.domain.NewsInfo;
import net.example.paul.rapapp.model.interfaces.GetImageCallbackByByte;
import net.example.paul.rapapp.utils.OKManager;

import java.util.List;

import okhttp3.OkHttpClient;

/**
 * Created by 牛谱乐
 * Timer 2017/1/4 12:21
 * E-mail niupuyue@togogo.net
 */
public class NewsAdapter extends BaseAdapter {
    private List<NewsInfo> newsInfos;
    private Activity activity;
    private OkHttpClient client;
    private OKManager manager;

    public NewsAdapter(List<NewsInfo> newsInfos, Activity activity) {
        this.newsInfos = newsInfos;
        this.activity = activity;
    }


    @Override
    public int getCount() {
        return newsInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return newsInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Item item = new Item();
        convertView = activity.getLayoutInflater().inflate(R.layout.item_news_listview, null);
        item.imageView = (ImageView) convertView.findViewById(R.id.news_listview_img);
        item.title = (TextView) convertView.findViewById(R.id.news_listview_title);
        item.content = (TextView) convertView.findViewById(R.id.news_listview_content);
        item.time = (TextView) convertView.findViewById(R.id.news_listview_time);
        convertView.setTag(item);
        item.title.setText(newsInfos.get(position).getTitle());
        item.content.setText(newsInfos.get(position).getAuthor_name());
        item.time.setText(newsInfos.get(position).getDate());
        convertView.setTag(newsInfos.get(position));
        /**
         * 根据图片地址请求网络
         */
        String img_path = newsInfos.get(position).getThumbnail_pic_s();
        manager = OKManager.getInstance();
        manager.asyncGetByteByURL(img_path, new GetImageCallbackByByte() {
            @Override
            public void onResponse(byte[] result) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(result, 0, result.length);
                item.imageView.setImageBitmap(bitmap);
            }
        });
        return convertView;
    }

    class Item {
        ImageView imageView;
        TextView title, content, time;
    }

}
