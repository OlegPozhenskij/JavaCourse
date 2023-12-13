package com.pozhenskii.task2;

import java.util.regex.Pattern;

public class TextValidator {
    public static String validateText(String text) {
        text = text.trim();

        if (Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", text)) {
            return "email";
        }

        if (Pattern.matches("\\+7-?\\(?[\\d]{3}\\)?-?\\(?[\\d]{3}\\)?-?[\\d]{2}-?[\\d]{2}", text)) {
            return "телефон";
        }

        if (Pattern.matches("\\d{10}|\\d{12}", text)) {
            return "ИНН";
        }

        if (Pattern.matches("[a-zA-Z][a-zA-Z0-9_$\\.]{7,}", text)) {
            return "username";
        }

        return "none";
    }
}
