package net.example.paul.rapapp.utils;

import android.os.Handler;
import android.os.Looper;
import net.example.paul.rapapp.model.interfaces.GetImageCallbackByByte;
import net.example.paul.rapapp.model.interfaces.GetNewsCallbackBYString;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 牛谱乐
 * Timer 2017/2/13 17:10
 * E-mail niupuyue@togogo.net
 */
public class OKManager {

    private OkHttpClient client;
    private volatile static OKManager manager;
    //获取当前的类名
    private final static String TAG = OKManager.class.getSimpleName();
    private Handler handler;
    //提交json数据
    private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    //提交字符串
    private final  static MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown;charset=utf-8");


    //构造方法
    private OKManager(){
        //实例化网络请求对象
        client = new OkHttpClient();
        //获取当前主线程
        handler = new Handler(Looper.getMainLooper());
    }

    /**
     * 单例形式获取对象
     */
    public static OKManager getInstance(){
        OKManager instance = new OKManager();
        if(manager ==null){
            synchronized (OKManager.class){
                if(instance == null){
                    instance = new OKManager();
                    manager = instance;
                }
            }
        }
        return instance;
    }

    /**
     * 异步网络请求，获取json字符串
     */
    public void asyncJsonStringByURL(String url, final GetNewsCallbackBYString callback){
        final Request request = new Request.Builder().get().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获取成功
                if(response != null && response.isSuccessful()){
                    onSuccessJsonStringMethod(response.body().string(),callback);
                }
            }
        });
    }

    /**
     * 请求返回的结果是json字符串
     */
    private void onSuccessJsonStringMethod(final String jsonValue,final GetNewsCallbackBYString callback){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(callback != null){
                    callback.onResponse(jsonValue);
                }
            }
        });
    }

    /**
     * 异步网络请求获取字节数组
     */
    public void asyncGetByteByURL(String url, final GetImageCallbackByByte callback){
        final Request request = new Request.Builder().get().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //成功
                if(response != null && response.isSuccessful()){
                    onSuccessByteMethod(response.body().bytes(),callback);
                }
            }
        });
    }

    /**
     * 请求返回的是数组
     */
    private void onSuccessByteMethod(final byte[] data,final GetImageCallbackByByte callbackByByte){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(callbackByByte != null){
                    callbackByByte.onResponse(data);
                }
            }
        });
    }

}
