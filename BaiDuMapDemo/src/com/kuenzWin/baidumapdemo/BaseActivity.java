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
	 * �ٶȵ�ͼ���������
	 */
	protected BMapManager manager;
	/**
	 * ��ͼ������
	 */
	protected MapController controller;
	/**
	 * ��ʾ��ͼ��View��
	 * 
	 * һ����ʾ��ͼ����ͼ����������ѡ��ʱ�����ܲ��񰴼��¼��ʹ�������ȥƽ�ƺ����ŵ�ͼ��
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
		 * �����Ƿ��������õ����ſؼ���������ã�MapView���Զ���ʾ��Щ���ſؼ���
		 * 
		 * @param on
		 *            ���õ����ſؼ��Ƿ����á������false���û��������ſؼ��ڽ����ϵ���ʾ��
		 */
		mv.setBuiltInZoomControls(true);

		controller = mv.getController();
		/**
		 * public float setZoom(float zoomLevel) ���õ�ͼ�����ż��� ���ֵ��ȡֵ��Χ��[3,19]��
		 * 
		 * @param zoomLevel
		 *            - ���ż���ȡֵ��Χ��[3,19]
		 * @return �µ����ż���ȡֵ��Χ[3,19]��
		 */
		controller.setZoom(Constant.ZOOM);
		/**
		 * public void setCenter(GeoPoint point) �ڸ��������ĵ�GeoPoint�����õ�ͼ��ͼ��
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
			 * public void animateTo(GeoPoint point) ��������λ�õ��Զ�����ʽ�ƶ�����ͼ����
			 * ���Ը����ĵ�GeoPoint����ʼ������ʾ��ͼ��
			 */
			controller.animateTo(Constant.point);
			break;
		case R.id.rotate1:
			// �õ���ͼ��ת�Ƕ�
			int rotate = mv.getMapRotation();
			// ���õ�ͼ��ת�Ƕ�(��ת�Ƕ�[0,360])
			controller.setRotation(rotate + 30);
			break;
		case R.id.rotate2:
			// �õ����ӽ���ת�Ƕ�
			int overlooking = mv.getMapOverlooking();
			// ���ø��ӽ���ת�Ƕ�(��ת�Ƕ�[-45,0])
			controller.setOverlooking(overlooking - 5);
			break;
		case R.id.big:
			// �Ŵ�һ��
			controller.zoomIn();
			break;
		case R.id.small:
			// ��Сһ��
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
	// Toast.makeText(MainActivity.this, "û������", Toast.LENGTH_LONG)
	// .show();
	// }
	// }
	//
	//
	// @Override
	// public void onGetPermissionState(int iError) {
	// if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
	// Toast.makeText(MainActivity.this, "��֤ʧ��", Toast.LENGTH_LONG)
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
		// ���ڳ����˳�ǰ����
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
		 * ���ص�ַ��Ϣ�������
		 * 
		 * @param result
		 *            �������
		 * @param iError
		 *            ����ţ�0��ʾ��ȷ����
		 */
		@Override
		public void onGetAddrResult(MKAddrInfo result, int iError) {
		}

		/**
		 * ���ع�����������Ϣ�������
		 * 
		 * @param result
		 *            �������
		 * @param iError
		 *            ����ţ�0��ʾ��ȷ����
		 */
		@Override
		public void onGetBusDetailResult(MKBusLineResult result, int iError) {

		}

		/**
		 * ���ؼݳ�·���������
		 * 
		 * @param result
		 *            �������
		 * @param iError
		 *            ����ţ�0��ʾ��ȷ����
		 */
		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult result,
				int iError) {

		}

		/**
		 * ����poi������Ϣ�����Ľ��
		 * 
		 * @param type
		 *            ֵΪGeoSearchManager.GEO_SEARCH_DETAILS
		 * @param iError
		 *            ����ţ�0��ʾ��ȷ����
		 */
		@Override
		public void onGetPoiDetailSearchResult(int type, int iError) {

		}

		/**
		 * ����poi�������
		 * 
		 * @param result
		 *            �������
		 * @param type
		 *            ���ؽ������: MKSearch.TYPE_POI_LIST,
		 *            MKSearch.TYPE_AREA_POI_LIST,
		 *            MKSearch.TYPE_AREA_MULTI_POI_LIST, MKSearch.TYPE_CITY_LIST
		 * @param iError
		 *            ����ţ�0��ʾ��ȷ����
		 */
		@Override
		public void onGetPoiResult(MKPoiResult result, int type, int iError) {

		}

		/**
		 * �����������Ϣ�������
		 * 
		 * @param result
		 *            �������
		 * @param iError
		 *            ����ţ�0��ʾ��ȷ����
		 */
		@Override
		public void onGetSuggestionResult(MKSuggestionResult result, int iError) {

		}

		/**
		 * ���ع����������
		 * 
		 * @param result
		 *            �������
		 * @param iError
		 *            ����ţ�0��ʾ��ȷ����
		 */
		@Override
		public void onGetTransitRouteResult(MKTransitRouteResult result,
				int iError) {

		}

		/**
		 * ���ز���·���������
		 * 
		 * @param result
		 *            �������
		 * @param iError
		 *            ����ţ�0��ʾ��ȷ����
		 */
		@Override
		public void onGetWalkingRouteResult(MKWalkingRouteResult result,
				int iError) {

		}

	}

}
