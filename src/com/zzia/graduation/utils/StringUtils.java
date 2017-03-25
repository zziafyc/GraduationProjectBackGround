package com.zzia.graduation.utils;

import java.util.UUID;

/**
 * @author wgy 编码日期：2016年9月5日下午4:35:52 email:wgyscsf@163.com
 */
public class StringUtils {
	// 获取字符串最后一位
	public static String getLastString(String str) {
		return str.charAt(str.length() - 1) + "";
	}

	public static void main(String[] args) {
		String methodName = Thread.currentThread().getStackTrace()[0]
				.getMethodName();
		String className = Thread.currentThread().getStackTrace()[0]
				.getClassName();
		int lineNumber = Thread.currentThread().getStackTrace()[0]
				.getLineNumber();

		String errMsg = "信息位置定位：所在类：" + className + ",所在方法：" + methodName
				+ ",所在行号：" + lineNumber;
		System.out.println(errMsg);
	}

	// 获取字符串最后一位
	public static int getLastInt(String str) {
		return char2Int(getLastString(str).charAt(0));
	}

	public static int char2Int(char c) {
		if (c == '0') {
			return 0;
		} else if (c == '1') {
			return 1;
		} else if (c == '2') {
			return 2;
		} else if (c == '3') {
			return 3;
		} else if (c == '4') {
			return 4;
		} else if (c == '5') {
			return 5;
		} else if (c == '6') {
			return 6;
		} else if (c == '7') {
			return 7;
		} else if (c == '8') {
			return 8;
		} else if (c == '9') {
			return 9;
		} else if (c == 'a' || c == 'A') {
			return 10;
		} else if (c == 'b' || c == 'B') {
			return 11;
		} else if (c == 'c' || c == 'C') {
			return 12;
		} else if (c == 'd' || c == 'D') {
			return 13;
		} else if (c == 'e' || c == 'E') {
			return 14;
		} else if (c == 'f' || c == 'F') {
			return 15;
		}
		return -1;

	}

	// 判断字符串是否为空
	public static boolean strIsNull(String str) {
		if (str == null || str.equals(""))
			return true;
		return false;
	}

	// 生成主键，全部走这里，如果后期修改主键生成规则，方便修改
	public static String getGUID() {
		return UUID.randomUUID() + "";
	}

	// 定位错误的类、方法以及行数，帮助开发者快速定位错误位置
	public static String getErrorMsg() {
		String methodName = Thread.currentThread().getStackTrace()[2]
				.getMethodName();
		String className = Thread.currentThread().getStackTrace()[2]
				.getClassName();
		int lineNumber = Thread.currentThread().getStackTrace()[2]
				.getLineNumber();

		String errMsg = "信息位置定位：所在类：" + className + ",所在方法：" + methodName
				+ ",所在行号：" + lineNumber;
		return errMsg;
	}

	// // test
	// public static void main(String[] args) {
	// // for (int i = 0; i < 100; i++) {
	// // System.out.println(getGUID());
	// // }
	//
	// // ExpertGroup expertGroup = new ExpertGroup();
	// // expertGroup.setExpertDes("asdsa");
	// // List<ExpertMember> list = new ArrayList<>();
	// // ExpertMember expertMember = new ExpertMember();
	// // expertMember.setMemberId("asdas");
	// // list.add(expertMember);
	// // expertGroup.setMemberList(list);
	// // ;
	// // System.out.println(new Gson().toJson(expertGroup));
	// System.out.println(getErrorMsg());
	// }

	public static boolean isEmpty(String str) {
		if (str == null || str.length() == 0 || str.equalsIgnoreCase("null")
				|| str.isEmpty() || str.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNumber(String str) {
		return str.matches("[0-9]+");
	}

	public static String getNoRepeatStr(String str) {
		return str.replaceAll("(?s)(.)(?=.*\\1)", "");
	}
	
	public static String subString(String str, int length) {
		if (str.length() > length) {
			return str.substring(0, length);
		}
		return str;
	}
}
