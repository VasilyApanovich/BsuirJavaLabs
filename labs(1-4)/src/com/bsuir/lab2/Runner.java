package com.bsuir.lab2;

/**
 * Created by v.apanovich on 25.02.2016.
 */
public class Runner {

    public static void main(String[] args) {
        String test1 = "e02fd0e4-00fd-090A-ca30-0d00a0038ba0"; //valid
        String test2 = "{e02fd0e4-00fd-090A-ca30-0d00a0038ba0}"; //valid
        String test3 = "e02fd0e400fd090Aca300d00a0038ba0"; //invalid
        String test4 = "e02fd0e4-00fV-090A-ca30-0d00a0038ba0"; //invalid (letter 'V')
        String test5 = "e02fd0e4-00f01-090A-ca30-0d00a0038ba0"; //invalid (length 5, not 4)

        System.out.println("Check if string is a valid GUID.\n" +
                "A GUID is most commonly written in text as a sequence " +
                "of hexadecimal digits separated into five groups (8,4,4,4,12 symbols),\n" +
                "such as: 3F2504E0-4F89-41D3-9A0C-0305E82C3301\n");

        System.out.println("Some tests:");
        System.out.println(test1 + "\t\t\t\t" + RegexUtil.isValidGuid(test1));
        System.out.println(test2 + "\t\t\t\t" + RegexUtil.isValidGuid(test2));
        System.out.println(test3 + "\t\t\t\t\t" + RegexUtil.isValidGuid(test3));
        System.out.println(test4 + "\t\t\t\t" + RegexUtil.isValidGuid(test4));
        System.out.println(test5 + "\t\t\t\t" + RegexUtil.isValidGuid(test5));
    }
}
