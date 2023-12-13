package com.pozhenskii.task3;

import java.util.Arrays;

public class GasolineSupplies {

  private final Supplier[] suppliers;

  public GasolineSupplies(Supplier ... suppliers) {
    this.suppliers = suppliers;
  }

  public void printTable() {
    System.out.println("-------------------------------------------------------------------------------------------------");
    System.out.printf("|%13s| %12s| %15s| %11s| %11s| %11s| %10s|\n", "Поставщик", "Макс. объём", "Цена закупки", "А", "Б", "В", "Г");
    System.out.println("|_______________________________________________________________________________________________|");
    for (int i = 0; i < suppliers.length; i++) {
      Supplier supplier = suppliers[i];
      System.out.printf("|%13s| %12s| %15s| %11s| %11s| %11s| %10s|\n",
              (i + 1), supplier.getMaximumPurchaseVolume(), supplier.getPurchasePrice(),
              supplier.getShippingCosts()[0], supplier.getShippingCosts()[1], supplier.getShippingCosts()[2], supplier.getShippingCosts()[3]);
      System.out.println("|_______________________________________________________________________________________________|");
    }
  }

  public Supplier[] getSuppliers() {
    return suppliers;
  }

  @Override
  public String toString() {
    return "GasolineSupplies{" +
            "suppliers=" + Arrays.toString(suppliers) +
            '}';
  }
}
