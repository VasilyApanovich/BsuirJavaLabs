package com.bsuir.lab3;

import java.util.Stack;

/**
 * Created by v.apanovich on 25.02.2016.
 */
public class StackUtil {

    public static int reverseNumber(int number) {
        Stack<String> stack = new Stack<>();
        String numberString = String.valueOf(number);
        for (int i = 0; i < numberString.length(); i++) {
            if (numberString.charAt(i) == '-') {
                continue;
            }
            stack.push(String.valueOf(numberString.charAt(i)));
            if (i == numberString.length() - 1 && numberString.charAt(0) == '-') {
                stack.push("-");
            }
        }
        String reverseNumber = "";
        while (!stack.empty()) {
            reverseNumber += stack.pop();
        }
        return Integer.parseInt(reverseNumber);
    }
}
