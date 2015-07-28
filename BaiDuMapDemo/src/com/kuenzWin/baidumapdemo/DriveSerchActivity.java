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
		 * MKPlanNode ·�߽����Ϣ�� public String name ������� public GeoPoint pt �������
		 */
		MKPlanNode start = new MKPlanNode();
		start.pt = Constant.point;

		MKPlanNode end = new MKPlanNode();
		end.name = "����������";

		/**
		 * public int drivingSearch(String startCity, MKPlanNode start, String
		 * endCity, MKPlanNode end) �ݳ�·������.
		 * �첽���������ؽ����MKSearchListener���onGetDrivingRouteResult����֪ͨ
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
		search.drivingSearch("����", start, "����", end);
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
			// �ݳ�·�ߵķ�����
			/**
			 * public int getNumPlan() ��ȡ������Ŀ
			 */
			int count = result.getNumPlan();
			// �жϻظ��Ľ�����У�������һ������0
			if (count > 0) {
				/**
				 * public MKRoutePlan getPlan(int index) ��ȡ��index������
				 * 
				 * @param index
				 *            ��Ҫ���صķ�����������0��ʼ
				 * @return ��index������
				 */
				/**
				 * MKRoute �����ʾһ���ݳ����г��з���
				 */
				MKRoutePlan plan = result.getPlan(0);
				/**
				 * public MKRoute getRoute(int index) ���ط���������ָ������·
				 * 
				 * @param index
				 *            - ����, 0��ʾ��һ����·
				 * @return ��������ָ�����· MKRoute,�����ʾһ���ݳ˻���·��
				 */
				MKRoute route = plan.getRoute(0);

				overlay.setData(route);
			}
		}
	}

}
