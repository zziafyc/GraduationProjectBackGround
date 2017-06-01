package com.zzia.graduation.utils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

public class ParameterUtils {

	public static JSONObject getObject(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ServletInputStream input = request.getInputStream();
		int len = request.getContentLength();
		if (len <= 0) {
			return new JSONObject();
		}
		byte[] buffer = new byte[len];
		for (int i = 0; i < len;) {

			int readlen = input.read(buffer, i, len - i);
			if (readlen == -1) {
				break;
			}
			i += readlen;
		}

		input.close();
		System.out.println(new String(buffer, "UTF-8"));
		System.out.println("-----------------------------------");
		return new JSONObject(new String(buffer, "UTF-8"));
	}

	public static <T> Object getObject(HttpServletRequest request, Class<T> type) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ServletInputStream input = request.getInputStream();
		int len = request.getContentLength();
		if (len <= 0) {
			return null;
		}
		byte[] buffer = new byte[len];
		for (int i = 0; i < len;) {

			int readlen = input.read(buffer, i, len - i);
			if (readlen == -1) {
				break;
			}
			i += readlen;
		}
		input.close();
		System.out.println(new String(buffer, "UTF-8"));
		System.out.println("-----------------------------------");
		return new Gson().fromJson(new String(buffer, "UTF-8"), type);
	}

	// 获取单个参数(String)，抛出异常给调用者
	public static String getStringParameter(JSONObject jsonObject, String key) throws JSONException {
		if (jsonObject.has(key)) {
			return jsonObject.getString(key);
		} else {
			return null;
		}
	}

	// 获取单个参数(int)，抛出异常给调用者
	public static int getIntParameter(JSONObject jsonObject, String key) throws JSONException {
		if (jsonObject.has(key)) {
			return jsonObject.getInt(key);
		} else {
			return -1;
		}
	}

	// 判断多参数是否合法
	public static boolean judgeParams(String... args) {
		for (int i = 0; i < args.length; i++) {
			if (StringUtils.isEmpty(args[i])) {
				return false;
			}
		}
		return true;
	}

	// 判断jsonObject中参数是否为空
	public static boolean judgeJsonParams(JSONObject jsonObject, String... args) {
		int i = 0;
		while (i < args.length) {
			if (!jsonObject.has(args[i]) && i == args.length - 1) {
				return false;
			}

			i++;
		}
		return true;
	}

	// 给出带有String默认值的函数
	public static String getDefaultParameter(String name, String defaultvalue) {
		if (ServletActionContext.getRequest().getParameter(name) == null
				|| ServletActionContext.getRequest().getParameter(name).length() <= 0) {
			return defaultvalue;
		}
		return ServletActionContext.getRequest().getParameter(name);
	}

	// 给出带有int默认值的函数
	public static int getDefaultParameter(String name, int defaultvalue) {
		if (ServletActionContext.getRequest().getParameter(name) == null) {
			return defaultvalue;
		}
		return Integer.parseInt(ServletActionContext.getRequest().getParameter(name));
	}

}
