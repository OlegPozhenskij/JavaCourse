package com.pozhenskii.Lab2.task3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Manufacturer man1 = new Manufacturer(1231231231L, "Lada", "Toglliaty");
        Manufacturer man2 = new Manufacturer(4564564564L, "Prostokvashino", "Moscow");
        Manufacturer man3 = new Manufacturer(7897897897L, "BeerLand", "London");

        Dealer deal1 = new Dealer(1234567890L, "Perekrestok", "Samara", man1, 1.2d);
        Dealer deal2 = new Dealer(1357924680L, "Web Zavod", "Samara", man2, 1.5d);

        List<Product> productCatalog = generateProductCatalog(15, man1, man2, man3, deal1, deal2);

        System.out.println("Список артикулов: ");
        for (Product p : productCatalog) {
            System.out.println(p.getArticle());
            System.out.println(p.getManufacturerPrice());
            System.out.println(p.getName());
            System.out.println(p.getSupplier());
            System.out.println();
        }

        while (true) {
            System.out.println("Введите артикул товара: ");
            String art = scanner.nextLine();
            boolean isFound = false;

            if(!art.isEmpty()) {
                for (Product p : productCatalog) {
                    if(p.getArticle().equals(art)) {
                        System.out.println("Наименование товара: " + p.getName());

                        System.out.println("Наименование и адрес поставщика:" +
                                p.getSupplier().getName() + " " + p.getSupplier().getAddress());

                        if (p.getSupplier() instanceof Dealer) {
                            System.out.println("Цена (для дилера – с учетом наценки): " +
                                    p.getManufacturerPrice() * ((Dealer)p.getSupplier()).getMarkupPercentage());
                            System.out.println("Реальный производитель: " +
                                    ((Dealer) p.getSupplier()).getManufacturer().getName() + "\n");
                        } else {
                            System.out.println("Цена: " + p.getManufacturerPrice() + "\n");
                        }

                        isFound = true;
                    }
                }
                if (!isFound) {
                    System.out.println("Товар был не найден, попробуйте ещё раз!" + "\n");
                }
            }
        }

    }

    private static List<Product> generateProductCatalog(int productAmount, Supplier... sup) {
        List<Product> catalog = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < productAmount; i++) {
            catalog.add(
                    new Product(
                            "Товар " + i,
                            50 + random.nextDouble() * 150,
                            String.format("%015d", i),
                            sup[random.nextInt(sup.length)]
                    )
            );
        }

        return catalog;
    }
}
