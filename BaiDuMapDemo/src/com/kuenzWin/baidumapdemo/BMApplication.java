package com.kuenzWin.baidumapdemo;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;
import com.kuenzWin.baidumapdemo.utils.Constant;

public class BMApplication extends Application {

	private static BMApplication mInstance = null;
	public boolean m_bKeyRight = true;
	BMapManager mBMapManager = null;

	public static final String strKey = Constant.KEY;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		initEngineManager(this);
	}

	public void initEngineManager(Context context) {
		if (mBMapManager == null) {
			mBMapManager = new BMapManager(context);
		}

		// 发送key到百度地图的服务器验证，一旦验证通过才有数据
		/*
		 * @param strKey - 申请的授权验证码
		 * 
		 * @param listener - 注册回调事件(该接口返回网络状态，授权验证等结果，用户需要实现该接口以处理相应事件)
		 */
		if (!mBMapManager.init(strKey, new MyGeneralListener())) {
			Toast.makeText(
					BMApplication.getInstance().getApplicationContext(),
					"BMapManager  初始化错误!", Toast.LENGTH_LONG).show();
		}
	}

	public static BMApplication getInstance() {
		return mInstance;
	}

	// 常用事件监听，用来处理通常的网络错误，授权验证错误等
	static class MyGeneralListener implements MKGeneralListener {
		/**
		 * 返回网络错误
		 * 
		 * @param iError
		 *            - 错误号（常量没有网络，由KeyEvent管理）
		 */
		@Override
		public void onGetNetworkState(int iError) {
			if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
				Toast.makeText(
						BMApplication.getInstance().getApplicationContext(),
						"您的网络出错啦！", Toast.LENGTH_LONG).show();
			} else if (iError == MKEvent.ERROR_NETWORK_DATA) {
				Toast.makeText(
						BMApplication.getInstance().getApplicationContext(),
						"输入正确的检索条件！", Toast.LENGTH_LONG).show();
			}
			// ...
		}

		/**
		 * 返回授权验证错误
		 * 
		 * @param iError
		 *            - 错误号: 300,验证失败（常量验证失败，由KeyEvent管理）
		 */
		@Override
		public void onGetPermissionState(int iError) {
			if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
				// 授权Key错误：
				Toast.makeText(
						BMApplication.getInstance().getApplicationContext(),
						"请在 DemoApplication.java文件输入正确的授权Key！",
						Toast.LENGTH_LONG).show();
				BMApplication.getInstance().m_bKeyRight = false;
			}
		}
	}
}