package com.zzia.graduation.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zzia.graduation.base.Constants;

public class PutUtils {
	// 判断list是否不为空且有值
	public static boolean adjustList(List<?> list) {
		if (list != null && list.size() > 0 && (!list.isEmpty())) {
			return true;
		} else {
			return false;
		}
	}

	// 判断对象是否不为空且有值
	public static boolean adjustObject(Object object) {
		if (object != null) {
			return true;
		} else {
			return false;
		}
	}

	// date为返回数据
	public static Map<String, Object> result(int resultCode, int status, Object data, String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.Key.RESULTCODE, resultCode);
		map.put(Constants.Key.STATUS, status);
		map.put(Constants.Key.RESULT, data);
		map.put(Constants.Key.MESSAGE, message);
		return map;
	}

	// 成功，返回不同的信息
	public static Map<String, Object> success() {
		return result(Constants.Code.SUCCESS, Constants.Code.SUCCESS, null, "请求成功");
	}

	public static Map<String, Object> success(Integer stateCode, String message) {

		return result(Constants.Code.SUCCESS, stateCode, null, message);
	}

	public static Map<String, Object> success(Object data) {
		return result(Constants.Code.SUCCESS, Constants.Code.SUCCESS, data, "请求成功");
	}

	public static Map<String, Object> success(Object data, String message) {
		return result(Constants.Code.SUCCESS, Constants.Code.SUCCESS, data, message);
	}

	public static Map<String, Object> success(Integer stateCode, Object data) {
		return result(Constants.Code.SUCCESS, stateCode, data, null);
	}

	// 请求出错，不带信息返回数据
	public static Map<String, Object> error() {
		return result(Constants.Code.ERROR, Constants.Code.ERROR, null, "系统错误，请稍后重试！");
	}

	// 请求出错，不带信息返回数据
	public static Map<String, Object> error(String msg) {
		return result(Constants.Code.ERROR, Constants.Code.ERROR, null, msg);
	}

	// 请求出错，不带信息返回数据
	public static Map<String, Object> error(Integer errorCode, String msg) {
		return result(Constants.Code.ERROR, errorCode, null, msg);
	}

	// 请求出错，不带信息返回数据
	public static Map<String, Object> empty() {
		return result(Constants.Code.EMPTY, Constants.Code.SUCCESS, null, "没有更多数据了");
	}

	// 请求出错，不带信息返回数据
	public static Map<String, Object> empty(String message) {
		return result(Constants.Code.EMPTY, Constants.Code.SUCCESS, null, message);
	}

	// 用于增删改
	public static Map<String, Object> putCode(Boolean isSuccess) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (isSuccess) {
			map.put(Constants.Key.RESULTCODE, Constants.Code.SUCCESS);
			map.put(Constants.Key.STATUS, Constants.Code.SUCCESS);
			map.put(Constants.Key.MESSAGE, "操作成功");
		} else {
			map.put(Constants.Key.RESULTCODE, Constants.Code.SUCCESS);
			map.put(Constants.Key.STATUS, Constants.Code.ERROR);
			map.put(Constants.Key.MESSAGE, "操作失败，查看是否存在此记录或插入的数据是否为空");
		}
		return map;
	}

	public static Map<String, Object> putCode(Boolean isSuccess, String message1, String message2) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 取消点赞
		if (isSuccess) {
			map.put(Constants.Key.RESULTCODE, Constants.Code.SUCCESS);
			map.put(Constants.Key.STATUS, Constants.Code.SUCCESS);
			map.put("action", 1);
			map.put(Constants.Key.MESSAGE, message1);
		} else {
			map.put(Constants.Key.RESULTCODE, Constants.Code.SUCCESS);
			map.put(Constants.Key.STATUS, Constants.Code.SUCCESS);
			map.put("action", 2);
			map.put(Constants.Key.MESSAGE, message2);
		}
		return map;
	}

	// 将对象put里面,
	public static Map<String, Object> putValues(Object object) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (adjustObject(object)) {
			map.put(Constants.Key.RESULT, object);
			map.put(Constants.Key.RESULTCODE, Constants.Code.SUCCESS);
			map.put(Constants.Key.MESSAGE, "请求成功");
			map.put(Constants.Key.STATUS, Constants.Code.SUCCESS);
		} else {
			map.put(Constants.Key.RESULTCODE, Constants.Code.SUCCESS);
			map.put(Constants.Key.MESSAGE, "无数据");
			map.put(Constants.Key.STATUS, Constants.Code.EMPTY);
		}
		return map;
	}

	public static Map<String, Object> putId(String id) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put(Constants.Key.RESULTCODE, Constants.Code.SUCCESS);
		map.put(Constants.Key.MESSAGE, "操作成功");
		map.put(Constants.Key.STATUS, Constants.Code.SUCCESS);
		map.put("id", id);
		return map;
	}

	// 传递的参数不完整
	public static Map<String, Object> parameterError() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.Key.RESULTCODE, Constants.Code.ERROR);
		map.put(Constants.Key.MESSAGE, "参数不完整");
		return map;
	}

}
