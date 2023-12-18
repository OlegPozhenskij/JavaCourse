package com.pozhenskii.Lab_3.second;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeUtil {

    public static String compareDateTimezones(String utcDateTime, String timezone1, String timezone2) {
        ZonedDateTime utcDt1 = ZonedDateTime.parse(utcDateTime).withZoneSameInstant(ZoneId.of(timezone1));
        ZonedDateTime utcDt2 = ZonedDateTime.parse(utcDateTime).withZoneSameInstant(ZoneId.of(timezone2));

        if (utcDt1.equals(utcDt2)) {
            return "0";
        }

        if (utcDt1.getYear() != utcDt2.getYear()) {
            return "YEAR";
        }

        if (utcDt1.getMonthValue() != utcDt2.getMonthValue()) {
            return "MONTH";
        }

        if (utcDt1.getDayOfMonth() != utcDt2.getDayOfMonth()) {
            return "DAY";
        }

        return "HOUR";
    }

    public static void main(String[] args) {
        System.out.println("Разница во времени: " + compareDateTimezones("2024-10-01T04:59:59Z", "UTC-08", "UTC-04"));
        System.out.println("Разница во времени: " + compareDateTimezones("2020-06-01T14:25:16Z", "UTC+01", "UTC+02"));
        System.out.println("Разница во времени: " + compareDateTimezones("2023-05-10T23:00:00Z", "UTC-03", "UTC+04"));
        System.out.println("Разница во времени: " + compareDateTimezones("2010-12-31T20:15:00Z", "Europe/Moscow", "Asia/Vladivostok"));
        System.out.println("Разница во времени: " + compareDateTimezones("2023-11-12T00:00:00Z", "UTC+00", "UTC+00"));
    }
}
