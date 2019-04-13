package com.zhiyou.video.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zhiyou.video.common.Myconstant;



public class DateUtil {

	// 字符串转日期对象
	public static java.sql.Date stringToDate(String dateString) {
		// yyyy-MM-dd
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(Myconstant.yyyyMMdd);
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new java.sql.Date(date.getTime());
	}
	
	
	// 字符串转日期对象
		public static java.sql.Date stringToDateyy(String dateString) {
			// yyyy-MM-dd
			Date date = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = format.parse(dateString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new java.sql.Date(date.getTime());
		}
		
	
	public static String dateToString(java.sql.Date date) {
		
		SimpleDateFormat format = new SimpleDateFormat(Myconstant.yyyyMMdd);
		String dateString = format.format(date);
	
		return dateString;
	}
	
	
}
