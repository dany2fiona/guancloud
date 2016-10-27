package com.dany.projectdemo.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;

import com.dany.projectdemo.R;
import com.dany.projectdemo.widget.dialog.LoadingDialog;

/**
 * Created by dan.y on 2016/10/25.
 */
public class BaseActivity extends AppCompatActivity {
    protected LoadingDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        StatusBarCompat.compat(BaseActivity.this,0xFF3399fe);
    }

    protected Activity getRootContext() {
        return getRootContext(this);
    }

    private Activity getRootContext(Activity act) {
        if (act.isChild()) {
            return getRootContext(act.getParent());
        } else {
            return act;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isChild()) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void baseSetContentView(int layoutResId) {
        setContentView(layoutResId);
    }

    // 退出隐藏到后台
    public void keyDownToExit() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    /**
     * 显示加载
     * @param showMessage
     */
    public void showWaiting() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = LoadingDialog.createProgrssDialog(getRootContext());
        progressDialog.show();
    }

    public void stopWaiting() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
