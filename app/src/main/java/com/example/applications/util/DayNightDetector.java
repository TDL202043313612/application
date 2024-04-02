package com.example.applications.util;

import java.util.Calendar;

public class DayNightDetector {

    public static void setDayStartHour(int dayStartHour) {
        DAY_START_HOUR = dayStartHour;
    }

    public static void setDayStartMinute(int dayStartMinute) {
        DAY_START_MINUTE = dayStartMinute;
    }

    public static void setDayEndHour(int dayEndHour) {
        DAY_END_HOUR = dayEndHour;
    }

    public static void setDayEndMinute(int dayEndMinute) {
        DAY_END_MINUTE = dayEndMinute;
    }

    // 白天的开始和结束时间
    public static int DAY_START_HOUR = 6;
    public static int DAY_START_MINUTE = 6;

    public static int DAY_END_HOUR = 18;
    public static int DAY_END_MINUTE = 6;

    public static boolean isDayTime() {
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);
        if (currentHour > DAY_START_HOUR && currentHour < DAY_END_HOUR){
            return true;
        }else if (currentHour == DAY_START_HOUR){
            return currentMinute >= DAY_START_MINUTE;
        }else if (currentHour == DAY_END_HOUR){
            return currentMinute < DAY_END_MINUTE;
        }
        return false;
    }
}
//7.30 --- 20.20
//        8或19
//        7.29
//        20.19