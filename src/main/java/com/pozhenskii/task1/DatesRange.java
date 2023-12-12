package com.pozhenskii.task1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DatesRange {
    public static String[] getDateRangeArr(String date1, String date2) {
        LocalDate ld1 = LocalDate.parse(date1);
        LocalDate ld2 = LocalDate.parse(date2);
        String[] arr;

        if(!ld1.isAfter(ld2)) {
            arr = new String[(int) (ChronoUnit.DAYS.between(ld1, ld2)) + 1];

            for (int i = 0; !ld1.isAfter(ld2); i++) {
                arr[i] = ld1.toString();
                ld1 = ld1.plusDays(1);
            }
            return arr;
        } else {
            return new String[]{};
        }
    }

}
