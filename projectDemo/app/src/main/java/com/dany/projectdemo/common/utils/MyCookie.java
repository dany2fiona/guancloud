package com.dany.projectdemo.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.dany.projectdemo.view.MyApplication;

/**
 * Created by dan.y on 2016/10/27.
 */
public class MyCookie {
    private SharedPreferences mCookie;
    private SharedPreferences.Editor mEditor;
    private final String Tag_isFirstIn = "tag_isfirstin";

    public static MyCookie getInstance()
    {
        return Singleton.Instance;
    }

    private static class Singleton
    {
        static final MyCookie Instance = new MyCookie(MyApplication.getContext().getSharedPreferences("my_sp_cookie", Context.MODE_PRIVATE));
        private Singleton(){
        }
    }

    public MyCookie(SharedPreferences cookie){
        this.mCookie = cookie;
    }
    public MyCookie getEditor(){
        if (this.mEditor == null){
            this.mEditor = mCookie.edit();
        }
        return this;
    }

    public void commit(){
        this.mEditor.commit();
        this.mEditor = null;
    }

    public void putIsFirstIn(Boolean isFirstIn){
        this.getEditor();
        this.mEditor.putBoolean(Tag_isFirstIn, isFirstIn);
        commit();
    }

    public Boolean getIsFirstIn(){
        return mCookie.getBoolean(Tag_isFirstIn, true);
    }

}
