package com.pozhenskii.Lab2.task3;

public class Dealer extends Supplier {
    private Manufacturer manufacturer;
    private double markupPercentage;

    public Dealer(long inn, String name, String address, Manufacturer manufacturer, double markupPercentage) {
        super(inn, name, address);
        this.manufacturer = manufacturer;
        this.markupPercentage = markupPercentage;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getMarkupPercentage() {
        return markupPercentage;
    }

    public void setMarkupPercentage(double markupPercentage) {
        this.markupPercentage = markupPercentage;
    }
}
