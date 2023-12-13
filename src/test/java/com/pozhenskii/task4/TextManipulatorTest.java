package com.pozhenskii.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextManipulatorTest {
    @Test
    public void testSearchCaseInsensitive() {
        TextManipulator manipulator = new TextManipulator("Hello World, hello Java!");

        int[] result = manipulator.search("hello");
        assertArrayEquals(new int[]{0, 13}, result);

        result = manipulator.search("WORLD");
        assertArrayEquals(new int[]{6}, result);
    }

    @Test
    public void testSearchCaseSensitive() {
        TextManipulator manipulator = new TextManipulator("Hello World, hello Java!");

        int[] result = manipulator.search("hello", true);
        assertArrayEquals(new int[]{13}, result);

        result = manipulator.search("WORLD", true);
        assertArrayEquals(new int[]{}, result);
    }

    @Test
    public void testReplaceCaseInsensitive() {
        TextManipulator manipulator = new TextManipulator("Hello World, hello Java!");

        int replacements = manipulator.replace("hello", "Hi");
        assertEquals(2, replacements);
        assertEquals("Hi World, Hi Java!", manipulator.getText());
    }

    @Test
    public void testReplaceCaseSensitive() {
        TextManipulator manipulator = new TextManipulator("Hello World, hello Java!");

        int replacements = manipulator.replace("hello", "Hi", true);
        assertEquals(1, replacements);
        assertEquals("Hello World, Hi Java!", manipulator.getText());
    }

    @Test
    public void testReplaceMultipleOccurrences() {
        TextManipulator manipulator = new TextManipulator("Hello World, Hello World!");

        int replacements = manipulator.replace("hello", "Hi");
        assertEquals(2, replacements);
        assertEquals("Hi World, Hi World!", manipulator.getText());
    }

}