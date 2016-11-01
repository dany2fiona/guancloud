package com.dany.projectdemo.Presenter;

import android.content.Context;
import android.util.Log;

import com.dany.projectdemo.Contract.WXEntryContract;
import com.dany.projectdemo.common.utils.MyUser;
import com.dany.projectdemo.model.UserBean;
import com.dany.projectdemo.model.WXBean;
import com.dany.projectdemo.model.WXUserBean;
import com.dany.projectdemo.retrofit.Servers.UserSevers;
import com.dany.projectdemo.retrofit.Servers.WXEntryServers;
import com.dany.projectdemo.retrofit.utils.BaseSubscriber;
import com.dany.projectdemo.view.BaseActivity;

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
    public void getWXToken(String code, final BaseActivity context) {
        WXEntryServers.getWXToken(code, new BaseSubscriber<WXBean>(context) {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(WXBean wxBean) {

                accesstoken = wxBean.getAccess_token();
                openid = wxBean.getOpenid();
                Log.i("---wx---", accesstoken + openid);
                getWXUserInfo(context);
            }
        });

    }

    /*
     *获取微信用户信息
     */
    @Override
    public void getWXUserInfo(final BaseActivity context) {
        if (accesstoken != null && openid != null) {
            WXEntryServers.getWXUserInfo(accesstoken, openid, new BaseSubscriber<WXUserBean>(context) {
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
                    requestLogin(context);
                }
            });

        }
    }

    /*
     *第三方登录
     */
    @Override
    public void requestLogin(final BaseActivity context) {
        UserSevers.getLogin(nickname, nickname, "1", openid, accesstoken, headurl, sex + "",  new BaseSubscriber<UserBean>(context) {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(UserBean userBean) {
                context.stopWaiting();
                if (userBean.getStatus().equalsIgnoreCase("SUCCESS")) {
                    saveUserInfo(userBean);
                    view.goMainActivity();
                }
            }
        });
    }

    /*
    *保存用戶信息
    */
    private void saveUserInfo(UserBean userBean) {
        MyUser.saveUserData((Context) view, userBean);
    }


}
