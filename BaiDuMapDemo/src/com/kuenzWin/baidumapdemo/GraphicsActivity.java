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
		 * 1、定义覆盖物 2、设置覆盖物数据 3、获取mapview中存放覆盖物的集合，并加入覆盖物 4、刷新界面
		 */
		GraphicsOverlay overlay = new GraphicsOverlay(mv);
		setData(overlay);// 重点数据设置
		mv.getOverlays().add(overlay);
		mv.refresh();
	}

	private void setData(GraphicsOverlay overlay) {
		// 定义几何圆，圆心，半径

		// 几何图形类
		Geometry geometry = new Geometry();
		/**
		 * public void setCircle(GeoPoint geoPoint, int radius) 设置图形为圆
		 * 
		 * @param geoPoint
		 *            - 地理坐标
		 * @param radius
		 *            - 圆的半径，单位：米
		 */
		geometry.setCircle(Constant.point, 1 * 100);

		// 样式类
		Symbol symbol = new Symbol();
		// 颜色类
		Symbol.Color color = symbol.new Color();
		// 透明度分量,取值范围：0~255
		color.alpha = 100;
		// 红色分量,取值范围：0~25
		color.red = 255;
		// 绿色分量,取值范围：0~255
		color.green = 100;
		// 蓝色分量,取值范围：0~255
		color.blue = 20;
		/**
		 * public void setSurface(Symbol.Color color, int status, int linewidth)
		 * 设置面样式
		 * 
		 * @param color
		 *            - 颜色
		 * @param status
		 *            - 填充状态，0表示不填充，1表示填充
		 * @param linewidth
		 *            - 线宽,当填充状态为填充时线宽无意义
		 */
		symbol.setSurface(color, 1, 0);
		// 定义几何图形样式:颜色+线条宽度
		/**
		 * public Graphic(Geometry g, Symbol s) 构造 参数： g - 几何元素 s - 样式
		 */
		Graphic graphic = new Graphic(geometry, symbol);
		// 设置数据
		overlay.setData(graphic);
	}
}
