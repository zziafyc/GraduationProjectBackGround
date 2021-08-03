package com.zzia.graduation.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.zzia.graduation.base.Constants;

import net.sourceforge.pinyin4j.PinyinHelper;

public class CommentUtils {

	// 获取字符串首字母
	public static String getChineseFirstChar(String chinese) {

		String result[] = PinyinHelper.toHanyuPinyinStringArray(chinese
				.charAt(0));
		if (result == null) {
			char ch = chinese.charAt(0);
			if ((ch > 'a' && ch < 'z') || (ch > 'A' && ch < 'Z')) {
				return (ch + "").toUpperCase();
			} else {
				return "#";
			}

		}
		return (result[0].charAt(0) + "").toUpperCase();

	}

	// 根据首字母将list分类
	// name为对象的属性名
	public static Map<String, List<Object>> sortByFirstChar(List<?> datas,
			String name) throws Exception {
		Map<String, List<Object>> map = new TreeMap<String, List<Object>>();
		for (int i = 0; i < datas.size(); i++) {
			String values = (String) datas.get(i).getClass()
					.getDeclaredField(name).get(datas.get(i));
			String a = CommentUtils.getChineseFirstChar(values);
			if (map.containsKey(a)) {
				map.get(a).add(datas.get(i));
			} else {
				List<Object> temp = new ArrayList<Object>();
				temp.add(datas.get(i));
				map.put(a, temp);
			}
		}

		return map;

	}

	public static int getLastInt(int code) {
		String text = code + "";
		return Integer.parseInt(text.substring(text.length() - 1));
	}

	// 获取字符串最后一位并转换为响应的int值
	public static int getLastCharForInt(String text) {
		String ch = text.substring(text.length() - 1).toLowerCase();
		switch (ch) {
		case "g":
		case "0":
			return 0;

		case "h":
		case "1":
			return 1;

		case "i":
		case "2":
			return 2;

		case "j":
		case "3":
			return 3;

		case "k":
		case "4":
			return 4;

		case "l":
		case "5":
			return 5;

		case "m":
		case "6":

			return 6;
		case "n":
		case "7":
			return 7;

		case "o":
		case "8":
			return 8;

		case "p":
		case "9":
			return 9;

		case "q":
		case "a":
			return 10;

		case "r":
		case "b":
			return 11;

		case "s":
		case "c":
			return 12;

		case "t":
		case "d":
			return 13;

		case "u":
		case "e":
			return 14;

		case "v":
		case "f":
			return 15;

		default:
			return 15;
		}
	}

	public static String getThumbAddress(String address) {
		StringBuffer thumb = new StringBuffer();
		int index = address.lastIndexOf('/');
		thumb.append(address.substring(0, index + 1)).append("thumb_")
				.append(address.substring(index + 1));
		String filePath = thumb.toString().replace(
				Constants.FileManager.rootAccessPath,
				Constants.FileManager.rootPath);
		if (new File(filePath).exists()) {
			return thumb.toString();
		} else {
			return address;
		}
	}

	public static String getVideoPAddress(String address) {
		StringBuffer thumb = new StringBuffer();
		int index = address.lastIndexOf('.');
		thumb.append(address.substring(0, index));
		thumb.append(".jpg");
		return thumb.toString();
	}
}
