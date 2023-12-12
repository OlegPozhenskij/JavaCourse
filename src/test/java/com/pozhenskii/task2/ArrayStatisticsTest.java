package com.pozhenskii.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStatisticsTest {
    private ArrayStatistics emptyArrayStats;
    private ArrayStatistics singleElementArrayStats;
    private ArrayStatistics smallArrayStats;
    private ArrayStatistics manualArrayStats;
    private ArrayStatistics largeRandomArrayStats;
    private int seed;

    @BeforeEach
    void setUp() {
        seed = 1234;
        emptyArrayStats = new ArrayStatistics(new int[]{});
        singleElementArrayStats = new ArrayStatistics(new int[]{42});
        smallArrayStats = new ArrayStatistics(new int[]{1, 2, 3});
        manualArrayStats = new ArrayStatistics(new int[]{5, 2, 8, 3, 1, 7, 6, 4, 9, 10});
        largeRandomArrayStats = new ArrayStatistics(generateLargeRandomArray(100_000, seed));
    }

    @Test
    void testMode() {
        assertArrayEquals(new int[]{}, emptyArrayStats.mode());
        assertArrayEquals(new int[]{42}, singleElementArrayStats.mode());
        assertArrayEquals(new int[]{1, 2, 3}, smallArrayStats.mode());
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, manualArrayStats.mode());
        assertArrayEquals(calculateExpectedModeForLargeRandomArrayStats(), largeRandomArrayStats.mode());
    }

    private int[] calculateExpectedModeForLargeRandomArrayStats() {
        // Пример простой реализации для поиска режима
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : largeRandomArrayStats.getOriginalArray()) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Находим максимальную частоту
        int maxFrequency = Collections.max(frequencyMap.values());

        // Фильтруем значения с максимальной частотой
        List<Integer> modes = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                modes.add(entry.getKey());
            }
        }

        // Преобразуем в массив
        int[] expectedMode = new int[modes.size()];
        for (int i = 0; i < modes.size(); i++) {
            expectedMode[i] = modes.get(i);
        }

        return expectedMode;
    }

    @Test
    void testMedian() {
        assertEquals(0, emptyArrayStats.median());
        assertEquals(42, singleElementArrayStats.median());
        assertEquals(2, smallArrayStats.median());
        assertEquals(5.5, manualArrayStats.median());
    }

    @Test
    void testAverage() {
        assertEquals(0, emptyArrayStats.average());
        assertEquals(42, singleElementArrayStats.average());
        assertEquals(2, smallArrayStats.average());
        assertEquals(5.5, manualArrayStats.average());
    }

    @Test
    void testVariance() {
        assertEquals(0, emptyArrayStats.variance());
        assertEquals(0, singleElementArrayStats.variance());
        assertEquals(2.0, smallArrayStats.variance(), 0.0001);
        assertEquals(82.5, manualArrayStats.variance(), 0.0001);
    }

    @Test
    void testGeomMean() {
        assertEquals(0, emptyArrayStats.geomMean());
        assertEquals(42, singleElementArrayStats.geomMean());
        assertEquals(1.8171205928321397, smallArrayStats.geomMean(), 0.0001);
        assertEquals(4.528728688116765, manualArrayStats.geomMean(), 0.0001);
    }

    @Test
    void testShuffle() {
        assertArrayEquals(emptyArrayStats.shuffle(seed), emptyArrayStats.shuffle(seed));
        assertArrayEquals(singleElementArrayStats.shuffle(seed), singleElementArrayStats.shuffle(seed));
        assertArrayEquals(smallArrayStats.shuffle(seed), smallArrayStats.shuffle(seed));
        assertArrayEquals(manualArrayStats.shuffle(seed), manualArrayStats.shuffle(seed));
        assertArrayEquals(largeRandomArrayStats.shuffle(seed), largeRandomArrayStats.shuffle(seed));

        assertFalse(Arrays.equals(smallArrayStats.shuffle(seed), smallArrayStats.shuffle(-seed)));

        assertArrayEquals(new int[]{1, 2, 3}, smallArrayStats.getOriginalArray());
    }

    @Test
    void testSample() {
        assertEquals(0, emptyArrayStats.sample(0).length);
        assertEquals(1, singleElementArrayStats.sample(1).length);
        assertEquals(3, smallArrayStats.sample(3).length);
        assertEquals(5, manualArrayStats.sample(5).length);
        assertEquals(5, largeRandomArrayStats.sample(5).length);

        assertTrue(Arrays.stream(manualArrayStats.sample(5)).allMatch(value -> Arrays.stream(manualArrayStats.getOriginalArray()).anyMatch(original -> original == value)));
    }


    private int[] generateLargeRandomArray(int size, int seed) {
        var random = new Random(seed);
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }

        return array;
    }

}