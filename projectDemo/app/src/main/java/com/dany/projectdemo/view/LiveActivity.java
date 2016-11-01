package com.dany.projectdemo.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dany.projectdemo.Contract.LiveContract;
import com.dany.projectdemo.Presenter.LivePresenter;
import com.dany.projectdemo.R;
import com.dany.projectdemo.common.utils.MyUser;
import com.dany.projectdemo.common.utils.ToastUtils;

import tv.buka.sdk.entity.Stream;
import tv.buka.sdk.entity.User;
import tv.buka.sdk.entity.setting.CameraConfig;
import tv.buka.sdk.entity.setting.MediaConfig;
import tv.buka.sdk.listener.ConnectListener;
import tv.buka.sdk.listener.MediaListener;
import tv.buka.sdk.listener.StreamListener;
import tv.buka.sdk.listener.UserListener;
import tv.buka.sdk.manager.ConnectManager;
import tv.buka.sdk.manager.MediaManager;
import tv.buka.sdk.manager.UserManager;

/*
 *开启直播
 */
public class LiveActivity extends BaseActivity implements LiveContract.View {
    private LiveContract.Presenter mPresenter;
    private String username = "";
    private String myroomid = "";
    private String pk = "";

    private LinearLayout content_layout;
    private TextView title_tv;
    private LinearLayout toolbar_back;

    private Boolean isLive = false;//是否正在直播

    private final int FLAG_PLAY_STARTED_STREAM = 1;
    private final int FLAG_PLAY_CLOSED_STREAM = 2;
    private final int FLAG_PUBLISH_STARTED_STREAM = 3;
    private final int FLAG_PUBLISH_CLOSED_STREAM = 4;
    private final int FLAG_MAINACTIVITY_DISMISS = 5;
    private final int FLAG_MAINACTIVITY_SHOW = 6;

    private int width = 640;//640
    private int height = 480;//480

