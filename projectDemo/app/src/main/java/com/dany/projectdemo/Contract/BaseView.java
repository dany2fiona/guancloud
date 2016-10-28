package com.dany.projectdemo.Contract;

import android.content.Context;

/**
 * Created by dan.y on 2016/10/25.
 */
public interface BaseView<T> {
    void setPresenter(T presenter);
}
