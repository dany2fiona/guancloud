package com.dany.projectdemo.Presenter;

import android.widget.Toast;

import com.dany.projectdemo.Contract.RoomContract;
import com.dany.projectdemo.model.Room;
import com.dany.projectdemo.retrofit.Servers.RoomServers;
import com.dany.projectdemo.retrofit.utils.BaseSubscriber;
import com.dany.projectdemo.view.BaseActivity;
import com.dany.projectdemo.view.MyApplication;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Func1;


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
    public void loadRoom(final BaseActivity context,int pageIndex) {
        final Room[] mRoom = new Room[1];
        RoomServers.getRoomsOrigin(pageIndex, new Func1<Room, List<Room.ResultsBean>>() {
            @Override
            public List<Room.ResultsBean> call(Room room) {
                mRoom[0] =room;
                return room.getResults();
            }
        },new BaseSubscriber<List<Room.ResultsBean>>(context) {
            @Override
            public void onError(Throwable e) {
                Toast.makeText(MyApplication.getContext(), "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                context.stopWaiting();
                view.stopRefresh();
            }

            @Override
            public void onNext(List<Room.ResultsBean> resultsBeens) {
                context.stopWaiting();
//                if (resultsBeens.size() == 0) {
//                    view.showToolBarNumber(mRoom[0].getTotal());
//                    view.showRoom(new ArrayList<Room.ResultsBean>() {
//                    });
//                } else {
                int currentPage = Integer.parseInt(mRoom[0].getCurrentPage());
                int totalPage = Integer.parseInt(mRoom[0].getTotalPage());
                    view.showToolBarNumber(mRoom[0].getTotal());
                    view.isToEnd(currentPage < totalPage?false:true);
                    view.showRoom(resultsBeens);
//                }
            }
        });

    }

}
