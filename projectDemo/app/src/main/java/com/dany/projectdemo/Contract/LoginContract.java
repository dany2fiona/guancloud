package com.dany.projectdemo.Contract;

import com.dany.projectdemo.view.BaseActivity;

/**
 * 说明:
 * 作者：mint.zhang
 * 时间：2016/10/31 18:17
 */
public interface LoginContract {
    interface View extends BaseView {
        void requestPhoneCode();

        void requestPhoneLogin();

        void goMainActivity();
    }

    interface Presenter {
        void requestPhoneCode(String phoneNumber, BaseActivity context);

        void requestPhoneLogin(String phoneNumber, String code, BaseActivity context);


    }
}
