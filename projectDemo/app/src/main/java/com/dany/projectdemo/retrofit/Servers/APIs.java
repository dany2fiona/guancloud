package com.dany.projectdemo.retrofit.Servers;

import com.dany.projectdemo.model.PhoneCodeBean;
import com.dany.projectdemo.model.Room;
import com.dany.projectdemo.model.UserBean;
import com.dany.projectdemo.model.WXBean;
import com.dany.projectdemo.model.WXUserBean;

import retrofit2.http.GET;
import retrofit2.http.POST;
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
    Observable<WXUserBean> getWXUserInfo(@Query("access_token") String access_token, @Query("openid") String openid);

    //用户登录
    @POST("snslogin/")
    Observable<UserBean> getUserInfo(@Query("username") String username, @Query("nickname") String nickname, @Query("login_type") String login_type, @Query("snsid") String snsid, @Query("access_token") String access_token,
                                     @Query("icon") String icon, @Query("sex") String sex);

    //发送手机短信验证码
    @POST("phonelogin/")
    Observable<PhoneCodeBean> requestCode(@Query("phone") String phoneNumber);

    //手机验证码登录
    @GET("phonelogin/")
    Observable<UserBean> requestPhoneLogin(@Query("phone") String phoneNumber, @Query("captcha") String resultCode);


}
