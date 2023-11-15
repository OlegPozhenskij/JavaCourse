package com.pozhenskii.lab1;

import java.text.DecimalFormat;
import java.util.Scanner;

public class L2 {
    public static void main(String[] args) {
//        vat();
//        countDown(args);
    }

    private static void vat() {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        double totalCostWithVat;
        double costWithoutVat;
        String roundedCostWithoutVat;
        double vatAmount;
        int roundedTaxDeclarationAmount;
        String roundedReceiptAmount;
        DecimalFormat df = new DecimalFormat("#.##");

        try {
            totalCostWithVat = Double.parseDouble(text);
        } catch (NumberFormatException e) {
            System.out.println("Введено не рациональное число");
            return;
        }

        if (totalCostWithVat <= 0) {
            System.out.println("Неправильный ввод цены");
            return;
        }

        costWithoutVat = totalCostWithVat / 1.2;
        roundedCostWithoutVat = df.format(costWithoutVat);

        vatAmount = totalCostWithVat - costWithoutVat;
        roundedReceiptAmount = df.format(vatAmount);

        roundedTaxDeclarationAmount = (int) Math.round(vatAmount);

        System.out.println("Стоимость товара без НДС: " + roundedCostWithoutVat + " руб.");
        System.out.println("Сумма НДС в чеке: " + roundedReceiptAmount + " руб.");
        System.out.println("Сумма НДС для налоговой декларации: " + roundedTaxDeclarationAmount + " руб.");
    }

    private static void countDown(String[] args) {
        int minutes;

        if (args.length > 0) {
            try {
                minutes = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа минут.");
                return;
            }
        } else {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите количество минут: ");
            try {
                minutes = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа минут.");
                return;
            }
        }

        if (minutes <= 0) {
            System.out.println("Уже началось!");
        } else {
            displayTimeRemaining(minutes);
        }
    }

    private static void displayTimeRemaining(int minutes) {
        int days = minutes / (24 * 60);
        int hours = (minutes % (24 * 60)) / 60;
        int remainingMinutes = minutes % 60;

        System.out.printf("%d %s %d %s %d %s%n",
                days, getUnit(days, "день", "дня", "дней"),
                hours, getUnit(hours, "час", "часа", "часов"),
                remainingMinutes, getUnit(remainingMinutes, "минута", "минуты", "минут"));
    }

    private static String getUnit(int number, String form1, String form2, String form5) {
        int absNumber = Math.abs(number);
        int lastDigit = absNumber % 10;
        int lastTwoDigits = absNumber % 100;

        if (lastTwoDigits >= 11 && lastTwoDigits <= 19) {
            return form5;
        }

        if (lastDigit == 1) {
            return form1;
        }

        if (lastDigit >= 2 && lastDigit <= 4) {
            return form2;
        }

        return form5;
    }

}
