package com.example.applications.util;

public class HidePhoneNumber {
    public static String hidePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() != 11) {
            return phoneNumber; // 不是有效的手机号码，直接返回
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (i >= 3 && i <= 6) {
                result.append('*'); // 将第4位到第7位之间的数字替换为*
            } else {
                result.append(phoneNumber.charAt(i)); // 其他位置保持不变
            }
        }
        return result.toString();
    }

}
