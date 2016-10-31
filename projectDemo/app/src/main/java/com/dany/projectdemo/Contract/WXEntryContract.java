package com.dany.projectdemo.Contract;

import com.dany.projectdemo.view.BaseActivity;

/**
 * 说明:
 * 作者：mint.zhang
 * 时间：2016/10/28 16:40
 */
public interface WXEntryContract {
    interface View extends BaseView {
        void goMainActivity();
    }

    interface Presenter {
        void getWXToken(String code, BaseActivity context);

        void getWXUserInfo(BaseActivity context);

        void requestLogin(BaseActivity context);
    }
}
