package com.example.bandofbrotherszts.androidphoneinfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ${zhaojz} on 2017/9/1.
 */

public class TimeUtil {

    private static SimpleDateFormat sf = null;

     //根据个人需要自行添加
    public static String DATA_FORMAT_1 ="yyyy年MM月dd日";
    public static String DATA_FORMAT_2 ="yy年MM月dd日";
    public static String DATA_FORMAT_3 ="yy-MM-dd";
    public static String DATA_FORMAT_4 ="yyyy-MM-dd HH:mm:ss";
    public static String DATA_FORMAT_5 ="yyyy年MM月dd日HH时mm分ss秒";
    public static String DATA_FORMAT_6 ="yy/MM/dd";
    public static String DATA_FORMAT = DATA_FORMAT_4;  //当前用的，以此为例
    /**
     * 当前时间戳   long
     * @return
     */
    public static long getCurrentTime(){
        return  System.currentTimeMillis();
    }
    /**
     * 当前时间戳   String
     * @return
     */
    public static String getStringCurrentTime(){
        return  String.valueOf(System.currentTimeMillis());
    }
    /**
     * @return  当前时间与历史时间的时间差
     * @params 历史时间
     */
    public static String getDurationDate(String lastTime) {
        String temp = "";
        try {
            long now = System.currentTimeMillis() / 1000;
            long publish = Long.parseLong(lastTime);
            long diff = now - publish;
            long months = diff / (60 * 60 * 24*30);
            long days = diff / (60 * 60 * 24);
            long hours = (diff - days * (60 * 60 * 24)) / (60 * 60);
            long minutes = (diff - days * (60 * 60 * 24) - hours * (60 * 60)) / 60;
            long seconds =  (diff - days * (60 * 60 * 24) - hours * (60 * 60)) / (60*60);
            if (months > 0) {
                temp = months + "月前";
            } else if (days > 0) {
                temp = days + "天前";
            } else if (hours > 0) {
                temp = hours + "小时前";
            } else if (minutes > 0){
                temp = minutes + "分钟前";
            }else if (seconds>0){
                temp = minutes + "秒前";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }
    /**
     * 获取系统时间 格式为：自选
     */
    public static String getCurrentDate(){
        Date date  = new Date();
        sf  = new SimpleDateFormat(DATA_FORMAT);
        String currentDate = sf.format(date);
        return currentDate;
    }
    /**
     * 时间戳转换成字符窜
     */
    public static String getDateToString(long time){
        Date date  = new Date(time);
        sf  = new SimpleDateFormat(DATA_FORMAT);
        String currentDate = sf.format(date);
        return currentDate;
    }
    /**
     * 将字符串转为时间戳
     */
    public static long getStringToDate(String time){
        sf  = new SimpleDateFormat(DATA_FORMAT);
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
    /**
     * 输入时间戳转换成星期几
     */
    public static String getWeek(long timeStamp) {
        int myDate = 0;
        String week = null;
        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date(timeStamp));
        myDate = cd.get(Calendar.DAY_OF_WEEK);
        if (myDate == 1) {
            week = "周日";
        } else if (myDate == 2) {
            week = "周一";
        } else if (myDate == 3) {
            week = "周二";
        } else if (myDate == 4) {
            week = "周三";
        } else if (myDate == 5) {
            week = "周四";
        } else if (myDate == 6) {
            week = "周五";
        } else if (myDate == 7) {
            week = "周六";
        }
        return week;
    }
    /**
     * 输入时间戳转换成月份
     */
    public static int getMonth(long timeStamp) {
        int myDate = 0;
        String month = null;
        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date(timeStamp));
        myDate = cd.get(Calendar.MONTH)+1;
        return myDate;
    }
    /**
     * 输入时间戳转换成当前月的那一天
     */
    public static int getDayOfMonth(long timeStamp) {
        int myDate = 0;
        String month = null;
        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date(timeStamp));
        myDate = cd.get(Calendar.DAY_OF_MONTH);
        return myDate;
    }

}
