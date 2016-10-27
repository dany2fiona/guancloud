package com.dany.projectdemo.Contract;

import com.dany.projectdemo.Presenter.BasePresenter;
import com.dany.projectdemo.model.Room;

import java.util.List;

/**
 * Created by dan.y on 2016/10/27.
 */
public interface RoomContract {
    interface View extends BaseView{
        void showRoom(List<Room.ResultsBean> list);
        void showDialog();
        void stopDialog();
    }
    interface Presenter extends BasePresenter{
        void loadRoom();
    }
}
