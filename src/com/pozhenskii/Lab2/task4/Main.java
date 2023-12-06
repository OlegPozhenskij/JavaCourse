package com.pozhenskii.Lab2.task4;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Owner owner1 = new Owner("Олег");
        Owner owner2 = new Owner("Дарья");

        Storage s1 = new Storage("Самарский", owner1);
        Storage s2 = new Storage("Самарский", owner2);

        SaleShipmentDocument saleDocument = new SaleShipmentDocument(
                "123id",
                LocalDate.now(),
                s1,
                "David"
        );
        MovingShipmentDocument movingDocument = new MovingShipmentDocument(
                "789id",
                LocalDate.now(),
                s1,
                s2
        );

        System.out.println(saleDocument);
        System.out.println("getTotalAmount: " + saleDocument.getTotalAmount());
        System.out.println("findItemAmount: " + saleDocument.findItemAmount("id-3"));
        System.out.println("getPromoSum, со скидкой 25%: " + saleDocument.getPromoSum(new String[]{"000000000000003"}, 25));
        System.out.println("isWholesale: " + saleDocument.isWholesale(30));

        System.out.println("--------------");

        System.out.println(movingDocument);
        System.out.println("getTotalAmount: " + movingDocument.getTotalAmount());
        System.out.println("findItemAmount: " + movingDocument.findItemAmount("id-5"));
        System.out.println("getPromoSum, без скидки: " + movingDocument.getPromoSum(new String[]{"000000000000005"}, ShipmentDocument.NO_DISCOUNT));
        System.out.println("isInternalMovement: " + movingDocument.isInternalMovement());

    }
}
