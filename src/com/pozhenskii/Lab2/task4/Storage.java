package com.pozhenskii.Lab2.task4;

public class Storage {
    private String name;
    private Owner owner;

    public Storage(String name, Owner owner) {
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public Owner getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "name='" + name + '\'' +
                ", owner=" + owner +
                '}';
    }
}
