package com.pozhenskii.task1;

import com.pozhenskii.task1.UnicodeCharInfo.CharType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class UnicodeCharInfoTest {
    @Test
    void testN() {
        testChar('N', 78, "U+004E", 'O', 'M', CharType.UPPERCASE_LETTER, 14);
    }

    @Test
    void testSpace() {
        testChar(' ', 32, "U+0020", '!', '\u001F', CharType.WHITESPACE, -1);
    }

    @Test
    void testA() {
        testChar('A', 65, "U+0041", 'B', '@', CharType.UPPERCASE_LETTER, 1);
    }

    @Test
    void testDigit() {
        testChar('5', 53, "U+0035", '6', '4', CharType.DIGIT, -1);
    }

    @Test
    void testLambda() {
        testChar('λ', 955, "U+03BB", 'μ', 'κ', CharType.OTHER, -1);
    }

    @Test
    void testBackspace() {
        testChar('\b', 8, "U+0008", '\t', '\u0007', CharType.OTHER, -1 );
    }


    private void testChar(char character, int codePoint, String hexCodePoint, char nextChar, char prevChar, CharType charType, int alpNum) {
        UnicodeCharInfo unicodeCharInfo = new UnicodeCharInfo(character);

        assertEquals(codePoint, unicodeCharInfo.getCodePoint());
        assertEquals(hexCodePoint, unicodeCharInfo.getHexCodePoint());
        assertEquals(nextChar, unicodeCharInfo.getNextChar());
        assertEquals(prevChar, unicodeCharInfo.getPrevChar());
        assertEquals(charType, unicodeCharInfo.getCharType());
        assertEquals(alpNum, unicodeCharInfo.getAlphabetPosition());
    }
}