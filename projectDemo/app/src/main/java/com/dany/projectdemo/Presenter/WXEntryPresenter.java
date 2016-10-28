package com.dany.projectdemo.Presenter;

import android.util.Log;

import com.dany.projectdemo.Contract.WXEntryContract;
import com.dany.projectdemo.model.WXBean;
import com.dany.projectdemo.model.WXUserBean;
import com.dany.projectdemo.retrofit.Servers.WXEntryServers;

import rx.Subscriber;

/**
 * 说明:
 * 作者：mint.zhang
 * 时间：2016/10/28 16:46
 */
public class WXEntryPresenter implements WXEntryContract.Presenter {
    WXEntryContract.View view;

    private String accesstoken, openid, nickname, unionid, headurl;
    private int sex = 0;

    public WXEntryPresenter(WXEntryContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }


    /*
     *获取授权信息
     */
    @Override
    public void getWXToken(String code) {
        WXEntryServers.getWXToken(code, new Subscriber<WXBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(WXBean wxBean) {
                String access_token = wxBean.getAccess_token();
                String openid = wxBean.getOpenid();
                Log.i("wx", access_token + openid);
                getWXUserInfo();
            }
        });
    }

    /*
     *获取微信用户信息
     */
    @Override
    public void getWXUserInfo() {
        if (accesstoken != null && openid != null) {
            WXEntryServers.getWXUserInfo(accesstoken, openid, new Subscriber<WXUserBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(WXUserBean wxUserBean) {
                    nickname = wxUserBean.getNickname();
                    sex = wxUserBean.getSex();
                    unionid = wxUserBean.getUnionid();
                    headurl = wxUserBean.getHeadimgurl();
                    Log.i("wx", nickname + sex + unionid + headurl);
                    requestLogin();
                }
            });
        }
    }

    /*
     *第三方登录
     */
    @Override
    public void requestLogin() {

    }

    @Override
    public void start() {

    }
}
