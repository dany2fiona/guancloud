package com.dany.projectdemo.Presenter;

import android.content.Context;

import com.dany.projectdemo.Contract.LoginContract;
import com.dany.projectdemo.common.utils.MyUser;
import com.dany.projectdemo.common.utils.ToastUtils;
import com.dany.projectdemo.model.PhoneCodeBean;
import com.dany.projectdemo.model.UserBean;
import com.dany.projectdemo.retrofit.Servers.LoginServers;
import com.dany.projectdemo.retrofit.utils.BaseSubscriber;
import com.dany.projectdemo.view.BaseActivity;

/**
 * 说明:
 * 作者：mint.zhang
 * 时间：2016/10/31 18:19
 */
public class LoginPresenter implements LoginContract.Presenter {
    LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void requestPhoneCode(String phoneNumber, final BaseActivity context) {
        LoginServers.getMsgCode(phoneNumber, new BaseSubscriber<PhoneCodeBean>(context) {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(PhoneCodeBean phoneCodeBean) {
                context.showWaiting();
                String status = phoneCodeBean.getStatus();
                if (status.equalsIgnoreCase("success")) {
                    ToastUtils.show(context, "短信验证码已发送，请查收！");
                }

            }
        });
    }

    @Override
    public void requestPhoneLogin(String phoneNumber, String code, final BaseActivity context) {
        LoginServers.requestLogin(phoneNumber, code, new BaseSubscriber<UserBean>(context) {
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
