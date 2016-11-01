package com.dany.projectdemo.retrofit.Servers;

import com.dany.projectdemo.model.PhoneCodeBean;
import com.dany.projectdemo.model.UserBean;

import rx.Observable;
import rx.Subscriber;

/**
 * 说明:
 * 作者：mint.zhang
 * 时间：2016/10/31 18:11
 */
public class LoginServers extends Servers {

    //获取短信验证码
    public static void getMsgCode(String phoneNumber, Subscriber<PhoneCodeBean> subscriber) {
        getMsgCode(service.requestCode(phoneNumber), subscriber);
    }

    private static void getMsgCode(Observable<PhoneCodeBean> observable, Subscriber<PhoneCodeBean> subscriber) {
        setSubscribe(observable, subscriber);
    }

    //短信验证码登录
    public static void requestLogin(String phoneNumber, String captcha, Subscriber<UserBean> subscriber) {
        requestLogin(service.requestPhoneLogin(phoneNumber, captcha), subscriber);
    }

    private static void requestLogin(Observable<UserBean> observable, Subscriber<UserBean> subscriber) {
        setSubscribe(observable, subscriber);
    }
}
