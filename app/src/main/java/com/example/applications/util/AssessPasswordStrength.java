package com.example.applications.util;

public class AssessPasswordStrength {
    public static PasswordStrength assessPasswordStrength(String password) {
        if (password.length() < 8) {
            return PasswordStrength.WEAK;
        }

        boolean containsLowerCase = false;
        boolean containsUpperCase = false;
        boolean containsDigit = false;
        boolean containsSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                containsLowerCase = true;
            } else if (Character.isUpperCase(c)) {
                containsUpperCase = true;
            } else if (Character.isDigit(c)) {
                containsDigit = true;
            } else {
                containsSpecialChar = true;
            }
        }

        if (containsLowerCase && containsUpperCase && containsDigit && containsSpecialChar) {
            return PasswordStrength.STRONG;
        } else if ((containsLowerCase || containsUpperCase) && containsDigit) {
            return PasswordStrength.MEDIUM;
        } else {
            return PasswordStrength.WEAK;
        }
    }
}
