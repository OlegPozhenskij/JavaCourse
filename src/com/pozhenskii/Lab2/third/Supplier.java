package com.pozhenskii.Lab2.third;

public class Supplier {
    private long inn;
    private String name;
    private String address;

    public Supplier(long inn, String name, String address) {
        this.inn = inn;
        this.name = name;
        this.address = address;
    }

    public long getInn() {
        return inn;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
