package com.pozhenskii.task2;

import java.util.Arrays;

public class TextProcessor {
    public static String processText(String inputText) {
        // Разбиваем текст на слова, исключаем короткие слова и приводим к верхнему регистру
        String[] words = Arrays.stream(inputText.split("[^a-zA-Zа-яА-Я]+"))
                .filter(word -> word.length() >= 3)
                .map(String::toUpperCase)
                .distinct()
                .sorted()
                .toArray(String[]::new);

        // Собираем результат в виде строки, разделяя слова пробелами
        return String.join(" ", words);
    }
}
