package com.pozhenskii.Lab2.fourth;

public class Product {
    private String itemId;
    private String itemArticle;
    private String itemTitle;
    private double itemPrice;
    private double itemsQuantity;

    public Product(String itemId, String itemArticle, String itemTitle,
                   double itemPrice, double itemsQuantity) {
        this.itemId = itemId;
        this.itemArticle = itemArticle;
        this.itemTitle = itemTitle;
        this.itemPrice = itemPrice;
        this.itemsQuantity = itemsQuantity;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemArticle() {
        return itemArticle;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public double getItemsQuantity() {
        return itemsQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "itemId=" + itemId +
                ", itemArticle='" + itemArticle + '\'' +
                ", itemTitle='" + itemTitle + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemsQuantity=" + itemsQuantity +
                '}';
    }
}
