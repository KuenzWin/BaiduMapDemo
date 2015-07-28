package com.kuenzWin.baidumapdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.kuenzWin.baidumapdemo.utils.Constant;

public class BaseActivity extends Activity implements OnClickListener {

	/**
	 * 百度地图引擎管理工具
	 */
	protected BMapManager manager;
	/**
	 * 地图控制器
	 */
	protected MapController controller;
	/**
	 * 显示地图的View。
	 * 
	 * 一个显示地图的视图，当被焦点选中时，它能捕获按键事件和触摸手势去平移和缩放地图。
	 */
	protected MapView mv;

	protected Button pingyi;
	protected Button rotate1;
	protected Button rotate2;
	protected Button big;
	protected Button small;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// checkKey();
		setContentView(R.layout.activity_main);

		mv = (MapView) this.findViewById(R.id.mv);
		pingyi = (Button) this.findViewById(R.id.pingyi);
		rotate1 = (Button) this.findViewById(R.id.rotate1);
		rotate2 = (Button) this.findViewById(R.id.rotate2);
		big = (Button) this.findViewById(R.id.big);
		small = (Button) this.findViewById(R.id.small);

		/**
		 * 设置是否启用内置的缩放控件。如果启用，MapView将自动显示这些缩放控件。
		 * 
		 * @param on
		 *            内置的缩放控件是否启用。如果是false，用户处理缩放控件在界面上的显示。
		 */
		mv.setBuiltInZoomControls(true);

		controller = mv.getController();
		/**
		 * public float setZoom(float zoomLevel) 设置地图的缩放级别。 这个值的取值范围是[3,19]。
		 * 
		 * @param zoomLevel
		 *            - 缩放级别，取值范围是[3,19]
		 * @return 新的缩放级别，取值范围[3,19]。
		 */
		controller.setZoom(Constant.ZOOM);
		/**
		 * public void setCenter(GeoPoint point) 在给定的中心点GeoPoint上设置地图视图。
		 */
		controller.setCenter(Constant.point);

		pingyi.setOnClickListener(this);
		rotate1.setOnClickListener(this);
		rotate2.setOnClickListener(this);
		big.setOnClickListener(this);
		small.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pingyi:
			/**
			 * public void animateTo(GeoPoint point) 将给定的位置点以动画形式移动至地图中心
			 * 对以给定的点GeoPoint，开始动画显示地图。
			 */
			controller.animateTo(Constant.point);
			break;
		case R.id.rotate1:
			// 得到地图旋转角度
			int rotate = mv.getMapRotation();
			// 设置地图旋转角度(旋转角度[0,360])
			controller.setRotation(rotate + 30);
			break;
		case R.id.rotate2:
			// 得到俯视角旋转角度
			int overlooking = mv.getMapOverlooking();
			// 设置俯视角旋转角度(旋转角度[-45,0])
			controller.setOverlooking(overlooking - 5);
			break;
		case R.id.big:
			// 放大一级
			controller.zoomIn();
			break;
		case R.id.small:
			// 缩小一级
			controller.zoomOut();
			break;
		}
	}

	// /**
	// *
	// */
	// private void checkKey() {
	// manager = new BMapManager(getApplicationContext());
	//
	// manager.init(Constant.KEY, new MKGeneralListener() {
	//
	//
	// @Override
	// public void onGetNetworkState(int iError) {
	// if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
	// Toast.makeText(MainActivity.this, "没有网络", Toast.LENGTH_LONG)
	// .show();
	// }
	// }
	//
	//
	// @Override
	// public void onGetPermissionState(int iError) {
	// if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
	// Toast.makeText(MainActivity.this, "验证失败", Toast.LENGTH_LONG)
	// .show();
	// }
	// }
	//
	// });
	// }

	@Override
	protected void onResume() {
		if (mv != null) {
			mv.onResume();
		}
		super.onResume();
	}

	@Override
	protected void onPause() {
		if (mv != null) {
			mv.onPause();
		}
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// 请在程序退出前调用
		if (mv != null) {
			mv.destroy();
		}
		if (manager != null) {
			manager.destroy();
			manager = null;
		}
		super.onDestroy();
	}

	protected class MyMKSearchAdapter implements MKSearchListener {
		/**
		 * 返回地址信息搜索结果
		 * 
		 * @param result
		 *            搜索结果
		 * @param iError
		 *            错误号，0表示正确返回
		 */
		@Override
		public void onGetAddrResult(MKAddrInfo result, int iError) {
		}

		/**
		 * 返回公交车详情信息搜索结果
		 * 
		 * @param result
		 *            搜索结果
		 * @param iError
		 *            错误号，0表示正确返回
		 */
		@Override
		public void onGetBusDetailResult(MKBusLineResult result, int iError) {

		}

		/**
		 * 返回驾乘路线搜索结果
		 * 
		 * @param result
		 *            搜索结果
		 * @param iError
		 *            错误号，0表示正确返回
		 */
		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult result,
				int iError) {

		}

		/**
		 * 返回poi相信信息搜索的结果
		 * 
		 * @param type
		 *            值为GeoSearchManager.GEO_SEARCH_DETAILS
		 * @param iError
		 *            错误号，0表示正确返回
		 */
		@Override
		public void onGetPoiDetailSearchResult(int type, int iError) {

		}

		/**
		 * 返回poi搜索结果
		 * 
		 * @param result
		 *            搜索结果
		 * @param type
		 *            返回结果类型: MKSearch.TYPE_POI_LIST,
		 *            MKSearch.TYPE_AREA_POI_LIST,
		 *            MKSearch.TYPE_AREA_MULTI_POI_LIST, MKSearch.TYPE_CITY_LIST
		 * @param iError
		 *            错误号，0表示正确返回
		 */
		@Override
		public void onGetPoiResult(MKPoiResult result, int type, int iError) {

		}

		/**
		 * 返回联想词信息搜索结果
		 * 
		 * @param result
		 *            搜索结果
		 * @param iError
		 *            错误号，0表示正确返回
		 */
		@Override
		public void onGetSuggestionResult(MKSuggestionResult result, int iError) {

		}

		/**
		 * 返回公交搜索结果
		 * 
		 * @param result
		 *            搜索结果
		 * @param iError
		 *            错误号，0表示正确返回
		 */
		@Override
		public void onGetTransitRouteResult(MKTransitRouteResult result,
				int iError) {

		}

		/**
		 * 返回步行路线搜索结果
		 * 
		 * @param result
		 *            搜索结果
		 * @param iError
		 *            错误号，0表示正确返回
		 */
		@Override
		public void onGetWalkingRouteResult(MKWalkingRouteResult result,
				int iError) {

		}

	}

}
