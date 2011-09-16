package org.digitalact.domain.users.query;

import org.digitalact.domain.users.entity.Person;

/**
 * Klasa umożliwiająca wyszukanie i pobranie użytkowników.
 * @author Marcin Pieciukiewicz
 */

public interface UserQuery {

    /**
     * Zwraca liczbę zarejestrowanych użytkowników.
     * @return liczba użytkoweników
     */
    long countRegisteredUsers();

    /**
     * Znajduje użytkownika po podanym adresie email.
     * @param email adres email uzytkownika
     * @return użytkownik
     */
    Person findByEmail(String email);
    

}
