package net.example.paul.rapapp.widget.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.example.paul.rapapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by 牛谱乐
 * Timer 2017/1/4 12:21
 * E-mail niupuyue@togogo.net
 */
public class NearByAdapter extends BaseAdapter {

    private List<Map<String,Object>> data = new ArrayList<>();
    private Activity activity;

    public NearByAdapter(List<Map<String,Object>> data,Activity activity){
        this.data = data;
        this.activity = activity;
    }

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
        if(convertView == null){
            holder = new Holder();
            convertView = activity.getLayoutInflater().inflate(R.layout.item_medic_listview,null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_medic_list_img);
            holder.textView1 = (TextView) convertView.findViewById(R.id.item_medic_list_title);
            holder.textView2 = (TextView) convertView.findViewById(R.id.item_medic_list_dis);
            holder.textView3 = (TextView) convertView.findViewById(R.id.item_medic_list_cope);
            holder.btn = (Button) convertView.findViewById(R.id.item_medic_list_btn);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        holder.imageView.setImageResource((Integer) data.get(position).get("icon"));
        holder.textView1.setText(data.get(position).get("name").toString());
        holder.textView2.setText(data.get(position).get("dis").toString());
        holder.textView3.setText(data.get(position).get("cope").toString());

        return convertView;
    }

    class Holder{
        ImageView imageView;
        TextView textView1,textView2,textView3;
        Button btn;
    }

}
