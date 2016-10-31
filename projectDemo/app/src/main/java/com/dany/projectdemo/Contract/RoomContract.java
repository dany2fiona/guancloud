package com.dany.projectdemo.Contract;

import android.content.Context;

import com.dany.projectdemo.model.Room;
import com.dany.projectdemo.view.BaseActivity;

import java.util.List;

/**
 * Created by dan.y on 2016/10/27.
 */
public interface RoomContract {
    interface View extends BaseView{
        void showRoom(List<Room.ResultsBean> list);
        void stopRefresh();
        void showToolBarNumber(String total);
        void isToEnd(boolean isToEnd);
    }
    interface Presenter{
        void loadRoom(BaseActivity context,int pageIndex);
    }
}
