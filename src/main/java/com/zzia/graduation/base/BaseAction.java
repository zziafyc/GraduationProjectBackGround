package com.zzia.graduation.base;

import java.util.Map;
public class BaseAction {
	// 一些共用参数
	private Map<String, Object> Rows;

	public final String SUCCESS = "success";
	
	public String TAG = null;

	public BaseAction() {
		TAG = this.getClass().getSimpleName();
	}

	public Map<String, Object> getRows() {
		return Rows;
	}

	public Map<String, Object> setRows(Map<String, Object> rows) {
		Rows = rows;
		return Rows;
	}

}
