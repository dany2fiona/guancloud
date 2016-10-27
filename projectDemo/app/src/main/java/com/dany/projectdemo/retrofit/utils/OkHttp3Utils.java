package com.dany.projectdemo.retrofit.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.dany.projectdemo.view.MyApplication;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 创建人：dan.y
 * 创建时间：2016-10-25
 * 类描述：封装一个OkHttp3的获取类
 */
public class OkHttp3Utils {

    private static OkHttpClient mOkHttpClient;

    //设置缓存目录
    private static File cacheDirectory = new File(MyApplication.getContext().getCacheDir().getAbsolutePath(), "MyCache");
    private static Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);
    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);


    /**
     * 获取OkHttpClient对象
     *
     * @return
     */
    public static OkHttpClient getOkHttpClient() {

        if (null == mOkHttpClient) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            int maxAge = 60*60; // 有网络时 设置缓存超时时间1个小时
                            int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
                            Request request = chain.request();
                            if (isNetworkReachable(MyApplication.getContext())) {
                                request= request.newBuilder()
                                        .cacheControl(CacheControl.FORCE_NETWORK)//有网络时只从网络获取
                                        .build();
                            }else {
                                request= request.newBuilder()
                                        .cacheControl(CacheControl.FORCE_CACHE)//无网络时只从缓存中读取
                                        .build();
                            }
                            Response response = chain.proceed(request);
                            if (isNetworkReachable(MyApplication.getContext())) {
                                response= response.newBuilder()
                                        .removeHeader("Pragma")
                                        .header("Cache-Control", "public, max-age=" + maxAge)
                                        .build();
                            } else {
                                response= response.newBuilder()
                                        .removeHeader("Pragma")
                                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                        .build();
                            }
                            return response;
                        }

                    })
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .cache(cache)
                    .build();
            return mOkHttpClient;


        }

        return mOkHttpClient;
    }

    /**
     * 判断网络是否可用
     *
     * @param context Context对象
     */
    public static Boolean isNetworkReachable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo current = cm.getActiveNetworkInfo();
        if (current == null) {
            return false;
        }
        return (current.isAvailable());
    }
}
