package com.example.data;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * 
 * ���ڹ����࣬Ҫ��������utildateת��Ϊsqldate����stringת��Ϊutildate ��utildateת��Ϊstring
 * @author 76557
 *1999-5-22
 */
public class DateUtils {

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	//utildateת��Ϊsqldate
	public static Date uDateToSQLDate(java.util.Date uDate) {
		return new Date(uDate.getTime());
	}
	
	//stringת��Ϊutildate"1999-5-22"
	public static java.util.Date stringToUDate(String string) {
		try {
			return simpleDateFormat.parse(string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//utildateת��Ϊstring"1999-5-22"
	public static String udateToString(java.util.Date uDate) {
		return simpleDateFormat.format(uDate);
	}
}
