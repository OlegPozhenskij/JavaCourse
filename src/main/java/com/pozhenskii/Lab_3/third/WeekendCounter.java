package com.pozhenskii.Lab_3.third;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendCounter {
    public static void getWeekendDaysCounter(LocalDate startDate, LocalDate endDate) {
        int counter = 0;
        for (LocalDate day = startDate; !day.isAfter(endDate); day = day.plusDays(1)) {
            if (day.getDayOfWeek().equals(DayOfWeek.SATURDAY) || day.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                counter++;
            }
        }
        System.out.println(counter);
    }

    public static void main(String[] args) {
        WeekendCounter.getWeekendDaysCounter(
                LocalDate.parse("2023-01-01"),
                LocalDate.parse("2023-12-31")
        );
    }
}
