package com.pozhenskii.lab1;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;
import static java.lang.Math.*;

public class L1 {
    public static void main(String[] args) {
//        roundingValues();
//        typeDefinition();
//        calculateMetrics();
//        maxSameSignSeries();
    }

    private static void roundingValues() {
        double[] dArr = {30.0, 10000.1, 12.5, 99.99, 0.0, -23.45, -4.5, -129.675};

        System.out.printf("%-10s%-15s%-15s%-15s%-15s%-15s%-15s%n",
                "Value", "round()", "floor()", "ceil()", "rint()", "floorDiv(10)", "floorDiv(3)");

        for (double d : dArr) {
            System.out.printf("%-10s%-15s%-15s%-15s%-15s%-15s%-15s%n",
                    d,
                    (int) round(d),
                    (int) floor(d),
                    (int) ceil(d),
                    (int) rint(d),
                    floorDiv((int) d, 10),
                    floorDiv((int) d, 3));
        }
    }

    private static void typeDefinition() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String val = scanner.nextLine();
            if (val.equals("stop")) break;

            String type  =  isInteger(val) ? "Целое число" :
                            isDouble(val) ? "Рациональное число" :
                            isBoolean(val) ? "Логичекое число" :
                                    "Тект";

            System.out.println(type);
        }

    }

    private static boolean isInteger(String val) {
        try {
            Integer.parseInt(val);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isDouble(String val) {
        try {
            Double.parseDouble(val);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isBoolean(String val) {
        return Boolean.parseBoolean(val);
    }

    private static void calculateMetrics() {
        Scanner scanner = new Scanner(System.in);

        int count = 0;
        double sum = 0;
        double min = Integer.MAX_VALUE;
        double max = Integer.MIN_VALUE;

        while (true) {
            String val = scanner.nextLine();
            if (val.isEmpty()) {
                System.out.println("Вы ничего не ввели!");
                break;
            }

            try {
                double num = Double.parseDouble(val);
                sum += num;
                count++;

                if(num < min) {
                    min = num;
                }

                if(num > max) {
                    max = num;
                }
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели не число!");
                break;
            }
        }
        if (count != 0) {
            System.out.println("Кол-во: " + count);
            System.out.println("Сумма: " + sum);
            System.out.println("Среднее значение: " + sum/count);
            System.out.println("Минимальное значение: " + min);
            System.out.println("Максимальное значение: " + max);

        } else {
            System.out.println("Вы не ввели ни одного числа!");
        }
    }

    private static void maxSameSignSeries() {
        Random random = new Random();
        int count = random.nextInt(20) + 10;
        int streak = 0;
        int maxStreak = Integer.MIN_VALUE;
        String streakSing = "";

        System.out.printf("%-10s%-15s%-15s%-15s%n", "№", "Число", "Знак", "Длинна серии");

        for (int i = 1; i < count; i++) {
            int num = random.nextInt(2001) - 1000;
            String sign = (num == 0) ? "0" : num < 0 ? "-" : "+";

            if(streakSing.isEmpty()) {
                streak++;
            } else {
                streak = streakSing.equals(sign) ? streak + 1 : 1;
            }

            maxStreak = Math.max(streak, maxStreak);
            streakSing = sign;

            System.out.printf("%-10s%-15s%-15s%-15s%n", i + ".", num, sign , streak);
        }

        System.out.println("Самая длинная серия: " + maxStreak);
    }
}
