package com.dany.projectdemo.model;

/**
 * 说明: 微信授权结果
 * 作者：mint.zhang
 * 时间：2016/10/28 13:10
 */
public class WXBean {
    private String access_token;
    private String openid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
