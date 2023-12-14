package com.pozhenskii.task3;

import java.util.Stack;

public class BracketValidator {
    public static int checkBrackets(String input) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (isBracket(currentChar)) {
                if (isOpenBracket(currentChar)) {
                    stack.push(currentChar);
                } else {
                    if (stack.isEmpty() || !isMatchingPair(stack.pop(), currentChar)) {
                        return i;
                    }
                }
            }
        }

        return stack.isEmpty() ? -1 : input.length();
    }

    private static boolean isBracket(char c) {
        return c == '(' || c == ')' || c == '{' || c == '}' || c == '[' || c == ']';
    }

    private static boolean isOpenBracket(char c) {
        return c == '(' || c == '{' || c == '[';
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }

}
