package com.dany.projectdemo.retrofit.utils;

import android.content.Context;
import android.widget.Toast;


import com.dany.projectdemo.view.BaseActivity;

import rx.Subscriber;

/**
 * Created by dan.y on 2016/10/28.
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {
    private BaseActivity context;
    public BaseSubscriber(BaseActivity context) {
        this.context = context;
    }
    @Override
    public void onStart() {
        super.onStart();
        if (!OkHttp3Utils.isNetworkReachable(context)) {
            Toast.makeText(context, "当前网络不可用，请检查网络情况", Toast.LENGTH_SHORT).show();
            onCompleted();
            return;
        }
        context.showWaiting();
    }

    @Override
    public void onCompleted() {
        context.stopWaiting();
    }

}

