package com.dany.projectdemo.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dany.projectdemo.R;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;


/**
 * 说明: 登录
 * 作者：mint.zhang
 * 时间：2016/10/28
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private IWXAPI iwxapi;// 微信api
    private Context context;


    private EditText phone_edit, code_edit;
    private Button phone_login;
    private ImageView wx_iv;
    private TextView get_code_tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        initView();
    }

    private void initView() {
        iwxapi = MyApplication.getContext().getIWXAPI();

        phone_edit = (EditText) findViewById(R.id.login_phone_number);
        code_edit = (EditText) findViewById(R.id.login_code);
        phone_login = (Button) findViewById(R.id.phone_login);
        wx_iv= (ImageView) findViewById(R.id.login_wx);
        get_code_tv= (TextView) findViewById(R.id.get_code_tv);

        phone_login.setOnClickListener(this);
        wx_iv.setOnClickListener(this);
        get_code_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.phone_login://手机号登录
                break;
            case R.id.login_wx://微信授权登录
                Wechat();
                break;
            case R.id.get_code_tv://请求发送验证码
                break;
        }
    }

    /*
     *拉起微信，进行登录
     */
    private void Wechat() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        iwxapi.sendReq(req);
    }




}
