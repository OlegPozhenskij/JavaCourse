package com.pozhenskii.Lab2.fourth;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Документ отгрузки со склада.
 * Бывает двух типов: перемещение (на другой склад) и продажа (покупателю).
 */
public abstract class Document {
    private String documentId; // GUID документа
    protected LocalDate documentDate; // дата документа
    protected DocType documentType; // тип отгрузки: sale - продажа, moving - перемещение
    protected Storage storage; // название склада отгрузки
    protected int itemsCount; // количество товаров в документе
    protected static final Random random = new Random();
    protected List<Product> catalog = new ArrayList<>();
    protected static final int NO_DISCOUNT = 0;

    public Document(String documentId, LocalDate documentDate, DocType documentType, Storage storage) {
        this.documentId = documentId;
        this.documentDate = documentDate;
        this.documentType = documentType;
        this.storage = storage;
        genRandomProductList();
        this.itemsCount = getItemsCount();
    }

    protected int getItemsCount() {
        int sum = 0;
        for (Product p : catalog) {
            sum += p.getItemsQuantity();
        }
        return sum;
    }

    protected void genRandomProductList() {
        for(int i = 0; i < 10 + random.nextInt(20); i++) {
            catalog.add(
                    new Product(
                            "id-" + i,
                            String.format("%015d", i),
                            "Prod " + i,
                            50 + random.nextDouble() * 2000,
                            10 + random.nextInt(20)
                    )
            );
        }
    }

    public double getTotalAmount() {
        double sum = 0;
        for (Product p : catalog) {
            sum += Math.round(p.getItemsQuantity() * p.getItemPrice() * 100) / 100.0;
        }
        return sum;
    }

    public double findItemAmount(String id) {
        for (Product p : catalog) {
            if (p.getItemId().equals(id)) {
                return Math.round(p.getItemsQuantity() * p.getItemPrice() * 100) / 100.0;
            }
        }
        return -1;
    }

    public  double getPromoSum(String[] promoArticles, double discount) {
        double sum = 0;
        for (Product p : catalog) {
            for (String promo : promoArticles) {
                if (p.getItemArticle().equals(promo)) {
                    sum = getCustomSum(discount, sum, p);
                    break;
                }
            }
        }
        return sum;
    }

    protected abstract double getCustomSum(double discount, double sum, Product p);
}