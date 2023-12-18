package com.pozhenskii.Lab_3.first;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MonthInfo {

    private List<LocalDate> dates = new ArrayList<>();

    public MonthInfo() {
    }

    public void getInfo() {
        for (LocalDate date: dates) {
            System.out.printf("%-15s%-13s%-8s%-8s%-15s%-15s%n",
                    getFullDate(date),
                    getFullMonthName(date),
                    getMonthNum(date),
                    getShortWeekDayName(date),
                    getDaysCount(date),
                    getQuarterWithYear(date));
        }
    }

    public String getFullDate(LocalDate date) {
        return date.toString();
    }

    public String getFullMonthName(LocalDate date) {
            return date.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru"));
    }

    public int getMonthNum(LocalDate date) {
        return date.getMonth().getValue();
    }

    public String getShortWeekDayName(LocalDate date) {
        return date.getDayOfWeek().getDisplayName(TextStyle.SHORT, new Locale("ru"));
    }

    public String getDaysCount(LocalDate date) {
        return String.valueOf(date.with(TemporalAdjusters.lastDayOfMonth()));
    }

    public String getQuarterWithYear(LocalDate date) {
        int quarter = (date.getMonthValue() - 1) / 3 + 1;
        return date.getYear() + " Q" + quarter;
    }

    public void setDates(List<LocalDate> dates) {
        this.dates = dates;
    }

    public static void main(String[] args) {
        MonthInfo monthInfo = new MonthInfo();
        var arr = new ArrayList<LocalDate>();
        arr.add(LocalDate.of(2001, 4, 12));
        arr.add(LocalDate.of(2021, 7, 27));
        monthInfo.setDates(arr);
        monthInfo.getInfo();
    }
}
