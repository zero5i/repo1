package com.jmh.server.commom.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.SpringVersion;

/**
 * @author JiangMuhua
 */
public class DateTimeUtil{
	
	public static final String PATTERN_0 = "yyyyMMdd";
	public static final String PATTERN_1 = "yyyyMMddHHmmssSSS";
	public static final String PATTERN_2 = "yyyyMMddHHmmss";
	public static final String PATTERN_3 = "yyMMdd";
	public static final String PATTERN_4 = "yyyy/MM/dd";
	public static final String PATTERN_5 = "yyyy年MM月";
	public static final String PATTERN_6 = "yyyy/MM/dd";
	public static final String PATTERN_7 = "yyyy/MM/dd HH:mm:ss";
	public static final String PATTERN_8 = "yyyy/MM";
	public static final String PATTERN_9 = "yyyy年MM月dd日";
	public static final String PATTERN_10 = "yyyy/MM/dd HH:mm";
	public static final String PATTERN_11 = "HH:mm:ss";
	public static final String PATTERN_12 = "yyyy-MM-dd";
	public static final String PATTERN_13 = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_14 = "yyyy";
	public static final String PATTERN_15 = "yyyy年MM月dd日 HH时";
	public static final String PATTERN_16 = "yyyy-MM-dd HH:mm";
	public static final String PATTERN_17 = "MM月dd日 HH:mm";
	public static final String PATTERN_18 = "MM月dd日";
	public static final String PATTERN_19 = "HH:mm";
	public static final String PATTERN_20 = "yyyy年MM月dd日 HH:mm";
	public static final String PATTERN_21 = "yyyyMM";
	
    /**
     * get current time string in million second
     *
     * @return the million second string
     */
    public static String getCurrentTime() {
        long time = System.currentTimeMillis();
        return String.valueOf(time);
    }

 
	/**
	* Return short format datetime string.
	* @param date java.util.Date
	* @return short format datetime
	*/
	public static String getDateTimeStr(Date date,String pattern)  {
		if(date == null){
			return StringUtils.EMPTY;
		}

		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	/**
	* Return current datetime string.
	* @param pattern format pattern
	* @return current datetime
	*/
	public static String getDateTimeStr(String pattern)  {		
		return getDateTimeStr(new Date(), pattern);
	}

	/**
	* Parse a datetime string.
	* @param param datetime string, pattern example: "yyyy-MM-dd HH:mm:ss".
	* @return java.util.Date
	*/
	public static Date parse(String param,String patten) {
		if(param == null){
			return null;
		}
		
		Date date = null;
		SimpleDateFormat sdf=new SimpleDateFormat(patten);
		try {
			date = sdf.parse(param);
		}catch (ParseException ex) {
			//ex.printStackTrace();
		}
		return date;
	}
	
    public static void main(String[] args){

    	int m = 5;
    	int n = 1;
        for(int i = 3; i < 103;i++) {
            System.out.println((int)Math.abs(Math.random()*(m-n)+n));
        }
    	
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DATE, -1);    //得到前一天 
    	cal.add(Calendar.MONTH, -1);    //得到前一个月
    	
    	
		System.out.println(DateTimeUtil.getDateTimeStr(cal.getTime(),DateTimeUtil.PATTERN_4));
		System.out.println(SpringVersion.getVersion());
		String str = "0.00";
		Double a = Double.valueOf(str);
		System.out.println(a);
    }

}