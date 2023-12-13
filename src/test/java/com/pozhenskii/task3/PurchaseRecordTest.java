package com.pozhenskii.task3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PurchaseRecordTest {

    @Test
    public void testCalculatePurchaseRecordWithNotNullAndCostBiggerThenZero() {
        double[][] shippingCosts = {
                {803, 952, 997, 931},
                {967, 1012, 848, 1200},
                {825, 945, 777, 848},
                {1024, 1800, 931, 999},
                {754, 817, 531, 628},
                {911, 668, 865, 1526}
        };

        int[] maximumPurchaseVolume = {600, 120, 360, 250, 700, 390};
        double[] purchasePrice = {5.2, 4.5, 6.1, 3.8, 6.4, 5.6};

        Supplier[] suppliers = new Supplier[6];
        for (int i = 0; i < suppliers.length; i++) {
            suppliers[i] = new Supplier(maximumPurchaseVolume[i], purchasePrice[i], shippingCosts[i]);
        }

        GasolineSupplies gasolineSupplies = new GasolineSupplies(suppliers);
        int[] numberPurchases = {100, 50, 200, 150};

        PurchaseRecord purchaseRecord = PurchaseRecord.calculatePurchaseRecord(gasolineSupplies, numberPurchases);

        assertNotNull(purchaseRecord);

        assertTrue(purchaseRecord.getTotalCost() >= 0);
    }

    @Test
    public void testCalculatePurchaseRecordWithNegativeValues() {
        double[][] shippingCosts = {
                {803, 952, 997, 931},
                {967, 1012, 848, 1200},
                {825, 945, 777, 848},
                {1024, 1800, 931, 999},
                {754, 817, 531, 628},
                {911, 668, 865, 1526}
        };

        int[] maximumPurchaseVolume = {600, 120, 360, 250, 700, 390};
        double[] purchasePrice = {5.2, 4.5, 6.1, 3.8, 6.4, 5.6};

        Supplier[] suppliers = new Supplier[6];
        for (int i = 0; i < suppliers.length; i++) {
            suppliers[i] = new Supplier(maximumPurchaseVolume[i], purchasePrice[i], shippingCosts[i]);
        }

        GasolineSupplies gasolineSupplies = new GasolineSupplies(suppliers);
        int[] numberPurchasesWithNegative = {100, -50, 200, 150};

        PurchaseRecord purchaseRecord = PurchaseRecord.calculatePurchaseRecord(gasolineSupplies, numberPurchasesWithNegative);

        assertNull(purchaseRecord);
    }

    @Test
    void calculatePurchaseRecordTest() {
        double[][] shippingCosts = {
                {803, 952, 997, 931},
                {967, 1012, 848, 1200},
                {500, 500, 500, 500},
                {1024, 1800, 931, 999},
                {754, 817, 531, 628},
                {911, 668, 865, 1526}
        };

        int[] maximumPurchaseVolume = {600, 120, 1000, 250, 700, 390};
        double[] purchasePrice = {5.2, 4.5, 6.1, 3.8, 6.4, 5.6};

        GasolineSupplies gasolineSupplies = new GasolineSupplies(
                new Supplier(maximumPurchaseVolume[0], purchasePrice[0], shippingCosts[0]),
                new Supplier(maximumPurchaseVolume[1], purchasePrice[1], shippingCosts[1]),
                new Supplier(maximumPurchaseVolume[2], purchasePrice[2], shippingCosts[2]),
                new Supplier(maximumPurchaseVolume[3], purchasePrice[3], shippingCosts[3]),
                new Supplier(maximumPurchaseVolume[4], purchasePrice[4], shippingCosts[4]),
                new Supplier(maximumPurchaseVolume[5], purchasePrice[5], shippingCosts[5])
        );

        PurchaseRecord purchaseRecord = PurchaseRecord.calculatePurchaseRecord(gasolineSupplies, new int[]{300, 200});

        assertNull(purchaseRecord);

        int[] numberPurchases = {400, 550, 280, 310};
        purchaseRecord = PurchaseRecord.calculatePurchaseRecord(gasolineSupplies, numberPurchases);

        assertNotNull(purchaseRecord);

        int[] sum = new int[4];
        for (int i = 0; i < purchaseRecord.getPurchaseTable().length; i++) {
            for (int j = 0; j < purchaseRecord.getPurchaseTable()[i].length; j++) {
                sum[j] += purchaseRecord.getPurchaseTable()[i][j];
            }
        }

        assertArrayEquals(numberPurchases, sum);

        purchaseRecord.printTable();
    }


}