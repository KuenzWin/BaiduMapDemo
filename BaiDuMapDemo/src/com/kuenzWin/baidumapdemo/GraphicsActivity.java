package com.kuenzWin.baidumapdemo;

import android.os.Bundle;

import com.baidu.mapapi.map.Geometry;
import com.baidu.mapapi.map.Graphic;
import com.baidu.mapapi.map.GraphicsOverlay;
import com.baidu.mapapi.map.Symbol;
import com.kuenzWin.baidumapdemo.utils.Constant;

public class GraphicsActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.draw();
	}

	private void draw() {
		/**
		 * 1�����帲���� 2�����ø��������� 3����ȡmapview�д�Ÿ�����ļ��ϣ������븲���� 4��ˢ�½���
		 */
		GraphicsOverlay overlay = new GraphicsOverlay(mv);
		setData(overlay);// �ص���������
		mv.getOverlays().add(overlay);
		mv.refresh();
	}

	private void setData(GraphicsOverlay overlay) {
		// ���弸��Բ��Բ�ģ��뾶

		// ����ͼ����
		Geometry geometry = new Geometry();
		/**
		 * public void setCircle(GeoPoint geoPoint, int radius) ����ͼ��ΪԲ
		 * 
		 * @param geoPoint
		 *            - ��������
		 * @param radius
		 *            - Բ�İ뾶����λ����
		 */
		geometry.setCircle(Constant.point, 1 * 100);

		// ��ʽ��
		Symbol symbol = new Symbol();
		// ��ɫ��
		Symbol.Color color = symbol.new Color();
		// ͸���ȷ���,ȡֵ��Χ��0~255
		color.alpha = 100;
		// ��ɫ����,ȡֵ��Χ��0~25
		color.red = 255;
		// ��ɫ����,ȡֵ��Χ��0~255
		color.green = 100;
		// ��ɫ����,ȡֵ��Χ��0~255
		color.blue = 20;
		/**
		 * public void setSurface(Symbol.Color color, int status, int linewidth)
		 * ��������ʽ
		 * 
		 * @param color
		 *            - ��ɫ
		 * @param status
		 *            - ���״̬��0��ʾ����䣬1��ʾ���
		 * @param linewidth
		 *            - �߿�,�����״̬Ϊ���ʱ�߿�������
		 */
		symbol.setSurface(color, 1, 0);
		// ���弸��ͼ����ʽ:��ɫ+�������
		/**
		 * public Graphic(Geometry g, Symbol s) ���� ������ g - ����Ԫ�� s - ��ʽ
		 */
		Graphic graphic = new Graphic(geometry, symbol);
		// ��������
		overlay.setData(graphic);
	}
}
