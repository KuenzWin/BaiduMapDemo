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
				// 获取本次poi搜索的总页数
				int pages = result.getNumPages();
				// 获取本次poi搜索的总结果数
				int pois = result.getNumPois();
				// 返回当前页的索引
				int index = result.getPageIndex();
				// 获取当前页的poi结果数
				int poisCur = result.getCurrentNumPois();

				AlertDialog.Builder builder = new Builder(
						PoiSearchInCityActivity.this);
				builder.setTitle("结果")
						.setMessage(
								"当前" + index + "页/共" + pages + "页,当前" + poisCur
										+ "条结果/共" + pois + "条结果")
						.setPositiveButton("确定", null).show();
			}
		};
		search.init(manager, listener);
		/**
		 * 城市poi检索. 异步函数，返回结果在MKSearchListener里的onGetPoiResult方法通知
		 * 
		 * @param city
		 *            城市名
		 * @param key
		 *            - 关键词
		 * @return 成功返回0，否则返回-1
		 */
		search.poiSearchInCity("江门市", "酒店");
	}

}
