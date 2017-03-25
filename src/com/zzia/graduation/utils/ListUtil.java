package com.zzia.graduation.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 集合工具
 *
 */
public class ListUtil<T> {
	// 当前的T类型
	private Class<T> clazz = null;

	public ListUtil() {

	}

	/**
	 * 集合元素去重
	 */
	public List<T> getListNoRepeat(List<T> oldList, String id) {
		if (oldList != null) {
			HashMap<String, T> map = new LinkedHashMap<String, T>();//换成lingked为了记住之前的顺序   2016.10.8
			for (T o : oldList) {
				String methodName = null;
				String firstFiledName = id.substring(0, 1);
				String a = firstFiledName.toUpperCase();
				String b = id.substring(1, id.length());
				methodName = "get";
				methodName += a;
				methodName += b;
				Method method;
				String key = null;
				try {
					method = o.getClass().getMethod(methodName);
					key = (String) method.invoke(o);
				} catch (NoSuchMethodException | SecurityException
						| IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				if (!map.containsKey(key)) {
					map.put(key, o);
				}
			}
			oldList.clear();
			oldList.addAll(map.values());
		}
		return oldList;
	}

	/**
	 * 根据指定集合元素排序，降序
	 */
	public List<T> sortListDesc(List<T> oldList, final String field) {
		Collections.sort(oldList, new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				String className = null;
				Method m1 = null;
				Method m2 = null;
				try {
					className = o1.getClass().getDeclaredField(field).getType()
							.getName();
					m1 = o1.getClass()
							.getMethod(
									"get"
											+ getMethodName(o1.getClass()
													.getDeclaredField(field)
													.getName()));
					m2 = o2.getClass()
							.getMethod(
									"get"
											+ getMethodName(o1.getClass()
													.getDeclaredField(field)
													.getName()));
					if (className.equals(Date.class.getName())) {
						if (((Date) m1.invoke(o1)).before((Date) m2.invoke(o2))) {
							return 1;
						}
						if (((Date) m1.invoke(o1)).after((Date) m2.invoke(o2))) {
							return -1;
						}
					}
				} catch (NoSuchFieldException | SecurityException
						| NoSuchMethodException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}

				return 0;
			}

		});
		return oldList;
	}

	/**
	 * 根据指定集合元素排序，（按时间倒序）
	 * dbfan
	 */
	public List<T> sortListArc(List<T> oldList, final String field) {
		Collections.sort(oldList, new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				String className = null;
				Method m1 = null;
				Method m2 = null;
				try {
					className = o1.getClass().getDeclaredField(field).getType()
							.getName();
					m1 = o1.getClass()
							.getMethod(
									"get"
											+ getMethodName(o1.getClass()
													.getDeclaredField(field)
													.getName()));
					m2 = o2.getClass()
							.getMethod(
									"get"
											+ getMethodName(o1.getClass()
													.getDeclaredField(field)
													.getName()));
					String a = (Date.class.getName());
					if (className.equals(String.class.getName())) {
						//此处应注意转型
						Date mm1 =DateUtils.str2Date( (String)m1.invoke(o1), "yyyy-MM-dd HH:mm:ss");
						Date mm2 =DateUtils.str2Date( (String)m2.invoke(o2), "yyyy-MM-dd HH:mm:ss");
						if (mm1.after( mm2)) {
							return -1;
						}
						if (mm1.before( mm2)) {
							return 1;
						}
					}
				} catch (NoSuchFieldException | SecurityException
						| NoSuchMethodException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}

				return 0;
			}

		});
		return oldList;
	}
	// 把一个字符串的第一个字母大写，效率是最高的
	private static String getMethodName(String fildName) {
		byte[] items = fildName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}
}
