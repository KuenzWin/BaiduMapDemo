package com.kuenzWin.baidumapdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class StartActivity extends Activity implements OnItemClickListener {

	private ListView lv;

	private List<String> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		lv = (ListView) this.findViewById(R.id.lv);
		data = new ArrayList<String>();
		data.add("初识");
		data.add("图层");
		data.add("图形覆盖物");
		data.add("文字覆盖物");
		data.add("多条目绘制");
		data.add("兴趣点检索");
		data.add("全城兴趣点检索");
		data.add("驾车路线");
		data.add("步行路线");
		data.add("公交换乘");
		data.add("定位");

		lv.setAdapter(new MyAdapter());
		lv.setOnItemClickListener(this);
	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView tv;
			if (convertView == null) {
				tv = new TextView(StartActivity.this);
			} else {
				tv = (TextView) convertView;
			}
			tv.setTextSize(25);
			tv.setText(data.get(position));
			return tv;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		switch (position) {
		case 0:
			intent.setClass(this, MainActivity.class);
			break;
		case 1:
			intent.setClass(this, LayerActivity.class);
			break;
		case 2:
			intent.setClass(this, GraphicsActivity.class);
		case 3:
			intent.setClass(this, TextOverlayActivity.class);
			break;
		case 4:
			intent.setClass(this, ItemizedOverActivity.class);
			break;
		case 5:
			intent.setClass(this, PoiSerachNearByActivity.class);
			break;
		case 6:
			intent.setClass(this, PoiSearchInCityActivity.class);
			break;
		case 7:
			intent.setClass(this, DriveSerchActivity.class);
			break;
		case 8:
			intent.setClass(this, WalkingSearchActivity.class);
			break;
		case 9:
			intent.setClass(this, TransitOverlayActivity.class);
		case 10:
			intent.setClass(this, MyLocationOverlayActivity.class);
		}
		this.startActivity(intent);

	}

}
