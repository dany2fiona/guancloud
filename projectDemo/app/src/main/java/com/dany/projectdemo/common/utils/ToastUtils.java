package com.dany.projectdemo.common.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 说明: Toast工具类
 * 作者：mint.zhang
 * 时间：2016/10/28 15:45
 */
public class ToastUtils {
		
	public static void show(Context context, String msg){
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	
	public static void show(Context context, int resMsg){
		Toast.makeText(context, context.getResources().getString(resMsg), Toast.LENGTH_SHORT).show();
	}

}
