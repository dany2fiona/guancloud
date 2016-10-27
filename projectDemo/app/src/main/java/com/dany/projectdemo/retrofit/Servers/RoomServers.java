package com.dany.projectdemo.retrofit.Servers;

import android.util.Log;

import com.dany.projectdemo.model.Room;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by dan.y on 2016/10/26.
 */
public class RoomServers extends Servers{
    //Get请求 获取room
    public static void getRooms(Subscriber<List<Room.ResultsBean>> subscriber){
        getRooms(service.getRooms(),subscriber);
    }

    private static void getRooms(Observable<Room> observable, Subscriber<List<Room.ResultsBean>> subscriber){
        setSubscribe(observable.map(new Func1<Room, List<Room.ResultsBean>>() {
            @Override
            public List<Room.ResultsBean> call(Room room) {
                Log.i("dan.y", "rooms:" + room.getResults());
                return room.getResults();
            }
        }),subscriber);
    }

}
