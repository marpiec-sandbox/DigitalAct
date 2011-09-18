package org.digitalact.utils;

import org.apache.commons.lang.StringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Klasa testująca narzędzia do obsługi losowych ciągów znaków. Klasę MyRandomStringUtils.
 * @author Marcin Pieciukiewicz
 */
public final class MyRandomStringUtilsTest {

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
     * Testuje generowanie losowych Stringów.
     */
    @Test
    public void testRandomStringGeneration() {
        //having

        int firstStringLength = 50;
        int secondStringLength = 30;

        //when
        String firstString = MyRandomStringUtils.generateAlphanumericString(firstStringLength);
        String secondString = MyRandomStringUtils.generateAlphanumericString(secondStringLength);

        //then
        assertEquals(firstString.length(), firstStringLength);
        assertEquals(secondString.length(), secondStringLength);

        assertTrue(StringUtils.containsOnly(firstString, MyRandomStringUtils.ALL_ALPHANUMERIC));
        assertTrue(StringUtils.containsOnly(secondString, MyRandomStringUtils.ALL_ALPHANUMERIC));
    }

}
