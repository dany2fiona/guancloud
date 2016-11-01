package com.dany.projectdemo.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.dany.projectdemo.model.UserBean;

/**
 * SharedPreferences存放用户信息
 *
 */
public class MyUser {
    private final static String SHAREDPERENCES_NAME = "myapp_user";

    /**
     * 保存用户数据
     *
     * @param ct
     * @param loginData
     */
    public static void saveUserData(Context ct, UserBean userBean) {

        SharedPreferences preferences = ct.getSharedPreferences(SHAREDPERENCES_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("roompk", userBean.getRoompk());// 唯一标识
        editor.putString("username", userBean.getUsername());// 用户名
        editor.putString("token", userBean.getToken());//
        editor.putString("login_type", userBean.getLogin_type());// 登录类型:0.普通,1.微信,2.QQ,3.微博
        editor.putString("nickname", userBean.getNickname());// 昵称
        editor.putString("sex", (String) userBean.getSex());// 性别 0:男,1:女,2:未知
        editor.putInt("age",userBean.getAge());//  	年龄
        editor.putString("email", (String) userBean.getEmail());// 邮箱
        editor.putString("phone", (String) userBean.getPhone());// 手机号
        editor.putString("img", userBean.getImg());// 头像
        editor.putString("country", (String) userBean.getCountry());// 国家
        editor.putString("city", (String) userBean.getCity());// 城市
        editor.putString("career", (String) userBean.getCareer());// 职业
        editor.putString("roomid", userBean.getRoomid());// 我的房间号
        editor.putString("date_joined", userBean.getJoin_date());// 注册日期
        editor.putString("status", userBean.getStatus());//
        editor.putString("msg", userBean.getMsg());//
        editor.putString("code", userBean.getCode());//
        editor.commit();
    }


    /**
     * 获取Token
     * @param ct
     * @return
     */
    public static String getUserToken(Context ct) {
        SharedPreferences preferences = ct.getSharedPreferences(SHAREDPERENCES_NAME,
                Context.MODE_PRIVATE);
        return preferences.getString("token", "");
    }

    /**
     * 获取roomid
     * @param ct
     * @return
     */
    public static String getUserRoomID(Context ct) {
        SharedPreferences preferences = ct.getSharedPreferences(SHAREDPERENCES_NAME,
                Context.MODE_PRIVATE);
        return preferences.getString("roomid", "");
    }

    /**
     * 获取pk
     * @param ct
     * @return
     */
    public static int getUserRoomPk(Context ct) {
        SharedPreferences preferences = ct.getSharedPreferences(SHAREDPERENCES_NAME,
                Context.MODE_PRIVATE);
        return preferences.getInt("roompk", 0);
    }

    /**
     * 获取username
     * @param ct
     * @return
     */
    public static String getUserName(Context ct) {
        SharedPreferences preferences = ct.getSharedPreferences(SHAREDPERENCES_NAME,
                Context.MODE_PRIVATE);
        return preferences.getString("username", "");
    }
}
