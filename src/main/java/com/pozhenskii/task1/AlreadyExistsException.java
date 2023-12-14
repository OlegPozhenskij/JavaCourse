package com.pozhenskii.task1;

public class AlreadyExistsException extends Exception {

    private String value;
    private int position;

    public AlreadyExistsException(String value, int position) {
        super("Значение '" + value + "' уже было введено на позиции " + position);
        this.value = value;
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public int getPosition() {
        return position;
    }
}
