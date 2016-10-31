package com.dany.projectdemo.retrofit.Servers;

import com.dany.projectdemo.common.utils.Constants;
import com.dany.projectdemo.model.WXBean;
import com.dany.projectdemo.model.WXUserBean;

import rx.Observable;
import rx.Subscriber;

/**
 * 说明:
 * 作者：mint.zhang
 * 时间：2016/10/28 14:53
 */
public class WXEntryServers extends Servers {
    //Get请求 获取微信授权信息
    public static void getWXToken(String code, Subscriber<WXBean> subscriber) {
        getWXToken(serviceforwx.getWXToken(Constants.WXAPPID, Constants.WXSECRET, code, "authorization_code"), subscriber);
    }

    private static void getWXToken(Observable<WXBean> observable, Subscriber<WXBean> subscriber) {
        setSubscribe(observable, subscriber);

    }

    //Get请求 获取微信用户信息
    public static void getWXUserInfo(String access_token, String openid, Subscriber<WXUserBean> subscriber) {
        getWXUserInfo(serviceforwx.getWXUserInfo(access_token, openid), subscriber);
    }

    private static void getWXUserInfo(Observable<WXUserBean> observable, Subscriber<WXUserBean> subscriber) {
        setSubscribe(observable, subscriber);
    }

}
