package com.pozhenskii.task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextValidatorTest {

    @Test
    void testEmail() {
        assertEquals("email", TextValidator.validateText("user.name@example.com"));
        assertEquals("none", TextValidator.validateText("@example.com"));
        assertEquals("username", TextValidator.validateText("user.name.example.com"));
        assertEquals("none", TextValidator.validateText("user.name@example"));
        assertEquals("email", TextValidator.validateText("user_name1@some.example.com"));
    }

    @Test
    void testPhone() {
        assertEquals("телефон", TextValidator.validateText("+7-(123)-456-78-90"));
        assertEquals("телефон", TextValidator.validateText("+7(123)456-78-90"));
        assertEquals("телефон", TextValidator.validateText("+7-123-456-78-90"));
        assertEquals("телефон", TextValidator.validateText("  +71234567890   "));
        assertEquals("none", TextValidator.validateText("71234567890"));
    }

    @Test
    void testINN() {
        assertEquals("ИНН", TextValidator.validateText("1234567890"));
        assertEquals("ИНН", TextValidator.validateText("000000000000"));
        assertEquals("none", TextValidator.validateText("7777-8888-9999"));
    }

    @Test
    void testUsername() {
        assertEquals("username", TextValidator.validateText("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.$1234_"));
        assertEquals("username", TextValidator.validateText("aaaa1111"));
        assertEquals("none", TextValidator.validateText("a"));
        assertEquals("none", TextValidator.validateText("qwerty 456"));
        assertEquals("none", TextValidator.validateText("4abc"));
        assertEquals("none", TextValidator.validateText("$asdfghjk"));
        assertEquals("none", TextValidator.validateText(" "));
    }
}