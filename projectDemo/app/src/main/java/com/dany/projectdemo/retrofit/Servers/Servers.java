package com.dany.projectdemo.retrofit.Servers;


import com.dany.projectdemo.retrofit.utils.RetrofitUtils;


import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dan.y on 2016/10/26.
 */
public class Servers extends RetrofitUtils {
    protected static final APIs service = getRetrofit().create(APIs.class);

    /**
     * 插入观察者
     * @param observable
     * @param subscriber
     * @param <T>
     *     需要map的转换下再写入
     */
    public static <T> void setSubscribe(Observable<T> observable, Subscriber<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(subscriber);
    }
}
