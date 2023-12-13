package com.pozhenskii.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private GasolineSupplies gasolineSupplies;

    @BeforeEach
    void setUp() {
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

        gasolineSupplies = new GasolineSupplies(suppliers);
    }

    @Test
    void testCalculatePurchaseRecord() {
        int[] numberPurchases = {100, 50, 200, 150};

        PurchaseRecord purchaseRecord = PurchaseRecord.calculatePurchaseRecord(gasolineSupplies, numberPurchases);

        assertNotNull(purchaseRecord);
        assertEquals(6, purchaseRecord.getPurchaseTable().length);

        assertFalse(Arrays.stream(purchaseRecord.getPurchaseTable())
                .flatMapToInt(Arrays::stream)
                .anyMatch(value -> value < 0));

        assertFalse(Arrays.stream(purchaseRecord.getPurchaseCost())
                .anyMatch(value -> value < 0));

        assertFalse(Arrays.stream(purchaseRecord.getShippingCost())
                .anyMatch(value -> value < 0));

    }

    @Test
    void testCalculatePurchaseRecordWithInvalidData() {
        int[] numberPurchases = {100, 50, -200, 150};

        PurchaseRecord purchaseRecord = PurchaseRecord.calculatePurchaseRecord(gasolineSupplies, numberPurchases);

        assertNull(purchaseRecord);
    }
}