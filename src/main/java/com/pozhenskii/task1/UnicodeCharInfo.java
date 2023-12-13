package com.pozhenskii.task1;

public class UnicodeCharInfo {
    private char character;

    public UnicodeCharInfo(char character) {
        this.character = character;
    }

    public int getCodePoint() {
        return character;
    }

    public String getHexCodePoint() {
        return String.format("U+%04X", (int) character);
    }

    public char getNextChar() {
        return (char) (character + 1);
    }

    public char getPrevChar() {
        return (char) (character - 1);
    }

    public CharType getCharType() {
        if (Character.isDigit(character)) {
            return CharType.DIGIT;
        } else if (Character.isLetter(character)) {
            if (Character.isUpperCase(character)) {
                return CharType.UPPERCASE_LETTER;
            } else {
                return CharType.LOWERCASE_LETTER;
            }
        } else if (Character.isWhitespace(character)) {
            return CharType.WHITESPACE;
        } else {
            return CharType.OTHER;
        }
    }

    public enum CharType {
        DIGIT,
        UPPERCASE_LETTER,
        LOWERCASE_LETTER,
        WHITESPACE,
        OTHER
    }


    public static void main(String[] args) {
        var ci =  new UnicodeCharInfo('λ');
        System.out.println(ci.getCodePoint());
        System.out.println(ci.getHexCodePoint());
    }
}
