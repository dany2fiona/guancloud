package com.dany.projectdemo.view;

import android.app.Activity;
import android.app.Application;

import com.dany.projectdemo.common.utils.Constants;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.util.HashSet;

/**
 * Created by dan.y on 2016/10/25.
 */
public class MyApplication extends Application {
    public static MyApplication mContext;
    public HashSet<Activity> mainActivities;

    private IWXAPI wxApi;

    private String goToMainTab;

    public void setToMainTab(String tab) {
        this.goToMainTab = tab;
    }

    public String getToMainTab() {
        return this.goToMainTab;
    }

    public void clearToMainTab() {
        this.goToMainTab = "";
    }

    public IWXAPI getIWXAPI() {
        return wxApi;
    }

    @Override
    public void onCreate() {
        mContext = (MyApplication) getApplicationContext();
        mainActivities = new HashSet<Activity>();

        //初始化微信
        wxApi = WXAPIFactory.createWXAPI(this, Constants.WXAPPID,true);
        wxApi.registerApp(Constants.WXAPPID);

        super.onCreate();
    }

    public static MyApplication getContext() {
        return mContext;
    }

}
