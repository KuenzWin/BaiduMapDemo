package com.kuenzWin.baidumapdemo;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.Toast;

import com.baidu.mapapi.map.PoiOverlay;
import com.baidu.mapapi.search.MKPoiInfo;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.kuenzWin.baidumapdemo.utils.Constant;

/**
 * ��Ȥ�����(Բ��)
 * 
 * @author ������
 * @date 2015-7-26
 */
public class PoiSerachNearByActivity extends BaseActivity {

	private MKSearch search;
	private MKSearchListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		controller.enableClick(true);
		search();
	}

	@Override
	protected void onResume() {

		super.onResume();
	}

	private void search() {
		search = new MKSearch();
		listener = new MyListener();
		/**
		 * public boolean init(BMapManager bmapMan, MKSearchListener listener)
		 * ��ʹ��
		 * 
		 * @param bmapMan
		 *            ʵ������BMapManger
		 * @return �ɹ�����true�����򷵻�false
		 */
		search.init(manager, listener);
		/**
		 * public int poiSearchNearBy(String key, GeoPoint pt, int radius)
		 * �������ĵ㡢�뾶������ʷ����ܱ߼���. ��1.1�汾֮��֧�֡�
		 * �첽���������ؽ����MKSearchListener���onGetPoiResult����֪ͨ
		 * 
		 * @param key
		 *            �ؼ���
		 * @param pt
		 *            ���ĵ��������
		 * @param radius
		 *            �뾶����λ:��
		 * @return �ɹ�����0�����򷵻�-1
		 */
		search.poiSearchNearBy("����վ", Constant.point, 10 * 1000);
	}

	private class MyListener extends MyMKSearchAdapter {
		@Override
		public void onGetPoiResult(MKPoiResult result, int type, int iError) {
			// TODO ����Բ�������ڵ�����չʾ
			Toast.makeText(PoiSerachNearByActivity.this,
					result.getNumPois() + "", Toast.LENGTH_LONG).show();
			if (iError != 0 || result == null) {
				Toast.makeText(manager.getContext(), "�޽��", Toast.LENGTH_LONG)
						.show();
				return;
			}
			PoiOverlay overlay = new PoiOverlay(PoiSerachNearByActivity.this,
					mv);
			mv.getOverlays().add(overlay);
			setData(overlay, result);
			mv.refresh();
			super.onGetPoiResult(result, type, iError);
		}

		private void setData(PoiOverlay overlay, MKPoiResult result) {
			ArrayList<MKPoiInfo> infos = result.getAllPoi();
			/**
			 * ����poi ����
			 * 
			 * @param data
			 *            poi ��Ϣ����
			 */
			overlay.setData(infos);
		}
	}
}
