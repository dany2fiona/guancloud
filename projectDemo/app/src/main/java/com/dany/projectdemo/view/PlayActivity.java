package com.dany.projectdemo.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dany.projectdemo.R;
import com.dany.projectdemo.common.utils.MyUser;

import java.util.ArrayList;
import java.util.List;

import tv.buka.sdk.entity.Stream;
import tv.buka.sdk.entity.User;
import tv.buka.sdk.listener.ConnectListener;
import tv.buka.sdk.listener.StreamListener;
import tv.buka.sdk.listener.UserListener;
import tv.buka.sdk.manager.ConnectManager;
import tv.buka.sdk.manager.MediaManager;
import tv.buka.sdk.manager.UserManager;
/*
 *观看直播
 */
public class PlayActivity extends BaseActivity implements View.OnClickListener {

    private String username = "";

    private LinearLayout content_layout;

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
    private TextView play_tv, stop_tv;

    private String roomid;

    private List<Stream> streamList = new ArrayList<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 3) {
                play();
                return;
            }
            if (msg.what == 4) {
                Toast.makeText(PlayActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();
                return;
            }


            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            lp.setMargins(0, 5, 0, 5);
            lp.gravity = Gravity.CENTER_HORIZONTAL;
            SurfaceView sv = (SurfaceView) msg.obj;
            if (msg.what == FLAG_PLAY_STARTED_STREAM) {
                content_layout.addView(sv, lp);
            }
            if (msg.what == FLAG_PLAY_CLOSED_STREAM) {
                content_layout.removeView(sv);
            }

        }


    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        roomid = getIntent().getStringExtra("roomid");
        username = MyUser.getUserName(this);
        Log.i("roomid+username", roomid + "+" + username);

        MediaManager.getInstance().setLoudspeakerStatus(true, this);
        UserManager.getInstance().addListener(mUserListener);
        ConnectManager.getInstance().addListener(mConnectListener);
        initView();
    }


    @Override
    public void onStart() {
        MediaManager.getInstance().applicationWillResignActive();
        super.onStart();
    }

    private void initView() {
        content_layout = (LinearLayout) findViewById(R.id.content);
        play_tv = (TextView) findViewById(R.id.play_tv);
        stop_tv = (TextView) findViewById(R.id.stop_tv);
        play_tv.setOnClickListener(this);
        stop_tv.setOnClickListener(this);

        try {
            ConnectManager.getInstance().connect(roomid, username, "", username, 1);
        } catch (Exception e) {
            Log.i("exception", e.getMessage());
        }


    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    /*
     *播放
     */
    private void play() {
        try {
            streamList = MediaManager.getInstance().getStreamList();
            Log.i("min", streamList.size() + "");
        } catch (Exception e) {
            Log.i("exception", e.getMessage());
        }
        if (streamList.size() == 0) {
            return;
        }
        stream = streamList.get(0);

        playSv = MediaManager.getInstance()
                .createSurfaceView(this);


        MediaManager.getInstance().startPlay(playSv, stream,
                mStreamListener);
    }

    /*
     *停止播放
     */
    private void stop() {
        content_layout.removeView(MediaManager.getInstance().stopPlay());
        /*new Thread(new Runnable() {

            @Override
            public void run() {
                SurfaceView sv = MediaManager.getInstance().stopPlay();
                if (sv != null) {
                    sendSurfaceViewHandler(FLAG_PLAY_CLOSED_STREAM, sv);
                }

            }
        }).start();*/
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
            Log.i("play", "登录成功！");
            // 登录成功
            handler.sendEmptyMessage(3);
        }

        @Override
        public void onUserLoginError() {
            // 登录失败
            handler.sendEmptyMessage(4);

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
            // TODO Auto-generated method stub
        }

        @Override
        public void onStreamPublishError(SurfaceView surfaceView) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStreamPlaySuccess(Stream stream, SurfaceView surfaceView) {
            // TODO Auto-generated method stub
            sendSurfaceViewHandler(FLAG_PLAY_STARTED_STREAM, surfaceView);
        }

        @Override
        public void onStreamPlayError(SurfaceView surfaceView) {
            // TODO Auto-generated method stub
        }
    };

    private void sendSurfaceViewHandler(int what, SurfaceView sv) {
        Message msg = new Message();
        msg.what = what;
        msg.obj = sv;
        handler.sendMessage(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_tv:
                break;
            case R.id.stop_tv:
                stop();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ConnectManager.getInstance().disconnect();
        UserManager.getInstance().removeListener(mUserListener);
        ConnectManager.getInstance().removeListener(mConnectListener);
    }
}
