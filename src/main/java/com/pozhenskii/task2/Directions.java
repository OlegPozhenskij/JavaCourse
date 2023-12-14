package com.pozhenskii.task2;

public enum Directions {
    UP("Вверх", '↑', new int[]{0, 1}),
    DOWN("Вниз", '↓', new int[]{0, -1}),
    LEFT("Влево", '←', new int[]{-1, 0}),
    RIGHT("Вправо", '→', new int[]{1, 0});

    private final String rusName;
    private final char keyCode;
    private final int[] vector;

    Directions(String rusName, char keyCode, int[] vector) {
        this.rusName = rusName;
        this.keyCode = keyCode;
        this.vector = vector;
    }

    public String getRusName() {
        return rusName;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public int[] getVector() {
        return vector;
    }
}
