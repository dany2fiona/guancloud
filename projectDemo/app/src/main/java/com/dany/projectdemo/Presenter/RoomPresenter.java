package com.dany.projectdemo.Presenter;

import android.content.Context;
import android.util.Log;

import com.dany.projectdemo.Contract.RoomContract;
import com.dany.projectdemo.model.Room;
import com.dany.projectdemo.retrofit.Servers.RoomServers;

import java.util.List;

import rx.Subscriber;

/**
 * Created by dan.y on 2016/10/27.
 */
public class RoomPresenter implements RoomContract.Presenter{
    RoomContract.View view;

    public RoomPresenter(RoomContract.View view){
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadRoom() {
        RoomServers.getRooms(
                new Subscriber<List<Room.ResultsBean>>() {
                    @Override
                    public void onCompleted() {
                        view.stopDialog();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Room.ResultsBean> resultsBeens) {
                        view.showRoom(resultsBeens);
                    }
                });
    }

    @Override
    public void start() {
        view.showDialog();
    }
}