    private SurfaceView playSv;
    private Stream stream;
    private Button stop_btn;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                live();
                return;
            }
            if (msg.what == 2) {
                Toast.makeText(LiveActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();
                return;
            }

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            lp.setMargins(0, 5, 0, 5);
            lp.gravity = Gravity.CENTER_HORIZONTAL;
            SurfaceView sv = (SurfaceView) msg.obj;
            if (msg.what == FLAG_PUBLISH_STARTED_STREAM) {
                content_layout.addView(sv, lp);
            }
            if (msg.what == FLAG_PUBLISH_CLOSED_STREAM) {
                content_layout.removeView(sv);
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        MediaManager.getInstance().setLoudspeakerStatus(true, this);
        UserManager.getInstance().addListener(mUserListener);
        ConnectManager.getInstance().addListener(mConnectListener);
        init();
    }

    private void init() {
        new LivePresenter(this);
        pk = MyUser.getUserRoomPk(this) + "";
        Log.i("dan.y", "pk:" + pk);
        myroomid = MyUser.getUserRoomID(this);
        username = MyUser.getUserName(this);
        Log.i("roomid+username", myroomid + "+" + username);

        stop_btn = (Button) findViewById(R.id.stop_btn);
        content_layout = (LinearLayout) findViewById(R.id.content);
        title_tv = (TextView) findViewById(R.id.tv_toobar_title);
        toolbar_back = (LinearLayout) findViewById(R.id.toolbar_back);

        title_tv.setText("我的房间：" + myroomid);
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backAction();
            }
        });

        ConnectManager.getInstance().connect(myroomid, username, "", username, 1);
        showWaiting();

        stop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopLive();
            }
        });
    }

    private void backAction() {
        if (isLive) {
            ToastUtils.show(LiveActivity.this, "您正在直播，请关闭后退出！");
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        backAction();
        super.onBackPressed();
    }

    /*
         *开始直播
         */
    private void live() {
        if (UserManager.getInstance().isLogin()) {

            final MediaConfig config = new MediaConfig();
            // config.setAudioCodecType(AudioCodecType.AudioCodecTypeOPUS);
            config.setHeight(height);
            config.setWidth(width);
            config.setMaxBitrate(1200);
            config.setStartBitrate(500);
            config.setTargetBitrate(1000);
            if (MediaManager.getInstance().isPublish()) {
                SurfaceView sv = MediaManager.getInstance().stopPublish();
                if (sv != null) {
                    sendSurfaceViewHandler(FLAG_PUBLISH_CLOSED_STREAM, sv);
                }

            } else {
                final SurfaceView sv = MediaManager.getInstance()
                        .createSurfaceView(this);
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        CameraConfig cameraConfig = new CameraConfig();
                        cameraConfig.setDeviceId(0);
                        MediaManager.getInstance()
                                .startPublishWithLocalCamera(sv,
                                        cameraConfig, config, 3,
                                        mStreamListener);
                    }
                }).start();

            }
        } else {
            Toast.makeText(this, R.string.alert_not_login,
                    Toast.LENGTH_SHORT).show();
        }
    }

    //停止直播
    private void stopLive() {
        if (MediaManager.getInstance().isPublish()) {
            SurfaceView sv = MediaManager.getInstance().stopPublish();
            if (sv != null) {
                sendSurfaceViewHandler(FLAG_PUBLISH_CLOSED_STREAM, sv);
                isLive = false;
                mPresenter.stopLive(this, pk, false);
            }


        }

    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        MediaManager.getInstance().applicationWillResignActive();
        super.onStart();
    }

    private UserListener mUserListener = new UserListener() {

        @Override
        public void onUserOut(User user) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onUserIn(User user) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onUserChanged() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onUserNumChanged(int userNum) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onUserLoginSuccess() {
            Log.i("live", "登录成功！");
            // 登录成功
            handler.sendEmptyMessage(1);
        }

        @Override
        public void onUserLoginError() {
            // 登录失败
            handler.sendEmptyMessage(2);
        }

        @Override
        public void onUserOff() {
            // TODO Auto-generated method stub

        }

    };

    private ConnectListener mConnectListener = new ConnectListener() {

        @Override
        public void onDisconnected() {
            //断开

        }

        @Override
        public void onConnected() {
            //连接
        }
    };

    private StreamListener mStreamListener = new StreamListener() {

        @Override
        public void onStreamPublishSuccess(Stream stream,
                                           SurfaceView surfaceView) {
            //发送到我们的服务器通知已开通直播
            mPresenter.modifyRoom(LiveActivity.this, pk, true, surfaceView);
        }

        @Override
        public void onStreamPublishError(SurfaceView surfaceView) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStreamPlaySuccess(Stream stream, SurfaceView surfaceView) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStreamPlayError(SurfaceView surfaceView) {
            // TODO Auto-generated method stub

        }
    };


    private MediaListener mListener = new MediaListener() {


        @Override
        public void onStreamCreated(Stream stream) {

        }

        @Override
        public void onStreamDestroyed(Stream stream) {

        }

        @Override
        public void onStreamChanged() {

        }

        @Override
        public void onPlayDisconnet(Stream stream) {

        }

        @Override
        public void onPublishDisconnet(Stream stream) {

        }
    };

    private void sendSurfaceViewHandler(int what, SurfaceView sv) {
        Message msg = new Message();
        msg.what = what;
        msg.obj = sv;
        handler.sendMessage(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ConnectManager.getInstance().disconnect();
        UserManager.getInstance().removeListener(mUserListener);
        ConnectManager.getInstance().removeListener(mConnectListener);
    }

    @Override
    public void showModifiedRoomLive(SurfaceView surfaceView) {
        //显示直播
        isLive = true;
        sendSurfaceViewHandler(FLAG_PUBLISH_STARTED_STREAM, surfaceView);

    }

    @Override
    public void setPresenter(Object presenter) {
        mPresenter = (LiveContract.Presenter) presenter;
    }
}
