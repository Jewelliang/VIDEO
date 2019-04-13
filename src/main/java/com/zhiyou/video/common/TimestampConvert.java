package com.zhiyou.video.common;

import java.sql.Date;
import java.util.Set;
import org.springframework.core.convert.converter.Converter;

import com.zhiyou.video.common.DateUtil;


public class TimestampConvert implements Converter<String, Date> {

	@Override
	public Date convert(String dateString) {
		return DateUtil.stringToDateyy(dateString);
	}

	/*
	@Override
	public Date convert(String string) {
		
		return DateUtil.stringToDateyy(string);
	}
*/
	

	



}
