package com.dany.projectdemo.view;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.dany.projectdemo.R;
import com.tencent.mm.sdk.openapi.IWXAPI;


/**
 * 说明: 登录
 * 作者：mint.zhang
 * 时间：2016/10/28
 */
public class LoginActivity extends BaseActivity {
    private IWXAPI iwxapi;// 微信api
    private Context context;


    private EditText phone_edit,code_edit;
    private Button phone_login;
    private ImageView wx_iv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context=this;


        initView();
    }

    private void initView() {
        iwxapi =MyApplication.getContext().getIWXAPI();




    }
}
