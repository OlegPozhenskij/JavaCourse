package com.pozhenskii.task2;

import java.util.*;
import java.util.stream.Collectors;

import static com.pozhenskii.task2.FavoritesUtil.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Set<String>> userFavoritesMap = new HashMap<>();

        while (true) {
            System.out.println("Введите данные пользователя (или пустую строку для завершения ввода):");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                break;
            }

            String[] parts = input.split(":");
            if (parts.length == 2) {
                String userName = parts[0].trim();
                Set<String> userFavorites = Arrays.stream(parts[1].split(","))
                        .map(String::trim)
                        .collect(Collectors.toSet());

                userFavoritesMap.put(userName, userFavorites);
            } else {
                System.out.println("Некорректный ввод. Попробуйте еще раз.");
            }
        }

        Set<String> commonFavorites = findCommonFavorites(userFavoritesMap.values());
        System.out.println("Всеобщие любимки: " + formatFavorites(commonFavorites));

        Set<String> allFavorites = findAllFavorites(userFavoritesMap.values());
        System.out.println("Все любимки: " + formatFavorites(allFavorites));

        userFavoritesMap.forEach((userName, userFavorites) -> {
            Set<String> exclusiveFavorites = findExclusiveFavorites(userName, userFavoritesMap);
            System.out.println("Любимки пользователя " + userName + ", которые нравятся только ему: "
                    + formatFavorites(exclusiveFavorites));
        });

        Map<String, Long> favoritesCountMap = countFavorites(userFavoritesMap.values());
        System.out.println("Количество пользователей, которым нравится каждая любимка:");
        favoritesCountMap.forEach((favorite, count) ->
                System.out.println(favorite + ": " + count + " пользователей"));

    }

}
