package com.pozhenskii.Lab_3.forth;

public class Validator {
    public static boolean validateYear(String year) {
        try {
            return Integer.parseInt(year) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validateWeek(String week) {
        try {
            int wkCount = Integer.parseInt(week);
            return wkCount > 1 && wkCount <= 53;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
