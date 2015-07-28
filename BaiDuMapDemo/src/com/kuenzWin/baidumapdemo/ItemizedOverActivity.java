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
	 * �Զ��帲������ע������ ͨ��ItemizedOverlay�������ͼ���һ�������Զ��帲������ע��
	 * 
	 * ��Ӹ������һ�����̣� 1. ��OverlayItem ׼��overlay���ݡ� 2. ����ItemizedOverlayʵ��,����
	 * addItem(OverlayItem) �ӿ����overlay. 3. ��һ������ӵ�overlay���ݽ϶࣬����ʹ��
	 * addItem(List) �ӿ�. 3. ����MapView.getOverlays().add()�������overlay��mapview�С�
	 * 4. ����MapView.refresh() ʹOverlay��Ч��
	 */
	private void draw() {
		ItemizedOverlay<OverlayItem> overlay = new ItemizedOverlay<OverlayItem>(
				this.getResources().getDrawable(R.drawable.ic_launcher), mv) {
			/**
			 * protected boolean onTap(int index) �����������ȥ����һ��item�ϵĵ���¼���
			 * ������Ƕ���Ļ��item�Ĵ�����������߶�λ����������ѡ����item�ĸ���������Ĭ������£�ʲô������������false
			 * 
			 * @return �������¼�����������true�������Ҫ������¼����ݸ�����overlay������false
			 */
			@Override
			protected boolean onTap(int index) {
				OverlayItem item = this.getItem(index);
				String msg = item.getTitle();
				// Toast.makeText(ItemizedOverActivity.this, msg,
				// Toast.LENGTH_LONG).show();
				GeoPoint pt = item.getPoint();
				// ���»���popView��λ�ã�ͨ�������ȡparams��λ�õĹ�ϵ
				/**
				 * MapView.LayoutParams(int width, int height, GeoPoint point,
				 * int alignment) �����Զ��岼�ֲ��������������겼��
				 * 
				 * @param width
				 *            - Ҫ��ʾ��view�Ŀ�
				 * @param height
				 *            - Ҫ��ʾ��view�ĸ�
				 * @param point
				 *            - �Զ���view��Ҫ��ӵ���λ��(ʹ�õ���λ�������ʾ)
				 * @param alignment
				 *            - ���뷽ʽ
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
			 * hitX, int hitY) �������ĵ�����Ե��Ƿ���һ��item��ǵ㷶Χ�ڡ�
			 * �����޸���һ��item������Եķ��������������ڱ�ǵ�ķ�Χ��Ĭ�ϵ�ʵ��ֻ��������Ƿ��ڱ�ǵ�Ŀɴ�����Χ��
			 * 
			 * @param item
			 *            - ���е�����Ե�item
			 * @param marker
			 *            - item�ı�ǵ�
			 * @param hitX
			 *            - ���Ե��x����
			 * @param hitY
			 *            - ���Ե��y���� ���أ� ���������Ե��ڱ�ǵ㷶Χ�ڣ�����true
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
		 * ��ʾ����overlay���ݣ����Զ����ע�������ȡ� OverlayItem��ItemizedOverlay�Ļ��������
		 * OverlayItem�洢��overlay����ͨ��ItemizedOverlay��ӵ���ͼ�С�
		 */
		/**
		 * OverlayItem(GeoPoint point, String title, String snippet)
		 * 
		 * @param point
		 *            ��item��λ��
		 * 
		 * @param title
		 *            ��item�ı����ı�
		 * @param snippet
		 *            ��item������Ƭ��
		 */
		OverlayItem item1 = new OverlayItem(pt1, "���㳡", "�ԺóԵ�");
		overlay.addItem(item1);
	}
}
