package com.pozhenskii.task3;

import java.util.Arrays;
import java.util.stream.IntStream;

public class PurchaseRecord {
  private final int[][] purchaseTable;
  private final double[] purchaseCost;
  private final double[] shippingCost;

  private PurchaseRecord(int[][] purchaseTable, double[] purchaseCost, double[] shippingCost) {
    if (checkNegatives(purchaseTable)) {
      this.purchaseTable = null;
      System.err.println("Вы не можете купить негативное число топлива");
    } else {
      this.purchaseTable = purchaseTable;
    }

    this.purchaseCost = purchaseCost;
    this.shippingCost = shippingCost;
  }

  private boolean checkNegatives(int[][] array) {
    return Arrays.stream(array)
            .flatMapToInt(Arrays::stream)
            .anyMatch(value -> value < 0);
  }

  public static double[] calculatePurchaseCost(GasolineSupplies gasolineSupplies, int[][] purchaseTable) {
    double[] purchasePrice = Arrays.stream(gasolineSupplies.getSuppliers())
            .mapToDouble(Supplier::getPurchasePrice)
            .toArray();

    return IntStream.range(0, purchaseTable.length)
            .mapToDouble(i -> roundDecimals(
                    IntStream.of(purchaseTable[i]).sum() * purchasePrice[i]))
            .toArray();
  }

  private static double roundDecimals(double value) {
    return Math.round(value * 100) / 100.0;
  }

  public static double[] calculateShippingCost(GasolineSupplies gasolineSupplies, int[][] purchaseTable) {
    double[][] suppliesShippingCosts = Arrays.stream(gasolineSupplies.getSuppliers())
            .map(Supplier::getShippingCosts)
            .toArray(double[][]::new);

    return IntStream.range(0, purchaseTable.length)
            .mapToDouble(i ->
                    IntStream.range(0, purchaseTable[i].length)
                            .filter(j -> purchaseTable[i][j] > 0)
                            .mapToDouble(j -> suppliesShippingCosts[i][j])
                            .sum())
            .toArray();
  }

  public static PurchaseRecord calculatePurchaseRecord(GasolineSupplies gasolineSupplies, int[] numberPurchases) {
    if (Arrays.stream(gasolineSupplies.getSuppliers())
            .anyMatch(supplier -> supplier.getShippingCosts().length != numberPurchases.length)) {
      System.err.println("Заправки не равны закупкам");
      return null;
    }

    if (Arrays.stream(numberPurchases).anyMatch(value -> value < 0)) {
      System.err.println("Нельзя закупить отрицательное количество топлива");
      return null;
    }

    int numberSupplier = gasolineSupplies.getSuppliers().length;
    int numberGasStation = numberPurchases.length;
    int[][] purchaseTable = new int[numberSupplier][numberGasStation];

    double[][] shippingCosts = Arrays.stream(gasolineSupplies.getSuppliers())
            .map(Supplier::getShippingCosts)
            .toArray(double[][]::new);

    double[] purchasePrice = Arrays.stream(gasolineSupplies.getSuppliers())
            .mapToDouble(Supplier::getPurchasePrice)
            .toArray();

    int[] nowPurchaseVolume = Arrays.stream(gasolineSupplies.getSuppliers())
            .mapToInt(Supplier::getMaximumPurchaseVolume)
            .toArray();

    int[] nowNumberPurchases = numberPurchases.clone();

    while (Arrays.stream(nowNumberPurchases).max().orElse(0) > 0) {
      int indexMaximumPurchaseVolume = IntStream.range(0, nowNumberPurchases.length)
              .reduce((i, j) -> nowNumberPurchases[i] > nowNumberPurchases[j] ? i : j)
              .orElse(-1);

      if (indexMaximumPurchaseVolume == -1) {
        break;
      }

      double[] fullFuelPrices = IntStream.range(0, numberSupplier)
              .mapToDouble(i -> {
                int purchaseVolume = Math.min(nowPurchaseVolume[i], nowNumberPurchases[indexMaximumPurchaseVolume]);
                return purchasePrice[i] + shippingCosts[i][indexMaximumPurchaseVolume] / purchaseVolume;
              })
              .toArray();

      int indexMinPrice = IntStream.range(0, fullFuelPrices.length)
              .filter(i -> nowPurchaseVolume[i] != 0)
              .reduce((i, j) -> fullFuelPrices[i] < fullFuelPrices[j] ? i : j)
              .orElse(-1);

      if (indexMinPrice == -1) {
        break;
      }

      int purchaseVolume = Math.min(nowPurchaseVolume[indexMinPrice], nowNumberPurchases[indexMaximumPurchaseVolume]);

      purchaseTable[indexMinPrice][indexMaximumPurchaseVolume] = purchaseVolume;
      nowPurchaseVolume[indexMinPrice] -= purchaseVolume;
      nowNumberPurchases[indexMaximumPurchaseVolume] -= purchaseVolume;
    }

    double[] purchaseCost = calculatePurchaseCost(gasolineSupplies, purchaseTable);
    double[] shippingCost = calculateShippingCost(gasolineSupplies, purchaseTable);

    return new PurchaseRecord(purchaseTable, purchaseCost, shippingCost);
  }

  public void printTable() {
    double[] purchaseCost = getPurchaseCost();
    double[] shippingCost = getShippingCost();

    System.out.println("____________________________________________________________________________________________________");
    System.out.printf("|%13s|  %6s  |  %6s  |  %6s  |  %6s  | %18s | %18s |\n",
            "Поставщик", "АЗС А", "АЗС Б", "АЗС В", "АЗС Г", "Стоимость закупки", "С учетом доставки");
    System.out.println("|__________________________________________________________________________________________________|");
    double total = 0;
    for (int i = 0; i < this.getPurchaseTable().length; i++) {
      total += purchaseCost[i] + shippingCost[i];
      int[] ints = this.getPurchaseTable()[i];
      System.out.printf("|%13s|  %6s  |  %6s  |  %6s  |  %6s  |  %16.2f  |  %16.2f  |\n",
              (i + 1), ints[0], ints[1], ints[2], ints[3], purchaseCost[i], (purchaseCost[i] + shippingCost[i]));
      System.out.println("|__________________________________________________________________________________________________|");
    }
    System.out.print("ИТОГО: ");
    System.out.printf("%.2f\n", total);
  }

  public int[][] getPurchaseTable() {
    return purchaseTable;
  }

  public double[] getPurchaseCost() {
    return purchaseCost;
  }

  public double[] getShippingCost() {
    return shippingCost;
  }

  public double getTotalCost() {
    return IntStream.range(0, purchaseCost.length)
            .mapToDouble(i -> purchaseCost[i] + shippingCost[i])
            .sum();
  }

  @Override
  public String toString() {
    return "PurchaseRecord{" +
            "purchaseTable=" + Arrays.toString(purchaseTable) +
            ", purchaseCost=" + Arrays.toString(purchaseCost) +
            ", shippingCost=" + Arrays.toString(shippingCost) +
            '}';
  }

}