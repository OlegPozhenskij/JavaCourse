package com.pozhenskii.task1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> userInputMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        int position = 1;

        while (true) {
            System.out.print("Введите текст (или 'exit' для завершения): ");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                break;
            }

            try {
                checkForDuplicates(input, userInputMap);
                userInputMap.put(input, position);
                position++;
            } catch (AlreadyExistsException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
    private static void checkForDuplicates(String input, Map<String, Integer> userInputMap)
            throws AlreadyExistsException {
        if (userInputMap.containsKey(input)) {
            throw new AlreadyExistsException(input, userInputMap.get(input));
        }
    }
}
