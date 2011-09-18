package org.digitalact.utils;

import java.util.Date;

/**
 * Klasa dostarczająca narzędzi do obsługi dat.
 * @author Marcin Pieciukiewicz
 */
public final class MyDateUtils {

    private static final int MILLS_IN_24H = 86400000;
    private static final int MILLS_IN_1H = 3600000;
    private static final int MILLS_IN_1M = 60000;

    private MyDateUtils() {
    }

    //TODO: zamienić na obsługe JodaTime

    /**
     * Dodaje określony czas do podanej daty.
     * @param date data początkowa
     * @param hours godziny do dodania
     * @param minutes minuty do dodania
     * @return data powiększona o podany czas
     */
    public static Date addTime(Date date, int hours, int minutes) {
        return new Date(date.getTime() + calculateMills(hours, minutes));
    }

    private static int calculateMills(int hours, int minutes) {
        return MILLS_IN_1H * hours + MILLS_IN_1M * minutes;
    }


}
