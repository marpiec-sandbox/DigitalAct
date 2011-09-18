package org.digitalact.utils;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.*;

/**
 * Klasa testująca narzędzia do obsługi dat. Klasę MyDateUtils.
 * @author Marcin Pieciukiewicz
 */
public final class MyDateUtilsTest {

    /**
     * Inicjalizacja przed testem.
     */
    @BeforeMethod
    public void setUp() {
    }

    /**
     * Sprzątanie po teście.
     */
    @AfterMethod
    public void tearDown() {
    }

    /**
     * Testuje dodawanie czasu do daty.
     * @throws ParseException w przypadku błędu w parsowaniu daty
     */
    @Test
    public void testDateAdding() throws ParseException {
        //having

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date mainDate = dateFormat.parse("2010-05-05 13:24:15");

        //when
        Date firstDate = MyDateUtils.addTime(mainDate, 4, 15);
        Date secondDate = MyDateUtils.addTime(mainDate, 24, 0);

        //then
        assertEquals(firstDate, dateFormat.parse("2010-05-05 17:39:15"));
        assertEquals(secondDate, dateFormat.parse("2010-05-06 13:24:15"));
    }

}
