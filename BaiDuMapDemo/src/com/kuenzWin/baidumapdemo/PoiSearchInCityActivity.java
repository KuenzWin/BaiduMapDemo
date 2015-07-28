package com.kuenzWin.baidumapdemo;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;

import com.baidu.mapapi.map.PoiOverlay;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;

public class PoiSearchInCityActivity extends BaseActivity {

	private MKSearch search;
	private MKSearchListener listener;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		search();
	}

	@Override
	protected void onResume() {

		super.onResume();
	}

	private void search() {
		search = new MKSearch();
		listener = new MyMKSearchAdapter() {
			@Override
			public void onGetPoiResult(MKPoiResult result, int type, int iError) {
				PoiOverlay overlay = new PoiOverlay(
						PoiSearchInCityActivity.this, mv);
				setData(overlay, result);
				mv.getOverlays().add(overlay);
				mv.refresh();
				super.onGetPoiResult(result, type, iError);
			}

			private void setData(PoiOverlay overlay, MKPoiResult result) {
				// ��ȡ����poi��������ҳ��
				int pages = result.getNumPages();
				// ��ȡ����poi�������ܽ����
				int pois = result.getNumPois();
				// ���ص�ǰҳ������
				int index = result.getPageIndex();
				// ��ȡ��ǰҳ��poi�����
				int poisCur = result.getCurrentNumPois();

				AlertDialog.Builder builder = new Builder(
						PoiSearchInCityActivity.this);
				builder.setTitle("���")
						.setMessage(
								"��ǰ" + index + "ҳ/��" + pages + "ҳ,��ǰ" + poisCur
										+ "�����/��" + pois + "�����")
						.setPositiveButton("ȷ��", null).show();
			}
		};
		search.init(manager, listener);
		/**
		 * ����poi����. �첽���������ؽ����MKSearchListener���onGetPoiResult����֪ͨ
		 * 
		 * @param city
		 *            ������
		 * @param key
		 *            - �ؼ���
		 * @return �ɹ�����0�����򷵻�-1
		 */
		search.poiSearchInCity("������", "�Ƶ�");
	}

}
