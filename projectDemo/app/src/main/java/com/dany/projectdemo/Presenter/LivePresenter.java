package com.dany.projectdemo.Presenter;

import android.view.SurfaceView;
import android.widget.Toast;

import com.dany.projectdemo.Contract.LiveContract;
import com.dany.projectdemo.common.utils.ToastUtils;
import com.dany.projectdemo.model.ModifyRoom;
import com.dany.projectdemo.retrofit.Servers.RoomServers;
import com.dany.projectdemo.retrofit.utils.BaseSubscriber;
import com.dany.projectdemo.view.BaseActivity;
import com.dany.projectdemo.view.MyApplication;

/**
 * Created by dan.y on 2016/11/1.
 */
public class LivePresenter implements LiveContract.Presenter {
    LiveContract.View view;

    public LivePresenter(LiveContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void modifyRoom(final BaseActivity context, String roomPk, boolean active, final SurfaceView surfaceView) {
        RoomServers.putRoom(roomPk, true, new BaseSubscriber<ModifyRoom>(context) {
            @Override
            public void onError(Throwable e) {
                Toast.makeText(MyApplication.getContext(), "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                context.stopWaiting();
            }

            @Override
            public void onNext(ModifyRoom room) {
                context.stopWaiting();
                //显示直播
                view.showModifiedRoomLive(surfaceView);
            }
        });
    }

    @Override
    public void stopLive(final BaseActivity context, String roomPk, boolean active) {
        RoomServers.putRoom(roomPk, false, new BaseSubscriber<ModifyRoom>(context) {
            @Override
            public void onError(Throwable e) {
                Toast.makeText(MyApplication.getContext(), "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(ModifyRoom room) {
                //结束直播
                ToastUtils.show(context, "退出成功！");
                context.finish();
            }
        });
    }


}
