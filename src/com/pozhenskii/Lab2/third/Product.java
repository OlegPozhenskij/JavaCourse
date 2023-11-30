package com.pozhenskii.Lab2.third;

public class Product {
    private String article;
    private String name;
    private double manufacturerPrice;
    private Supplier supplier;

    public Product(String name, double manufacturerPrice, String article, Supplier supplier) {
        this.name = name;
        this.manufacturerPrice = manufacturerPrice;
        this.article = article;
        this.supplier = supplier;
    }

    public String getArticle() {
        return article;
    }

    public String getName() {
        return name;
    }

    public double getManufacturerPrice() {
        return manufacturerPrice;
    }

    public Supplier getSupplier() {
        return supplier;
    }
}
