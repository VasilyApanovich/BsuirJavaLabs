package com.bsuir.lab3;

import java.util.Scanner;

/**
 * Created by v.apanovich on 25.02.2016.
 */
public class Runner {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int number;
        String consoleInput;

        System.out.println("Please, enter any number:");
        consoleInput = scanner.nextLine();
        if (isValidInt(consoleInput)) {
            number = Integer.parseInt(consoleInput);
        } else {
            System.out.println("Invalid input");
            return;
        }

        int reverseNumber = StackUtil.reverseNumber(number);
        System.out.println("Reversed number: " + reverseNumber);
    }

    private static boolean isValidInt(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return (true);
    }
}
