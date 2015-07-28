package com.kuenzWin.baidumapdemo;

import android.os.Bundle;

import com.baidu.mapapi.map.RouteOverlay;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPlanNode;
import com.baidu.mapapi.search.MKRoute;
import com.baidu.mapapi.search.MKRoutePlan;
import com.baidu.mapapi.search.MKSearch;
import com.kuenzWin.baidumapdemo.utils.Constant;

public class DriveSerchActivity extends BaseActivity {

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
		search.init(manager, new MySearchAdapter());

		/**
		 * MKPlanNode 路线结点信息类 public String name 结点名称 public GeoPoint pt 结点坐标
		 */
		MKPlanNode start = new MKPlanNode();
		start.pt = Constant.point;

		MKPlanNode end = new MKPlanNode();
		end.name = "江门市政府";

		/**
		 * public int drivingSearch(String startCity, MKPlanNode start, String
		 * endCity, MKPlanNode end) 驾乘路线搜索.
		 * 异步函数，返回结果在MKSearchListener里的onGetDrivingRouteResult方法通知
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
		search.drivingSearch("江门", start, "江门", end);
	}

	private class MySearchAdapter extends MyMKSearchAdapter {
		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult result,
				int iError) {
			RouteOverlay overlay = new RouteOverlay(DriveSerchActivity.this, mv);
			this.setData(overlay, result);
			mv.getOverlays().add(overlay);
			mv.refresh();
			super.onGetDrivingRouteResult(result, iError);
		}

		private void setData(RouteOverlay overlay, MKDrivingRouteResult result) {
			// 驾车路线的方案数
			/**
			 * public int getNumPlan() 获取方案数目
			 */
			int count = result.getNumPlan();
			// 判断回复的结果集中，至少有一条大于0
			if (count > 0) {
				/**
				 * public MKRoutePlan getPlan(int index) 获取第index个方案
				 * 
				 * @param index
				 *            需要返回的方案索引，从0开始
				 * @return 第index个方案
				 */
				/**
				 * MKRoute 此类表示一条驾车或步行出行方案
				 */
				MKRoutePlan plan = result.getPlan(0);
				/**
				 * public MKRoute getRoute(int index) 返回方案中索引指定的线路
				 * 
				 * @param index
				 *            - 索引, 0表示第一条线路
				 * @return 返回索引指向的线路 MKRoute,此类表示一条驾乘或步行路线
				 */
				MKRoute route = plan.getRoute(0);

				overlay.setData(route);
			}
		}
	}

}
