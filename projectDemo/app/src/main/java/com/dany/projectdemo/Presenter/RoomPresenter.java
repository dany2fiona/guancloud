package com.dany.projectdemo.Presenter;

import android.widget.Toast;

import com.dany.projectdemo.Contract.RoomContract;
import com.dany.projectdemo.model.Room;
import com.dany.projectdemo.retrofit.Servers.RoomServers;
import com.dany.projectdemo.retrofit.utils.BaseSubscriber;
import com.dany.projectdemo.view.BaseActivity;
import com.dany.projectdemo.view.MyApplication;

import java.util.List;


/**
 * Created by dan.y on 2016/10/27.
 */
public class RoomPresenter implements RoomContract.Presenter {
    RoomContract.View view;

    public RoomPresenter(RoomContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadRoom(final BaseActivity context) {
        RoomServers.getRooms(new BaseSubscriber<List<Room.ResultsBean>>(context) {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Room.ResultsBean> resultsBeens) {
                context.stopWaiting();
                if (resultsBeens.size() == 0) {
                    Toast.makeText(MyApplication.getContext(), "resultsBeens.size()==" + resultsBeens.size(), Toast.LENGTH_SHORT).show();
                } else {
                    view.showRoom(resultsBeens);
                    Toast.makeText(MyApplication.getContext(), "resultsBeens.size()==" + resultsBeens.size(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
