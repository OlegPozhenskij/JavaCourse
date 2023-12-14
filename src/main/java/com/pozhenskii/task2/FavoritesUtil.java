package com.pozhenskii.task2;

import java.util.*;
import java.util.stream.Collectors;

public class FavoritesUtil {
    static Set<String> findCommonFavorites(Collection<Set<String>> userFavoritesSets) {
        return userFavoritesSets.stream()
                .reduce((set1, set2) -> {
                    Set<String> commonFavorites = new HashSet<>(set1);
                    commonFavorites.retainAll(set2);
                    return commonFavorites;
                })
                .orElse(Collections.emptySet());
    }

    static Set<String> findAllFavorites(Collection<Set<String>> userFavoritesSets) {
        return userFavoritesSets.stream()
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    static Set<String> findExclusiveFavorites(String userName, Map<String, Set<String>> userFavoritesMap) {
        Set<String> exclusiveFavorites = new HashSet<>();
        Set<String> userFavorites = new HashSet<>(userFavoritesMap.getOrDefault(userName, null));

        for (Map.Entry<String, Set<String>> entry : userFavoritesMap.entrySet()) {
            String currentUserName = entry.getKey();
            if (!currentUserName.equals(userName)) {
                Set<String> currentFavorites = entry.getValue();
                exclusiveFavorites.addAll(currentFavorites);
            }
        }

        userFavorites.removeAll(exclusiveFavorites);
        return userFavorites;
    }

    static String formatFavorites(Set<String> favorites) {
        return favorites.stream()
                .sorted()
                .collect(Collectors.joining(", "));
    }

    static Map<String, Long> countFavorites(Collection<Set<String>> userFavoritesSets) {
        return userFavoritesSets.stream()
                .flatMap(Set::stream)
                .collect(Collectors.groupingBy(
                        favorite -> favorite,
                        Collectors.counting()
                ));
    }
}
