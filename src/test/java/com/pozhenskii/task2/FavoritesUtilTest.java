package com.pozhenskii.task2;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FavoritesUtilTest {

    @Test
    void testFindCommonFavorites() {
        Set<String> set1 = new HashSet<>(Arrays.asList("A", "B", "C"));
        Set<String> set2 = new HashSet<>(Arrays.asList("B", "C", "D"));
        Set<String> set3 = new HashSet<>(Arrays.asList("C", "D", "E"));

        Collection<Set<String>> sets = Arrays.asList(set1, set2, set3);

        Set<String> result = FavoritesUtil.findCommonFavorites(sets);

        assertEquals(new HashSet<>(Arrays.asList("C")), result);
    }

    @Test
    void testFindAllFavorites() {
        Set<String> set1 = new HashSet<>(Arrays.asList("A", "B", "C"));
        Set<String> set2 = new HashSet<>(Arrays.asList("B", "C", "D"));

        Collection<Set<String>> sets = Arrays.asList(set1, set2);

        Set<String> result = FavoritesUtil.findAllFavorites(sets);

        assertEquals(new HashSet<>(Arrays.asList("A", "B", "C", "D")), result);
    }

    @Test
    void testFindExclusiveFavorites() {
        Map<String, Set<String>> userFavoritesMap = new HashMap<>();
        userFavoritesMap.put("User1", new HashSet<>(Arrays.asList("A", "B", "C")));
        userFavoritesMap.put("User2", new HashSet<>(Arrays.asList("B", "C", "D")));
        userFavoritesMap.put("User3", new HashSet<>(Arrays.asList("C", "D", "E")));

        Set<String> result1 = FavoritesUtil.findExclusiveFavorites("User1", userFavoritesMap);
        assertEquals(new HashSet<>(Arrays.asList("A")), result1);
        Set<String> result2 = FavoritesUtil.findExclusiveFavorites("User2", userFavoritesMap);
        assertEquals(new HashSet<>(Arrays.asList()), result2);
        Set<String> result3 = FavoritesUtil.findExclusiveFavorites("User3", userFavoritesMap);
        assertEquals(new HashSet<>(Arrays.asList("E")), result3);

    }

    @Test
    void testFormatFavorites() {
        Set<String> favorites = new HashSet<>(Arrays.asList("C", "A", "B"));

        String result = FavoritesUtil.formatFavorites(favorites);

        assertEquals("A, B, C", result);
    }

    @Test
    void testCountFavorites() {
        Set<String> set1 = new HashSet<>(Arrays.asList("A", "B", "C"));
        Set<String> set2 = new HashSet<>(Arrays.asList("B", "C", "D"));
        Set<String> set3 = new HashSet<>(Arrays.asList("C", "D", "E"));

        Collection<Set<String>> sets = Arrays.asList(set1, set2, set3);

        Map<String, Long> result = FavoritesUtil.countFavorites(sets);

        Map<String, Long> expected = new HashMap<>();
        expected.put("A", 1L);
        expected.put("B", 2L);
        expected.put("C", 3L);
        expected.put("D", 2L);
        expected.put("E", 1L);

        assertEquals(expected, result);
    }
}