package org.digitalact.utils;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Klasa służąca do obsługi losowych stringów.
 * @author Marcin Pieciukiewicz
 */
public final class MyRandomStringUtils {
    
    public static final String ALL_ALPHANUMERIC = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

    private MyRandomStringUtils() {
    }

    /**
     * Generuje losowy String alfanumeryczny o podanej długości.
     * @param length długość stringa do wygernerowania
     * @return wygenerowany String
     */
    public static String generateAlphanumericString(int length) {
        return RandomStringUtils.random(length, ALL_ALPHANUMERIC);
    }
}
