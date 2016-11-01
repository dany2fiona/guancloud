package com.dany.projectdemo.Contract;

import android.view.SurfaceView;

import com.dany.projectdemo.view.BaseActivity;

/**
 * Created by dan.y on 2016/11/1.
 */
public interface LiveContract{
    interface View extends BaseView{
        void showModifiedRoomLive(SurfaceView surfaceView);
    }
    interface Presenter{
        void modifyRoom(BaseActivity context,String roomPk,boolean active,SurfaceView surfaceView);

        void stopLive(BaseActivity context,String roomPk,boolean active);
    }
}
