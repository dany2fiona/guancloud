package com.dany.projectdemo.model;

/**
 * 说明: 微信用户信息类
 * 作者：mint.zhang
 * 时间：2016/10/28 13:13
 */
public class WXUserBean {
    private String nickname;
    private int sex;
    private String unionid;
    private String headimgurl;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }
}
