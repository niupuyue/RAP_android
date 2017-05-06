package net.example.paul.rapapp.widget.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.example.paul.rapapp.R;


/**
 * Created by 牛谱乐
 * Timer 2017/1/4 12:21
 * E-mail niupuyue@togogo.net
 */
public class HotAdapter extends BaseAdapter {

    private String hot_datas[] = null;
    private Activity activity;

    public HotAdapter(String[] hot_datas, Activity activity) {
        this.hot_datas = hot_datas;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return hot_datas.length;
    }

    @Override
    public Object getItem(int position) {
        return hot_datas[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = null;
        if(convertView == null){
            item = new Item();
            convertView = activity.getLayoutInflater().inflate(R.layout.item_hot_gridview,null);
            item.textView = (TextView) convertView.findViewById(R.id.item_hot_name);
            convertView.setTag(item);
        }else {
            item = (Item) convertView.getTag();
        }
        item.textView.setText(hot_datas[position]);

        return convertView;
    }

    class Item{
        TextView textView;
    }
}
