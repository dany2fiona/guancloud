package com.dany.projectdemo.Contract;

import com.dany.projectdemo.Presenter.BasePresenter;

/**
 * 说明:
 * 作者：mint.zhang
 * 时间：2016/10/28 16:40
 */
public interface WXEntryContract {
    interface View extends BaseView {
        void getWXToken();

        void getWXUserInfo();

        void requestLogin();
    }

    interface Presenter extends BasePresenter {
        void getWXToken(String code);

        void getWXUserInfo();

        void requestLogin();
    }
}
