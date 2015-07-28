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
 * 兴趣点检索(圆形)
 * 
 * @author 温坤哲
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
		 * 初使化
		 * 
		 * @param bmapMan
		 *            实例化的BMapManger
		 * @return 成功返回true，否则返回false
		 */
		search.init(manager, listener);
		/**
		 * public int poiSearchNearBy(String key, GeoPoint pt, int radius)
		 * 根据中心点、半径与检索词发起周边检索. 自1.1版本之后支持。
		 * 异步函数，返回结果在MKSearchListener里的onGetPoiResult方法通知
		 * 
		 * @param key
		 *            关键词
		 * @param pt
		 *            中心点地理坐标
		 * @param radius
		 *            半径，单位:米
		 * @return 成功返回0，否则返回-1
		 */
		search.poiSearchNearBy("加油站", Constant.point, 10 * 1000);
	}

	private class MyListener extends MyMKSearchAdapter {
		@Override
		public void onGetPoiResult(MKPoiResult result, int type, int iError) {
			// TODO 处理圆形区域内的数据展示
			Toast.makeText(PoiSerachNearByActivity.this,
					result.getNumPois() + "", Toast.LENGTH_LONG).show();
			if (iError != 0 || result == null) {
				Toast.makeText(manager.getContext(), "无结果", Toast.LENGTH_LONG)
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
			 * 设置poi 数据
			 * 
			 * @param data
			 *            poi 信息集合
			 */
			overlay.setData(infos);
		}
	}
}
