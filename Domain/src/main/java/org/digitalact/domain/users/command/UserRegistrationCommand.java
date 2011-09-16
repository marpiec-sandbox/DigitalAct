package org.digitalact.domain.users.command;

import org.digitalact.domain.users.exception.UserAlreadyActivated;
import org.digitalact.domain.users.exception.UserAlreadyRegisteredException;

/**
 * Klasa umożliwiająca rejestrowanie użykowników.
 * @author Marcin Pieciukiewicz
 */
public interface UserRegistrationCommand {

    /**
     * Wykonuje rejestrację użytkownika o maksymalnych uprawnieniach.
     * @param name nazwa użytkownika
     * @param email email użytkownika
     * @param password hasło dostępu
     * @throws UserAlreadyRegisteredException w przypadku,
     * gdy jakikolwiek użytkown ik został już zarejestrowany
     */
    void registerSuperUser(String name, String email, String password) throws UserAlreadyRegisteredException;

    /**
     * Wykonuje rejestrację użytkownika o standardowych uprawnieniach.
     * @param name nazwa użytkownika
     * @param email email użytkownika
     * @param password hasło dostępu
     * @throws UserAlreadyRegisteredException w przypadku,
     * gdy podany użytkownik został już zarejestrowany
     */
    void registerUser(String name, String email, String password) throws UserAlreadyRegisteredException;

    /**
     * Dokonuje aktywacji konta użytkownika.
     * @param personId id osoby
     * @throws UserAlreadyActivated w przypadku, gdy konto użytkownika jest już zarejestrowane.
     */
    public void activateAccount(long personId) throws UserAlreadyActivated;
}
