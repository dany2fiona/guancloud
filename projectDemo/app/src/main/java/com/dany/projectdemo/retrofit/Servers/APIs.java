package com.dany.projectdemo.retrofit.Servers;

import com.dany.projectdemo.model.Room;
import com.dany.projectdemo.model.WXBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dan.y on 2016/10/25.
 */
public interface APIs {
    @GET("rooms/")
    Observable<Room> getRooms(@Query("page") int pageIndex);

    //获取微信access_token,openid
    @GET("sns/oauth2/access_token?")
    Observable<WXBean> getWXToken(@Query("appid") String appid, @Query("secret") String secret, @Query("code") String code, @Query("grant_type") String grant_type);

    //获取微信用户信息
    @GET("sns/userinfo?")
    Observable<WXBean> getWXUserInfo(@Query("access_token") String access_token, @Query("openid") String openid);


}
