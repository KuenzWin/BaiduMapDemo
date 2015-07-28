package com.kuenzWin.baidumapdemo;

import com.baidu.mapapi.map.Symbol.Color;
import com.baidu.mapapi.map.Symbol;
import com.baidu.mapapi.map.TextItem;
import com.baidu.mapapi.map.TextOverlay;
import com.kuenzWin.baidumapdemo.utils.Constant;

import android.os.Bundle;

public class TextOverlayActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.draw();
	}

	private void draw() {
		
		// 1. ��TextItem׼����������
		// 2. ����TextOverlay,���� addText(TextItem) �ӿ��������.
		// 3.����MapView.getOverlays().add()�������overlay��mapview�С�
		// 4.����MapView.refresh() ʹOverlay��Ч��
		 
		// ���ֱ�ע������,�������ͼ���һ���������ֱ�ע
		TextOverlay overlay = new TextOverlay(mv);
		setData(overlay);
		mv.getOverlays().add(overlay);
		mv.refresh();
	}

	private void setData(TextOverlay overlay) {
		// �����������ݱ�ʾ TextItem�洢���������ݿ��ܹ�TextOverlay��ӵ�MapView��
		TextItem item = new TextItem();

		// int align ���ֶ��뷽ʽ ��
		// Ϊ ALIGN_TOP,ALIGN_CENTER, ALIGN_BOTTOM�е�һ��ֵ
		// static int ALIGN_BOTTOM
		// ���ֶ���������±��е����
		// static int ALIGN_CENTER
		// ���ֶ�����������Ķ���
		// static int ALIGN_TOP
		// ���ֶ���������ϱ��е����
		item.align = TextItem.ALIGN_CENTER;
		// Symbol.Color bgColor
		// ���ֱ���ɫ, Ĭ��Ϊ͸��
		item.bgColor = getColor(1);
		// Symbol.Color fontColor
		// ������ɫ
		item.fontColor = getColor(2);
		// int fontSize
		// �ֺŴ�С
		item.fontSize = 18;
		// GeoPoint pt
		// ������ʾ��λ�ã��þ�γ�������ʾ
		item.pt = Constant.point;
		// String text
		// Ҫ��ʾ����������
		item.text = "���ش�ѧ";
		// Typeface typeface
		// �������壬 android �����ʾ��Ϊ������ϵͳĬ������.

		overlay.addText(item);
	}

	private Color getColor(int b) {
		// ��ʽ��
		Symbol symbol = new Symbol();
		// ��ɫ��
		Symbol.Color color = symbol.new Color();
		// ͸���ȷ���,ȡֵ��Χ��0~255
		color.alpha = 100;
		// ��ɫ����,ȡֵ��Χ��0~25
		color.red = 255;
		// ��ɫ����,ȡֵ��Χ��0~255
		color.green = (b == 2) ? 100 : 255;
		// ��ɫ����,ȡֵ��Χ��0~255
		color.blue = (b == 2) ? 100 : 255;
		return color;
	}

}
