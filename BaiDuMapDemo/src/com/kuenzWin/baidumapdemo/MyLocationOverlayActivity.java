package com.kuenzWin.baidumapdemo;

import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;

//�����õ�λ�����ѹ��ܣ���Ҫimport����

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
		// ����LocationClient��

		client = new LocationClient(getApplicationContext());

		// ���ò���
		// ���ö�λ������������λģʽ�����ζ�λ����ʱ��λ���������������ͣ��Ƿ��GPS�ȵȡ�eg��

		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// ���ô�gps
		option.setAddrType("all");// ���صĶ�λ���������ַ��Ϣ
		option.setCoorType("bd09ll");// ���صĶ�λ����ǰٶȾ�γ��,Ĭ��ֵgcj02
		option.setScanSpan(5000);// ���÷���λ����ļ��ʱ��Ϊ5000ms
		option.disableCache(true);// ��ֹ���û��涨λ
		option.setPoiNumber(5);// //��෵��POI����
		option.setPoiDistance(1000); // poi��ѯ����
		option.setPoiExtraInfo(true); // �Ƿ���ҪPOI�ĵ绰�͵�ַ����ϸ��Ϣ

		client.setLocOption(option);

		// mLocationClient.registerLocationListener(
		// myListener
		// );
		// //ע���������
		client.registerLocationListener(new MyListener());
	}

	/**
	 * BDLocationListener�ӿ���2��������Ҫʵ�֣�
	 * 
	 * 
	 * @author ������
	 * @date 2015-7-28
	 */
	private class MyListener implements BDLocationListener {
		/**
		 * �����첽���صĶ�λ���
		 * 
		 * @param BDLocation
		 *            ���Ͳ�����
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
		 * �����첽���ص�POI��ѯ���
		 * 
		 * @param BDLocation
		 *            ���Ͳ�����
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
