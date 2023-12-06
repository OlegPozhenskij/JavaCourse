package com.pozhenskii.Lab2.task4;

import java.time.LocalDate;

public class MovingShipmentDocument extends ShipmentDocument {

    private Storage movingStorage;

    public MovingShipmentDocument(String documentId, LocalDate documentDate, Storage storage, Storage movingStorage) {
        super(documentId, documentDate, DocType.MOVING, storage);
        this.movingStorage = movingStorage;
    }

    @Override
    protected double getCustomSum(double discount, double sum, Product p) {
        double withDis = p.getItemsQuantity() * p.getItemPrice();
        return Math.round(withDis * 100) / 100.0;
    }

    boolean isInternalMovement() {
        return documentType == DocType.MOVING
                &&
                storage.getOwner().equals(movingStorage.getOwner());
    }

    @Override
    public String toString() {
        return "MovingDocument{" +
                "documentDate=" + documentDate +
                ", documentType=" + documentType +
                ", storage=" + storage +
                ", itemsCount=" + getItemsCount() +
                ", catalog=" + catalog +
                ", movingStorage=" + movingStorage +
                '}';
    }
}
