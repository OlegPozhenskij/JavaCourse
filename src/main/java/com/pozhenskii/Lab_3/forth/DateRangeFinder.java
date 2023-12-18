package com.pozhenskii.Lab_3.forth;

import java.time.LocalDate;
import java.time.temporal.IsoFields;

public class DateRangeFinder {
    public static LocalDate[] getDateRange(String year, String week) {
        LocalDate monday = LocalDate.of(
                Integer.parseInt(year), 1, 1)
                .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, Long.parseLong(week)
        );

        LocalDate sunday = monday.plusDays(6);

        return new LocalDate[]{monday, sunday};
    }

//    public static void main(String[] args) {
//        if (Validator.validateWeek("25") && Validator.validateYear("1993")) {
//            System.out.println(Arrays.toString(DateRangeFinder.getDateRange("1993", "25")));
//        }
//
//    }
}
