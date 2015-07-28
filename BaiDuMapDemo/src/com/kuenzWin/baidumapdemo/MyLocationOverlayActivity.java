package com.kuenzWin.baidumapdemo;

import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;

//假如用到位置提醒功能，需要import该类

public class MyLocationOverlayActivity extends BaseActivity {

	private LocationClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.location();
		client.start();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private void location() {
		// 声明LocationClient类

		client = new LocationClient(getApplicationContext());

		// 设置参数
		// 设置定位参数包括：定位模式（单次定位，定时定位），返回坐标类型，是否打开GPS等等。eg：

		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 设置打开gps
		option.setAddrType("all");// 返回的定位结果包含地址信息
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms
		option.disableCache(true);// 禁止启用缓存定位
		option.setPoiNumber(5);// //最多返回POI个数
		option.setPoiDistance(1000); // poi查询距离
		option.setPoiExtraInfo(true); // 是否需要POI的电话和地址等详细信息

		client.setLocOption(option);

		// mLocationClient.registerLocationListener(
		// myListener
		// );
		// //注册监听函数
		client.registerLocationListener(new MyListener());
	}

	/**
	 * BDLocationListener接口有2个方法需要实现：
	 * 
	 * 
	 * @author 温坤哲
	 * @date 2015-7-28
	 */
	private class MyListener implements BDLocationListener {
		/**
		 * 接收异步返回的定位结果
		 * 
		 * @param BDLocation
		 *            类型参数。
		 */
		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return;
			MyLocationOverlay overlay = new MyLocationOverlay(mv);
			LocationData data = new LocationData();
			data.latitude = location.getLatitude();
			data.longitude = location.getLongitude();
			overlay.setData(data);
			mv.getOverlays().add(overlay);
			mv.refresh();

			GeoPoint point = new GeoPoint((int) (data.longitude * 1E6),
					(int) (data.latitude * 1E6));
			controller.animateTo(point);
		}

		/**
		 * 接收异步返回的POI查询结果
		 * 
		 * @param BDLocation
		 *            类型参数。
		 */
		@Override
		public void onReceivePoi(BDLocation arg0) {

		}

	}

	@Override
	protected void onPause() {
		client.stop();
		super.onPause();
	}

}
