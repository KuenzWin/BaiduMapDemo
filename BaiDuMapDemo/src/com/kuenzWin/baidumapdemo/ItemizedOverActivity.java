package com.kuenzWin.baidumapdemo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class ItemizedOverActivity extends BaseActivity {

	private View popView;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		popView = View.inflate(this, R.layout.view_popup, null);
		popView.setVisibility(View.INVISIBLE);
		tv = (TextView) popView.findViewById(R.id.tv);
		mv.addView(popView);
		this.draw();
	}

	/**
	 * 自定义覆盖物或标注管理类 通过ItemizedOverlay可以向地图添加一个或多个自定义覆盖物或标注。
	 * 
	 * 添加覆盖物的一般流程： 1. 用OverlayItem 准备overlay数据。 2. 创建ItemizedOverlay实例,调用
	 * addItem(OverlayItem) 接口添加overlay. 3. 若一次性添加的overlay数据较多，可以使用
	 * addItem(List) 接口. 3. 调用MapView.getOverlays().add()方法添加overlay到mapview中。
	 * 4. 调用MapView.refresh() 使Overlay生效。
	 */
	private void draw() {
		ItemizedOverlay<OverlayItem> overlay = new ItemizedOverlay<OverlayItem>(
				this.getResources().getDrawable(R.drawable.ic_launcher), mv) {
			/**
			 * protected boolean onTap(int index) 覆盖这个方法去处理一个item上的点击事件。
			 * 这可能是对屏幕上item的触摸点击，或者对位于中心且已选定的item的跟踪球点击。默认情况下，什么都不做，返回false
			 * 
			 * @return 如果点击事件被处理，返回true；如果想要把这个事件传递给其它overlay，返回false
			 */
			@Override
			protected boolean onTap(int index) {
				OverlayItem item = this.getItem(index);
				String msg = item.getTitle();
				// Toast.makeText(ItemizedOverActivity.this, msg,
				// Toast.LENGTH_LONG).show();
				GeoPoint pt = item.getPoint();
				// 重新绘制popView的位置，通过坐标获取params的位置的关系
				/**
				 * MapView.LayoutParams(int width, int height, GeoPoint point,
				 * int alignment) 创建自定义布局参数，按地理坐标布局
				 * 
				 * @param width
				 *            - 要显示的view的宽
				 * @param height
				 *            - 要显示的view的高
				 * @param point
				 *            - 自定义view将要添加到的位置(使用地理位置坐标表示)
				 * @param alignment
				 *            - 对齐方式
				 */
				MapView.LayoutParams lp = new MapView.LayoutParams(
						MapView.LayoutParams.WRAP_CONTENT,
						MapView.LayoutParams.WRAP_CONTENT, pt,
						MapView.LayoutParams.BOTTOM_CENTER);
				mv.updateViewLayout(popView, lp);
				tv.setText(msg);
				popView.setVisibility(View.VISIBLE);
				return super.onTap(index);
			}

			/**
			 * protected boolean hitTest(OverlayItem item, Drawable marker, int
			 * hitX, int hitY) 检查给定的点击测试点是否在一个item标记点范围内。
			 * 覆盖修改了一个item点击测试的方法。点击点相对于标记点的范围。默认的实现只检查点击点是否在标记点的可触摸范围内
			 * 
			 * @param item
			 *            - 进行点击测试的item
			 * @param marker
			 *            - item的标记点
			 * @param hitX
			 *            - 测试点的x坐标
			 * @param hitY
			 *            - 测试点的y坐标 返回： 如果点击测试点在标记点范围内，返回true
			 */
			@Override
			protected boolean hitTest(OverlayItem item, Drawable marker,
					int hitX, int hitY) {
				return super.hitTest(item, marker, hitX, hitY);
			}
		};
		this.setData(overlay);
		mv.getOverlays().add(overlay);
		mv.refresh();
	}

	private void setData(ItemizedOverlay<OverlayItem> overlay) {

		GeoPoint pt1 = new GeoPoint((int) (22.608405 * 1E6),
				(int) (113.091463 * 1E6));
		/**
		 * 表示单个overlay数据，如自定义标注，建筑等。 OverlayItem是ItemizedOverlay的基本组件，
		 * OverlayItem存储的overlay数据通过ItemizedOverlay添加到地图中。
		 */
		/**
		 * OverlayItem(GeoPoint point, String title, String snippet)
		 * 
		 * @param point
		 *            该item的位置
		 * 
		 * @param title
		 *            该item的标题文本
		 * @param snippet
		 *            该item的文字片段
		 */
		OverlayItem item1 = new OverlayItem(pt1, "金汇广场", "吃好吃的");
		overlay.addItem(item1);
	}
}
