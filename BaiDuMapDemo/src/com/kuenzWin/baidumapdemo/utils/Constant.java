package com.kuenzWin.baidumapdemo.utils;

import com.baidu.platform.comapi.basestruct.GeoPoint;

public class Constant {

	public static final String KEY = "你的Key";
	
	public static final float ZOOM = 18;

	// 五邑大学 113.092607,22.606495
	public static final GeoPoint point = new GeoPoint((int) (22.606495 * 1E6),
			(int) (113.092607 * 1E6));
}
