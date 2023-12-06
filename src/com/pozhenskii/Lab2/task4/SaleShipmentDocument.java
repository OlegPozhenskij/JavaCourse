package com.pozhenskii.Lab2.task4;

import java.time.LocalDate;

public class SaleShipmentDocument extends ShipmentDocument {

    private String customer;

    public SaleShipmentDocument(String documentId, LocalDate documentDate, Storage storage, String customer) {
        super(documentId, documentDate, DocType.SALE, storage);
        this.customer = customer;
    }

    @Override
    protected double getCustomSum(double discount, double sum, Product p) {
        double withDis = p.getItemsQuantity() * p.getItemPrice() * (1 - discount / 100);
        return Math.ceil(withDis * 100) / 100.0;
    }

    public boolean isWholesale(double minQuantity) {
        double sumQuantity = 0;

        for (Product p : catalog) {
            if(p.getItemsQuantity() >= minQuantity) {
                return true;
            }
            sumQuantity += p.getItemsQuantity();
        }
        return sumQuantity >= minQuantity;
    }

    @Override
    public String toString() {
        return "SaleDocument{" +
                "documentDate=" + documentDate +
                ", documentType=" + documentType +
                ", storage=" + storage +
                ", itemsCount=" + getItemsCount() +
                ", catalog=" + catalog +
                ", customer='" + customer + '\'' +
                '}';
    }
}