package com.kuenzWin.baidumapdemo;

import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.kuenzWin.baidumapdemo.utils.Constant;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LayerActivity extends Activity implements OnClickListener {

	@ViewInject(R.id.mv)
	private MapView mv;

	@ViewInject(R.id.ditu)
	private Button ditu;
	@ViewInject(R.id.traffic)
	private Button traffic;
	@ViewInject(R.id.weixing)
	private Button weixing;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layer);

		ViewUtils.inject(this);

		MapController controller = mv.getController();
		controller.setZoom(Constant.ZOOM);
		controller.setCenter(Constant.point);

		ditu.setOnClickListener(this);
		traffic.setOnClickListener(this);
		weixing.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ditu:
			// 底图
			mv.setTraffic(false);
			mv.setSatellite(false);
			break;
		case R.id.traffic:
			// 实时交通
			mv.setTraffic(true);
			mv.setSatellite(false);
			break;
		case R.id.weixing:
			// 卫星图
			mv.setSatellite(true);
			mv.setSatellite(false);
			break;
		}
	}
}
