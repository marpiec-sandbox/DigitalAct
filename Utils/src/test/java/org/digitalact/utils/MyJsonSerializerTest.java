package org.digitalact.utils;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Klasa testująca narzędzia do serializacji i deserializacji JSON. Klasę MyJsonSerializer.
 * @author Marcin Pieciukiewicz
 */
public final class MyJsonSerializerTest {

    private static class SimpleDataObject {

        private int x;
        private int y;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

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
     * Testuje serializowanie obiektów.
     */
    @Test
    public void testSerialization() {
        //having

        SimpleDataObject simpleDataObject = new SimpleDataObject();
        simpleDataObject.setX(15);
        simpleDataObject.setY(43);

        //when
        String serialized = MyJsonSerializer.serialize(simpleDataObject);

        //then
        assertEquals(serialized, "{\"x\":15,\"y\":43}");
    }

    /**
     * Testuje deserializowanie obiektów.
     */
    @Test
    public void testDeserialization() {
        //having
        String serialized = "{x:100,y:13}";

        //when
        SimpleDataObject simpleDataObject = MyJsonSerializer.deserialize(serialized, SimpleDataObject.class);

        //then
        assertEquals(simpleDataObject.getX(), 100);
        assertEquals(simpleDataObject.getY(), 13);
    }


}
