package com.dany.projectdemo.view;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.HashSet;

/**
 * Created by dan.y on 2016/10/25.
 */
public class MyApplication extends Application {
    public static MyApplication mContext;
    public HashSet<Activity> mainActivities;

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

    @Override
    public void onCreate() {
        mContext = (MyApplication) getApplicationContext();
        mainActivities = new HashSet<Activity>();
        super.onCreate();
    }

    public static MyApplication getContext() {
        return mContext;
    }

}
