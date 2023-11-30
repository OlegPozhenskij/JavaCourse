package com.pozhenskii.Lab2.fourth;

import java.time.LocalDate;

public class MovingDocument extends Document {

    private Storage movingStorage;

    public MovingDocument(String documentId, LocalDate documentDate, Storage storage, Storage movingStorage) {
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
                ", itemsCount=" + itemsCount +
                ", catalog=" + catalog +
                ", movingStorage=" + movingStorage +
                '}';
    }
}
