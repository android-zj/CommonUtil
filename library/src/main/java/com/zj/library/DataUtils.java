package com.zj.library;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 年月日时分
 */

public class DataUtils {

    //1 精确到分与秒
    public static String getDateToString(long milSecond,String mFormat) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(mFormat);
        return format.format(date);
    }

   //返回String格式如2018-06-8 (星期五) 16:40
    public static String getDateAndWeek(String mDate,long time,String mTime) {
        Date date = new Date(time);
       //yyyy年MM月dd日,yyyy-MM-dd,yyyy.MM.dd
        SimpleDateFormat format = new SimpleDateFormat(mDate+"   ("+ getWeek(date)+"） "+mTime);
        String timeString = format.format(date);
        return timeString ;
    }


    //返回星期几
    public static String getWeek(Date date) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    /**
     * 返回日时分
     * @param second
     * @return
     */
    public static String secondToTime(long second) {
        long days = second / 86400;//转换天数
        second = second % 86400;//剩余秒数
        long hours = second / 3600;//转换小时数
        second = second % 3600;//剩余秒数
        long minutes = second / 60;//转换分钟
        second = second % 60;//剩余秒数
        if (0 < days){
            return days + "天"+hours+"小时"+minutes+"分后";
        }else {
            if(0<hours){
                return hours+"小时"+minutes+"分后";
            }
            return minutes+"分钟后";
        }
    }
}
