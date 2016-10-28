package com.dany.projectdemo.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.dany.projectdemo.R;
import com.dany.projectdemo.common.utils.MyCookie;

/**
 * Created by dan.y on 2016/10/27.
 */
public class WelcomeActivity extends Activity {
    private static final int GO_GUIDE = 0x0001;
    private static final int GO_HOME = 0x0002;
    boolean isFirstIn = false;

    // 延迟1秒
    private static final long SPLASH_DELAY_MILLIS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initPanel();
    }

    /**
     * Handler:跳转到不同界面
     */
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void initPanel(){
        // 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
        isFirstIn = MyCookie.getInstance().getIsFirstIn();
        // 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
        if (!isFirstIn) {
            // 使用Handler的postDelayed方法，1秒后执行跳转到MainActivity
            mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
        } else {
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
        }
    }

    private void goHome(){
//        Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
        Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
        startActivity(intent);
        WelcomeActivity.this.finish();
    }

    private void goGuide(){
        Intent intent = new Intent(WelcomeActivity.this,GuideActivity.class);
        startActivity(intent);
        WelcomeActivity.this.finish();
    }
}
