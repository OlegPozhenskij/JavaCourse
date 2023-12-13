package com.pozhenskii.task4;

import java.util.ArrayList;
import java.util.List;

public class TextManipulator {

    private String text;

    public TextManipulator(String text) {
        this.text = text;
    }

    public int[] search(String searchString) {
        return search(searchString, false);
    }

    public int[] search(String searchString, boolean caseSensitive) {
        String searchText = caseSensitive ? text : text.toLowerCase();
        String searchStr = caseSensitive ? searchString : searchString.toLowerCase();

        List<Integer> indexes = new ArrayList<>();
        int index = searchText.indexOf(searchStr);

        while (index != -1) {
            indexes.add(index);
            index = searchText.indexOf(searchStr, index + 1);
        }

        return indexes.stream().mapToInt(Integer::intValue).toArray();
    }

    public int replace(String searchStr, String replacementStr) {
        return replace(searchStr, replacementStr, false);
    }

    public int replace(String searchStr, String replacementStr, boolean caseSensitive) {
        String searchText = caseSensitive ? text : text.toLowerCase();
        String search = caseSensitive ? searchStr : searchStr.toLowerCase();

        int replacements = 0;
        int index = searchText.indexOf(search);

        while (index != -1) {
            text = text.substring(0, index) + replacementStr + text.substring(index + searchStr.length());
            searchText = caseSensitive ? text : text.toLowerCase();
            index = searchText.indexOf(search, index + replacementStr.length());
            replacements++;
        }

        return replacements;
    }

    public String getText() {
        return text;
    }
}