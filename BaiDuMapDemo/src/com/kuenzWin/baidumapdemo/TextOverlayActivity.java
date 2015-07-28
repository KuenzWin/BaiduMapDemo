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
		
		// 1. 用TextItem准备文字数据
		// 2. 创建TextOverlay,调用 addText(TextItem) 接口添加文字.
		// 3.调用MapView.getOverlays().add()方法添加overlay到mapview中。
		// 4.调用MapView.refresh() 使Overlay生效。
		 
		// 文字标注管理类,可以向地图添加一个或多个文字标注
		TextOverlay overlay = new TextOverlay(mv);
		setData(overlay);
		mv.getOverlays().add(overlay);
		mv.refresh();
	}

	private void setData(TextOverlay overlay) {
		// 单个文字数据表示 TextItem存储的文字数据可能过TextOverlay添加到MapView中
		TextItem item = new TextItem();

		// int align 文字对齐方式 ，
		// 为 ALIGN_TOP,ALIGN_CENTER, ALIGN_BOTTOM中的一个值
		// static int ALIGN_BOTTOM
		// 文字对齐参数，下边中点对齐
		// static int ALIGN_CENTER
		// 文字对齐参数，中心对齐
		// static int ALIGN_TOP
		// 文字对齐参数，上边中点对齐
		item.align = TextItem.ALIGN_CENTER;
		// Symbol.Color bgColor
		// 文字背景色, 默认为透明
		item.bgColor = getColor(1);
		// Symbol.Color fontColor
		// 文字颜色
		item.fontColor = getColor(2);
		// int fontSize
		// 字号大小
		item.fontSize = 18;
		// GeoPoint pt
		// 文字显示的位置，用经纬度坐标表示
		item.pt = Constant.point;
		// String text
		// 要显示的文字内容
		item.text = "五邑大学";
		// Typeface typeface
		// 文字字体， android 字体表示，为空则用系统默认字体.

		overlay.addText(item);
	}

	private Color getColor(int b) {
		// 样式类
		Symbol symbol = new Symbol();
		// 颜色类
		Symbol.Color color = symbol.new Color();
		// 透明度分量,取值范围：0~255
		color.alpha = 100;
		// 红色分量,取值范围：0~25
		color.red = 255;
		// 绿色分量,取值范围：0~255
		color.green = (b == 2) ? 100 : 255;
		// 蓝色分量,取值范围：0~255
		color.blue = (b == 2) ? 100 : 255;
		return color;
	}

}
