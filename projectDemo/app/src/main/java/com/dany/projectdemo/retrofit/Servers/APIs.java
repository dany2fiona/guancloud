package com.dany.projectdemo.retrofit.Servers;

import com.dany.projectdemo.model.ModifyRoom;
import com.dany.projectdemo.model.Room;
import com.dany.projectdemo.model.UserBean;
import com.dany.projectdemo.model.WXBean;
import com.dany.projectdemo.model.WXUserBean;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dan.y on 2016/10/25.
 */
public interface APIs {
    //查询所有直播房间
    @GET("rooms/")
    Observable<Room> getRooms(@Query("page") int pageIndex);
    //新增一个直播房间
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @PUT("rooms/{pk}/")
    Observable<ModifyRoom> putRoom(@Path("pk") String pk, @Body ModifyRoom modifyRoom);


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


}
