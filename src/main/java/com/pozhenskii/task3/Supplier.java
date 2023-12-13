package com.pozhenskii.task3;

import java.util.Arrays;

public class Supplier {
    private final int maximumPurchaseVolume;
    private final double purchasePrice;
    private final double[] shippingCosts;

    public Supplier(int maximumPurchaseVolume, double purchasePrice, double[] shippingCosts) {
        this.maximumPurchaseVolume = maximumPurchaseVolume;
        this.purchasePrice = purchasePrice;
        this.shippingCosts = shippingCosts;
    }

    public int getMaximumPurchaseVolume() {
        return maximumPurchaseVolume;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double[] getShippingCosts() {
        return shippingCosts;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "maximumPurchaseVolume=" + maximumPurchaseVolume +
                ", purchasePrice=" + purchasePrice +
                ", shippingCosts=" + Arrays.toString(shippingCosts) +
                '}';
    }
}