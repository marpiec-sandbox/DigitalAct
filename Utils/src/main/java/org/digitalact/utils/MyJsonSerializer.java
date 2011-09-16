/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.digitalact.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Klasa ułatwiająca obsługę JSON oraz separująca wykorzystaną bibliotekę.
 * @author Marcin Pieciukiewicz
 */
public class MyJsonSerializer {

    //TODO postarac sie nie tworzyc obiektu gson za kazdym razem

    /**
     * Serializuje podany obiekt do JSONa.
     * @param object obiekt do zserializowania
     * @return JSON z obiektem
     */
    public static String serialize(Object object) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(object);
    }

    /**
     * Deserializuje podanego JSONa do obiektu.
     * @param json JSON z obiektem
     * @param objectClass kalsa docelowego obiektu
     * @param <T> typ docelowy
     * @return zdeserializowany obiekt
     */
    public static <T> T deserialize(String json, Class<?> objectClass) {
        Gson gson = new Gson();
        return (T) gson.fromJson(json, objectClass);
    }
}
