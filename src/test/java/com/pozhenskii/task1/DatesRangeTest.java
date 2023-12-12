package com.pozhenskii.task1;


import org.junit.jupiter.api.Test;

import static com.pozhenskii.task1.DatesRange.getDateRangeArr;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class DatesRangeTest {

    @Test
    public void testDateRange() {
        String[] expected = {"2023-01-01", "2023-01-02", "2023-01-03", "2023-01-04"};
        assertArrayEquals(expected, getDateRangeArr("2023-01-01", "2023-01-04"));
    }

    @Test
    public void testSingleDate() {
        String[] expected = {"2023-01-01"};
        assertArrayEquals(expected, getDateRangeArr("2023-01-01", "2023-01-01"));
    }

    @Test
    public void testInvalidDateRange() {
        String[] expected = {};
        assertArrayEquals(expected, getDateRangeArr("2023-01-05", "2023-01-01"));
    }

    @Test
    public void testSameDate() {
        String[] expected = {"2023-01-01"};
        assertArrayEquals(expected, getDateRangeArr("2023-01-01", "2023-01-01"));
    }

}