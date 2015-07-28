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
		 * end) ����·������. �첽���������ؽ����MKSearchListener���onGetTransitRouteResult����֪ͨ
		 * 
		 * @param city
		 *            ���������������ĸ������ڽ��м���(������д)
		 * @param start
		 *            - ��������㣬��ͨ���ؼ��֣����꣬���ַ�ʽָ��
		 * @param end
		 *            �������յ㣬��ͨ���ؼ��֣����꣬���ַ�ʽָ��
		 * @return �ɹ�����0�����򷵻�-1
		 */
		MKPlanNode start = new MKPlanNode();
		start.pt = Constant.point;

		MKPlanNode end = new MKPlanNode();
		end.name = "����������";

		// public int setTransitPolicy(int policy)
		// ����·�߹滮����.
		// ����Ϊ���Գ��������´�������Ч
		//
		// ������
		// policy -
		// EBUS_TIME_FIRST:ʱ�����ȣ�EBUS_TRANSFER_FIRST:�ٻ��ˣ�EBUS_WALK_FIRST:�ٲ��У�EBUS_NO_SUBWAY:
		// �ǵ���
		// EBUS_NO_SUBWAY
		// �����������Գ�������������
		// static int EBUS_TIME_FIRST
		// �����������Գ�����ʱ������
		// static int EBUS_TRANSFER_FIRST
		// �����������Գ��������ٻ���
		// static int EBUS_WALK_FIRST
		// �����������Գ��������ٲ��о���
		// ���أ�
		// �ɹ�����0�����򷵻�-1
		search.setTransitPolicy(MKSearch.EBUS_TIME_FIRST);
		search.transitSearch("����", start, end);
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
