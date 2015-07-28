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
		 * endCity, MKPlanNode end) ����·������.
		 * �첽���������ؽ����MKSearchListener���onGetWalkingRouteResult����֪ͨ
		 * 
		 * @param startCity
		 *            - ������ڳ��У����Ϊ����ʱ�ɲ���
		 * @param start
		 *            - ��������㣬����Ϊ���꣬������һ��
		 * @param endCity
		 *            - �յ����ڳ��У��յ�Ϊ����ʱ�ɲ���
		 * @param end
		 *            - �������յ㣬����Ϊ���꣬������һ��
		 * @return �ɹ�����0�����򷵻�-1
		 */
		MKPlanNode start = new MKPlanNode();
		start.pt = Constant.point;

		MKPlanNode end = new MKPlanNode();
		end.name = "����������";
		search.walkingSearch("����", start, "����", end);
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
