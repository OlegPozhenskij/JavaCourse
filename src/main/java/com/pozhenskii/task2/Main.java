package com.pozhenskii.task2;

class Main {
    public static void main(String[] args) {
        for (Directions direction : Directions.values()) {
            System.out.println("Направление: " + direction.getRusName());
            System.out.println("Код символа на клавиатуре: " + direction.getKeyCode());
            System.out.println("Вектор движения: [" + direction.getVector()[0] + ", " + direction.getVector()[1] + "]\n");
        }
    }
}