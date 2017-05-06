package net.example.paul.rapapp.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import net.example.paul.rapapp.R;

/**
 * Created by 牛谱乐
 * Timer 2017/2/10 20:49
 * E-mail niupuyue@togogo.net
 */
public class SharePopwindow extends PopupWindow {

    private View view;

    //删除
    private ImageView share_delete;

    public SharePopwindow(final Activity activity){
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.pop_share_view,null);

        //获取当前屏幕高宽
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        //设置view,吧要现实的内容添加进来
        this.setContentView(view);
        //设置当前的高度
        this.setHeight(height);
        //设置宽度
        this.setWidth(width);
        //设置弹窗可以点击

        //设置点击弹窗之外的内容消失

        //刷新状态
        this.update();
        //设置颜色
//        ColorDrawable cd = new ColorDrawable(15132390);
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));
        //设施动画效果
        this.setAnimationStyle(R.style.pop_from_right_top);
        //实例化要点击的内容并为点击添加事件

        share_delete = (ImageView) view.findViewById(R.id.share_delete);
        share_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消窗口
                dismiss();
            }
        });

    }

    //调用此方法显示pop
    public void showPopWindow(View parent){
        if(!this.isShowing()){
            this.showAsDropDown(parent,parent.getLayoutParams().width/2,18);
        }else {
            this.dismiss();
        }

    }

}
