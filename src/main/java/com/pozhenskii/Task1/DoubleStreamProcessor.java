package com.pozhenskii.Task1;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

public class DoubleStreamProcessor {

    public static long countZeros(DoubleStream doubleStream) {
        return doubleStream.filter(value -> value == 0.0).count();
    }

    public static boolean hasFractionalPart(DoubleStream doubleStream) {
        return doubleStream.anyMatch(value -> value % 1 != 0);
    }

    public static double calculateRange(DoubleStream doubleStream) {
        DoubleSummaryStatistics stats = doubleStream.summaryStatistics();
        if (stats.getMin() != 0.0 && stats.getMax() != 0.0) {
            return Math.abs(stats.getMin() - stats.getMax());
        }

        return 0.0;
    }

    public static double[] valuesGreaterThanLimit(DoubleStream doubleStream, double limit) {
        return doubleStream.filter(value -> value > limit).toArray();
    }

    public static String longestDecimalRepresentation(DoubleStream doubleStream) {
        return doubleStream
                .mapToObj(Double::toString)
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }
}
