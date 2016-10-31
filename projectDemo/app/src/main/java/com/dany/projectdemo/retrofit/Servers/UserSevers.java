package com.dany.projectdemo.retrofit.Servers;

import com.dany.projectdemo.model.UserBean;

import rx.Observable;
import rx.Subscriber;

/**
 * 说明:
 * 作者：mint.zhang
 * 时间：2016/10/28 17:40
 */
public class UserSevers extends Servers {

    //Get请求 用户登录
    public static void getLogin(String username,String nickname,String login_type,String snsid,String access_token,String icon,String sex,Subscriber<UserBean> subscriber) {
        getLogin(service.getUserInfo(username, nickname, login_type, snsid, access_token, icon, sex), subscriber);
    }

    private static void getLogin(Observable<UserBean> observable, Subscriber<UserBean> subscriber) {
        setSubscribe(observable, subscriber);
    }
}
