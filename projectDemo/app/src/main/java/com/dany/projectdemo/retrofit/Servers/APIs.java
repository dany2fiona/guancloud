package com.dany.projectdemo.retrofit.Servers;

import com.dany.projectdemo.model.Room;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by dan.y on 2016/10/25.
 */
public interface APIs {
    @GET("rooms/")
    Observable<Room> getRooms();
}
