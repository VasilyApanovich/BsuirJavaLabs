package com.bsuir.lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by v.apanovich on 25.02.2016.
 */
public class Runner {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        String consoleInput;
        int lineNumber = 5;

        System.out.println("Please, enter number of lines:");
        consoleInput = scanner.nextLine();
        if (isValidInt(consoleInput)) {
            lineNumber = Integer.parseInt(consoleInput);
        } else {
            System.out.println("Invalid input. Default value (5) will be used.");
        }

        System.out.println("Please, enter " + lineNumber + " lines:");
        for (int i = 0; i < lineNumber; i++) {
            list.add(scanner.nextLine());
        }

        StringSorter.sortByLength(list);

        System.out.println("Lines sorted by length:");
        for (String item : list) {
            System.out.println(item + "\t\t\t(length = " + item.length() + ")");
        }
    }

    private static boolean isValidInt(String s) {
        int res;
        try {
            res = Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return (res > 0);
    }
}
