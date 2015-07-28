package com.kuenzWin.baidumapdemo;

import android.os.Bundle;

import com.baidu.mapapi.map.TransitOverlay;
import com.baidu.mapapi.search.MKPlanNode;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKTransitRoutePlan;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.kuenzWin.baidumapdemo.utils.Constant;

public class TransitOverlayActivity extends BaseActivity {

	private MKSearch search;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		this.search();
		super.onResume();
	}

	private void search() {
		search = new MKSearch();
		search.init(manager, new MySearchListener());
		/**
		 * public int transitSearch(String city, MKPlanNode start, MKPlanNode
		 * end) 公交路线搜索. 异步函数，返回结果在MKSearchListener里的onGetTransitRouteResult方法通知
		 * 
		 * @param city
		 *            城市名，用于在哪个城市内进行检索(必须填写)
		 * @param start
		 *            - 检索的起点，可通过关键字，坐标，两种方式指定
		 * @param end
		 *            检索的终点，可通过关键字，坐标，两种方式指定
		 * @return 成功返回0，否则返回-1
		 */
		MKPlanNode start = new MKPlanNode();
		start.pt = Constant.point;

		MKPlanNode end = new MKPlanNode();
		end.name = "江门市政府";

		// public int setTransitPolicy(int policy)
		// 设置路线规划策略.
		// 参数为策略常量。对下次搜索有效
		//
		// 参数：
		// policy -
		// EBUS_TIME_FIRST:时间优先；EBUS_TRANSFER_FIRST:少换乘；EBUS_WALK_FIRST:少步行；EBUS_NO_SUBWAY:
		// 非地铁
		// EBUS_NO_SUBWAY
		// 公交检索策略常量：不含地铁
		// static int EBUS_TIME_FIRST
		// 公交检索策略常量：时间优先
		// static int EBUS_TRANSFER_FIRST
		// 公交检索策略常量：最少换乘
		// static int EBUS_WALK_FIRST
		// 公交检索策略常量：最少步行距离
		// 返回：
		// 成功返回0，否则返回-1
		search.setTransitPolicy(MKSearch.EBUS_TIME_FIRST);
		search.transitSearch("江门", start, end);
	}

	private class MySearchListener extends MyMKSearchAdapter {
		@Override
		public void onGetTransitRouteResult(MKTransitRouteResult result,
				int iError) {
			TransitOverlay overlay = new TransitOverlay(
					TransitOverlayActivity.this, mv);
			setData(overlay, result);
			mv.getOverlays().add(overlay);
			mv.refresh();
			super.onGetTransitRouteResult(result, iError);
		}

		private void setData(TransitOverlay overlay, MKTransitRouteResult result) {
			if (result.getNumPlan() > 0) {
				MKTransitRoutePlan plan = result.getPlan(0);
				overlay.setData(plan);
			}
		}
	}

}
