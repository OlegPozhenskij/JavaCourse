package com.pozhenskii.Lab2.fourth;

public class Owner {
    private String name;

    public Owner(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\'' +
                '}';
    }
}
