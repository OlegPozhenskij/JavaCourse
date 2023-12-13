package com.pozhenskii.task3;

import java.util.Scanner;

public class Main {
  private static double[][] initializeShippingCosts() {
    double[][] shippingCosts = new double[6][4];
    shippingCosts[0] = new double[]{803, 952, 997, 931};
    shippingCosts[1] = new double[]{967, 1012, 848, 1200};
    shippingCosts[2] = new double[]{825, 945, 777, 848};
    shippingCosts[3] = new double[]{1024, 1800, 931, 999};
    shippingCosts[4] = new double[]{754, 817, 531, 628};
    shippingCosts[5] = new double[]{911, 668, 865, 1526};
    return shippingCosts;
  }

  private static int[] initializeMaximumPurchaseVolume() {
    return new int[]{600, 120, 360, 250, 700, 390};
  }

  private static double[] initializePurchasePrice() {
    return new double[]{5.2, 4.5, 6.1, 3.8, 6.4, 5.6};
  }

  private static int[] getUserInputForNumberPurchases() {
    int[] numberPurchases = new int[4];
    Scanner sc = new Scanner(System.in);

    for (int i = 0; i < 4; ) {
      System.out.println("\nВведите число закупок для АЗС:");
      if (sc.hasNextInt()) {
        int quantity = sc.nextInt();
        if (quantity > 0) {
          numberPurchases[i] = quantity;
          i++;
        } else {
          System.err.println("Ошибка: число < 0");
        }
      } else {
        sc.next();
        System.err.println("Ошибка: число не целое");
      }
    }

    return numberPurchases;
  }

  private static void printPurchaseList(String message, PurchaseRecord purchaseList) {
    System.out.println("\n" + message);
    assert purchaseList != null;
    purchaseList.printTable();
  }

  public static void main(String[] args) {
    double[][] shippingCosts = initializeShippingCosts();
    int[] maximumPurchaseVolume = initializeMaximumPurchaseVolume();
    double[] purchasePrice = initializePurchasePrice();

    Supplier[] suppliers = new Supplier[6];
    for (int i = 0; i < suppliers.length; i++) {
      suppliers[i] = new Supplier(maximumPurchaseVolume[i], purchasePrice[i], shippingCosts[i]);
    }

    GasolineSupplies gasolineSupplies = new GasolineSupplies(suppliers);
    gasolineSupplies.printTable();

    int[] numberPurchases = getUserInputForNumberPurchases();

    PurchaseRecord purchaseList = PurchaseRecord.calculatePurchaseRecord(gasolineSupplies, numberPurchases);
    printPurchaseList("Итоговый лист закупок : ", purchaseList);
  }
}