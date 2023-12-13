package com.pozhenskii.Task1;

import org.junit.jupiter.api.Test;

import java.util.stream.DoubleStream;

import static org.junit.jupiter.api.Assertions.*;

class DoubleStreamProcessorTest {
    @Test
    public void testCountZeros() {
        assertEquals(0, DoubleStreamProcessor.countZeros(DoubleStream.empty()));
        assertEquals(3, DoubleStreamProcessor.countZeros(DoubleStream.of(0.0, 0.0, 0.0)));
        assertEquals(0, DoubleStreamProcessor.countZeros(DoubleStream.of(1.0, 2.0, 3.0)));
    }

    @Test
    public void testHasFractionalPart() {
        assertFalse(DoubleStreamProcessor.hasFractionalPart(DoubleStream.empty()));
        assertTrue(DoubleStreamProcessor.hasFractionalPart(DoubleStream.of(1.5, 2.0, 3.8)));
        assertFalse(DoubleStreamProcessor.hasFractionalPart(DoubleStream.of(1.0, 2.0, 3.0)));
    }

    @Test
    public void testRange() {
        assertEquals(4.0, DoubleStreamProcessor.calculateRange(DoubleStream.of(1.0, 2.0, 3.0, 0.0, -1.0)), 0.001);
        assertEquals(0.0, DoubleStreamProcessor.calculateRange(DoubleStream.of(0.0, 0.0, 0.0)), 0.001);
        assertEquals(2.0, DoubleStreamProcessor.calculateRange(DoubleStream.of(0.0, 1.0, -1.0)), 0.001);
    }

    @Test
    public void testValuesGreaterThanLimit() {
        assertArrayEquals(new double[]{1.5, 2.0, 3.8},
                DoubleStreamProcessor.valuesGreaterThanLimit(DoubleStream.of(1.5, 2.0, 3.8, 0.5), 1.0), 0.001);
        assertArrayEquals(new double[]{},
                DoubleStreamProcessor.valuesGreaterThanLimit(DoubleStream.of(0.5, 0.2, -1.0), 1.0), 0.001);
    }

    @Test
    public void testLongestDecimalRepresentation() {
        assertEquals("12345.0987654321",
                DoubleStreamProcessor.longestDecimalRepresentation(
                        DoubleStream.of(12345.0987654321, 12.345, 1.0, 0.0, -1.0)));
        assertEquals("0.001",
                DoubleStreamProcessor.longestDecimalRepresentation(DoubleStream.of(0.1, 0.01, 0.001)));
    }
}