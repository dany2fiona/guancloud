package com.dany.projectdemo.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dany.projectdemo.R;

/**
 * Created by dan.y on 2016/10/27.
 */
public class LoadingDialog extends Dialog{
    private static LoadingDialog mProgrssDialog;
    private static ImageView mLoadingImageView;
    private static Context mContext;

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    public static LoadingDialog createProgrssDialog(Context context) {
        mContext = context;
        mProgrssDialog = new LoadingDialog(context,
                R.style.loading_view_dialog);
        mProgrssDialog.setContentView(R.layout.dialog_loading_view);
        mProgrssDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        mProgrssDialog.setCanceledOnTouchOutside(false);

        return mProgrssDialog;

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (null == mProgrssDialog)
            return;
         mLoadingImageView = (ImageView) mProgrssDialog
                .findViewById(R.id.ivLoading);
        Glide.with(mContext).load(R.mipmap.loading).asGif().into(mLoadingImageView);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mLoadingImageView = null;
        mProgrssDialog = null;
        System.gc();
    }

}
