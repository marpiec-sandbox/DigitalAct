package org.digitalact.utils;

import java.util.Date;

/**
 * Klasa dostarczająca narzędzi do obsługi dat.
 * @author Marcin Pieciukiewicz
 */
public final class MyDateUtils {

    private static final int MILLS_IN_24H = 86400000;

    private MyDateUtils() {
    }

    //TODO: zamienić na obsługe JodaTime

    /**
     * Dodaje 24 godziny do podanej daty.
     * @param date data początkowa
     * @return data powiększona o 24h
     */
    public static Date add24h(Date date) {
        return new Date(date.getTime()+MILLS_IN_24H);
    }
}
