package com.dany.projectdemo.retrofit.utils;

import com.dany.projectdemo.R;
import com.dany.projectdemo.view.MyApplication;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 创建人：dan.y
 * 创建时间：2016-10-25
 * 类描述：封装一个retrofit集成0kHttp3的抽象基类
 */
public abstract class RetrofitUtils {

    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;
    private static Retrofit mRetrofitforwx;

    /**
     * 获取Retrofit对象
     *
     * @return
     */
    protected static Retrofit getRetrofit() {

        if (null == mRetrofit) {

            if (null == mOkHttpClient) {
                mOkHttpClient = OkHttp3Utils.getOkHttpClient();
            }

            mRetrofit = new Retrofit.Builder()
//                    .baseUrl(.baseUrl("http://222.73.0.213:8300/")
                    .baseUrl(MyApplication.getContext().getString(R.string.baseUrl))
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();

        }

        return mRetrofit;
    }

    protected static Retrofit getRetrofitforwx() {

        if (null == mRetrofitforwx) {

            if (null == mOkHttpClient) {
                mOkHttpClient = OkHttp3Utils.getOkHttpClient();
            }

            mRetrofitforwx = new Retrofit.Builder()
                    .baseUrl("https://api.weixin.qq.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();

        }

        return mRetrofitforwx;
    }

}
