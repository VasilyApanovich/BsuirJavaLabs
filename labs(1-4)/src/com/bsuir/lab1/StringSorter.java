package com.bsuir.lab1;

import java.util.List;

/**
 * Created by v.apanovich on 25.02.2016.
 */
public class StringSorter {

    public static void sortByLength(List<String> list) {
        list.sort((String s1, String s2)-> (s1.length() - s2.length()));
    }
}
