package com.pozhenskii.task3;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Arrays.*;

public class Transliterator {

    private static final String[] rusLet = {"а", "б", "в", "г", "д", "е", "з", "и", "й", "к", "л", "м", "н", "о", "п", "р",
            "с", "т", "у", "ф", "х", "ь", "ц", "я", "ё", "ю", "ж", "ч", "ш", "э", "ъ", "ы", "це", "цы", "цй", "ци", "щ"};
    private static final String[] latLet = {"a", "b", "v", "g", "d", "e", "z", "i", "j", "k", "l", "m", "n", "o", "p", "r",
            "s", "t", "u", "f", "x", "`", "cz", "ya", "yo", "yu", "zh", "ch", "sh", "e`", "``", "y`", "ce", "c``", "cj", "ci", "shh"};

    public static String rusToLat(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .map(currentChar -> (currentChar != 'ц') ? rusToLat(currentChar) : rusToLat(currentChar, input.charAt(new AtomicInteger().getAndIncrement() + 1)))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    private static String rusToLat(char rusLetter, char... rusSecondLetter) {
        char rusLetterLower = Character.toLowerCase(rusLetter);
        if (!Character.UnicodeBlock.of(rusLetter).equals(Character.UnicodeBlock.CYRILLIC)) {
            return Character.toString(rusLetter);
        }

        int index;
        if (rusSecondLetter.length == 1) {
            char rusSecondLetterLower = Character.toLowerCase(rusSecondLetter[0]);
            index = (rusSecondLetterLower == 'е' || rusSecondLetterLower == 'ы' || rusSecondLetterLower == 'й' || rusSecondLetterLower == 'и') ?
                    asList(rusLet).indexOf(rusLetterLower + "" + rusSecondLetterLower) :
                    asList(rusLet).indexOf(Character.toString(rusLetterLower));
        } else {
            index = asList(rusLet).indexOf(Character.toString(rusLetterLower));
        }

        String latinLetter = latLet[index];
        if (Character.isUpperCase(rusLetter)) {
            latinLetter = latinLetter.substring(0, 1).toUpperCase() + latinLetter.substring(1);
        }
        return latinLetter;
    }

    public static String latToRus(String input) {
        StringBuilder result = new StringBuilder(input);
        StringBuilder inputLower = new StringBuilder(input.toLowerCase());

        for (int i = latLet.length - 1; i > -1;) {
            int index = inputLower.indexOf(latLet[i]);

            if (index >= 0) {
                String match = result.substring(index, index + latLet[i].length());
                String replacement = rusLet[i];

                if (Character.isUpperCase(match.charAt(0))) {
                    StringBuilder replacementSb = new StringBuilder(replacement);
                    replacementSb.setCharAt(0, Character.toUpperCase(replacementSb.charAt(0)));
                    replacement = replacementSb.toString();
                }

                result.replace(index, index + latLet[i].length(), replacement);
                inputLower.replace(index, index + latLet[i].length(), replacement);
            } else {
                i--;
            }
        }

        return result.toString();
    }

}