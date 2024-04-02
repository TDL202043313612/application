package com.example.applications.util;



public class StringUtils {
    public static boolean isEmpty(String str) {
        if (str==null || str.length()<=0)
            return true;
        else
            return false;
    }
    public static boolean isEqual(String str1, String str2) {
        if (str1.equals(str2))
            return false;
        else
            return true;
    }

    public static String[] stringSplit(String str,String regex){
        return str.split(regex);
    }

    public static String stringSub(String str, int beginIndex, int endIndex){
        return str.substring(beginIndex,endIndex);
    }
}
