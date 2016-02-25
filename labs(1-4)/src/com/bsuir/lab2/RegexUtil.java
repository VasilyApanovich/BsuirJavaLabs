package com.bsuir.lab2;

/**
 * Created by v.apanovich on 25.02.2016.
 */
public class RegexUtil {

    private static final String GUID_REGEX =
            "(?i)^[\\{]?[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}[\\}]?$";

    public static boolean isValidGuid(String guid) {
        return guid.matches(GUID_REGEX);
    }
}
