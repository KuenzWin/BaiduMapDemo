package com.kuenzWin.baidumapdemo;

import android.os.Bundle;

import com.baidu.mapapi.map.RouteOverlay;
import com.baidu.mapapi.search.MKPlanNode;
import com.baidu.mapapi.search.MKRoute;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.kuenzWin.baidumapdemo.utils.Constant;

public class WalkingSearchActivity extends BaseActivity {

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
		 * public int walkingSearch(String startCity, MKPlanNode start, String
		 * endCity, MKPlanNode end) 步行路线搜索.
		 * 异步函数，返回结果在MKSearchListener里的onGetWalkingRouteResult方法通知
		 * 
		 * @param startCity
		 *            - 起点所在城市，起点为坐标时可不填
		 * @param start
		 *            - 搜索的起点，可以为坐标，名称任一种
		 * @param endCity
		 *            - 终点所在城市，终点为坐标时可不填
		 * @param end
		 *            - 搜索的终点，可以为坐标，名称任一种
		 * @return 成功返回0，否则返回-1
		 */
		MKPlanNode start = new MKPlanNode();
		start.pt = Constant.point;

		MKPlanNode end = new MKPlanNode();
		end.name = "江门市政府";
		search.walkingSearch("江门", start, "江门", end);
	}

	private class MySearchListener extends MyMKSearchAdapter {
		@Override
		public void onGetWalkingRouteResult(MKWalkingRouteResult result,
				int iError) {

			RouteOverlay overlay = new RouteOverlay(WalkingSearchActivity.this,
					mv);
			this.setData(overlay, result);
			mv.getOverlays().add(overlay);
			mv.refresh();
			super.onGetWalkingRouteResult(result, iError);
		}

		private void setData(RouteOverlay overlay, MKWalkingRouteResult result) {
			if (result.getNumPlan() > 0) {
				MKRoute route = result.getPlan(0).getRoute(0);
				overlay.setData(route);
			}
		}
	}

}
