package net.example.paul.rapapp.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;

import java.util.Stack;

/**
 * Created by 牛谱乐
 * Timer 2016/12/28 11:50
 * E-mail niupuyue@togogo.net
 */
public class App extends Application{
    private static Stack<Activity> mActivityStack;
    private static App mApp;

    private App(){

    }

    /**
     * 单一实例
     */
    public static App getInstance(){
        if(mApp == null){
            mApp = new App();
        }
        return mApp;
    }

    /**
     * 添加Activity到堆栈中
     */
    public void addActivity(Activity activity){
        if(mActivityStack == null){
            mActivityStack = new Stack<Activity>();
        }
        mActivityStack.add(activity);
    }

    /**
     * 获取栈顶的activity
     */
    public Activity getTopActivity(){
        Activity activity = mActivityStack.lastElement();
        return activity;
    }

    /**
     * 结束栈顶activity
     */
    public void killTopActivity(){
        Activity activity = mActivityStack.lastElement();
        killActivity(activity);
    }

    /**
     * 结束指定的activity
     */
    public void killActivity(Activity activity){
        if(activity != null){
            mActivityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的activity
     */
    public void killActivity(Class<?> cls){
        for(Activity activity:mActivityStack){
            if(activity.getClass().equals(cls)){
                killActivity(activity);
            }
        }
    }

    /**
     * 结束所有的Activity
     */
    public void killAllActivity(){
        for(int i = 0, size = mActivityStack.size();i < size;i++){
            if(null != mActivityStack.get(i)){
                mActivityStack.get(i).finish();
            }
        }
        mActivityStack.clear();
    }


    /**
     * 退出应用
     */
    public void AppExit(Context context){
        try{
            killAllActivity();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.restartPackage(context.getPackageName());
            System.exit(0);
        } catch(Exception e){

        }
    }
}
