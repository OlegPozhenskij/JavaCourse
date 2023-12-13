package com.pozhenskii.task2;

import java.util.Arrays;

public class TextProcessor {
    public static String processText(String inputText) {
        String[] words = Arrays.stream(inputText.split("[^a-zA-Zа-яА-Я]+"))
                .filter(word -> word.length() >= 3)
                .map(String::toUpperCase)
                .distinct()
                .sorted()
                .toArray(String[]::new);

        return String.join(" ", words);
    }
}
