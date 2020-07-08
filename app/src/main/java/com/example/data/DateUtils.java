package com.example.data;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 *
 * 日期工具类，要做到，①utildate转换为sqldate，②string转换为utildate ③utildate转换为string
 * @author 76557
 *1999-5-22
 */
public class DateUtils {

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	//utildate转换为sqldate
	public static Date uDateToSQLDate(java.util.Date uDate) {
		return new Date(uDate.getTime());
	}

	//string转换为utildate
	public static java.util.Date stringToUDate(String string) {
		try {
			return simpleDateFormat.parse(string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	//utildate转换为string
	public static String udateToString(java.util.Date uDate) {
		return simpleDateFormat.format(uDate);
	}
}
