package com.dany.projectdemo.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.dany.projectdemo.Contract.WXEntryContract;
import com.dany.projectdemo.Presenter.WXEntryPresenter;
import com.dany.projectdemo.common.utils.Constants;
import com.dany.projectdemo.view.BaseActivity;
import com.dany.projectdemo.view.MainActivity;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler, WXEntryContract.View {

    private IWXAPI api;
    private String token, openid, code;

    private WXEntryContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constants.WXAPPID, true);
        api.handleIntent(getIntent(), this);
        new WXEntryPresenter(this);


    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        super.onNewIntent(intent);

    }

    @Override
    public Intent getIntent() {
        new WXEntryPresenter(this);
        return super.getIntent();
    }



    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {

    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX) {// 分享
            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    Toast.makeText(WXEntryActivity.this, "分享成功!",
                            Toast.LENGTH_SHORT).show();
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    Toast.makeText(WXEntryActivity.this, "分享取消!",
                            Toast.LENGTH_SHORT).show();
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    Toast.makeText(WXEntryActivity.this, "分享被拒绝!",
                            Toast.LENGTH_SHORT).show();
                    break;
            }
            finish();
        } else if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {// 登陆发送广播

            Bundle bundle = new Bundle();
            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    code = ((SendAuth.Resp) resp).code;
                    // 上面的code就是接入指南里要拿到的code
                    if (code != null) {
                        presenter.getWXToken(code, WXEntryActivity.this);
                    }
                    break;

                default:
                    break;
            }
        }

    }


    @Override
    public void setPresenter(Object presenter) {
        this.presenter = (WXEntryContract.Presenter) presenter;
    }

    @Override
    public void goMainActivity() {
        Intent intent = new Intent(WXEntryActivity.this,MainActivity.class);
        startActivity(intent);
        WXEntryActivity.this.finish();
    }
}
