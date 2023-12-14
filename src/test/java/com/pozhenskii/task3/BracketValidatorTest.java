package com.pozhenskii.task3;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BracketValidatorTest {

    @Test
    void testValidBracketSequence() {
        String input = "({[()]})";
        int result = BracketValidator.checkBrackets(input);
        assertEquals(-1, result, "Скобочная последовательность верна");
    }

    @Test
    void testInvalidBracketSequence() {
        String input = "({[()})";
        int result = BracketValidator.checkBrackets(input);
        assertEquals(5, result, "Ошибка в позиции 5");
    }

    @Test
    void testEmptyBracketSequence() {
        String input = "";
        int result = BracketValidator.checkBrackets(input);
        assertEquals(-1, result, "Скобочная последовательность верна (пустая)");
    }

    @Test
    void testNoClosingBracket() {
        String input = "({[()])";
        int result = BracketValidator.checkBrackets(input);
        assertEquals(6, result, "Ошибка в позиции 6 (нет закрывающей скобки)");
    }

    @Test
    void testNoOpeningBracket() {
        String input = ")}]";
        int result = BracketValidator.checkBrackets(input);
        assertEquals(0, result, "Ошибка в позиции 0 (нет открывающей скобки)");
    }

    @Test
    void testWithAlphabets() {
        String input = "[a{(d)}c]";
        int result = BracketValidator.checkBrackets(input);
        assertEquals(-1, result, "Скобочная последовательность c буквами верна");
    }
}
