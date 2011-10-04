package org.digitalact.configuration;

/**
 * Konfiguracja głównej aplikacji webowej.
 */
public interface WebappConfiguration {

    /**
     * Zwraca url na którym uruchomiona została aplikacja.
     * @return wpis w konfiguracji
     */
    String getApplicationUrl();

    /**
     * Zwraca liczbę serwerów plików.
     * @return wpis w konfiguracji
     */
    String getFileServersCount();

    /**
     * Zwraca adres podanego serwera plików.
     * @param serverId id serwera plików
     * @return wpis w konfiguracji
     */
    String getFileServerAddress(int serverId);

    /**
     * Tymczasowe miejsce zapisywania plików, póki nie będzie to zrealizowane w fileserverze.
     * @return wpis w konfiguracji
     */
    String getUploadedFileDirectory();

    /**
     * Namiar na bazę danych.
     * @return wpis w konfiguracji
     */
    String getDatabaseConnectionUrl();

    /**
     * Login do bazy danych.
     * @return wpis w konfiguracji
     */
    String getDatabaseUsername();

    /**
     * Hasło do bazy danych.
     * @return wpis w konfiguracji
     */
    String getDatabasePassword();

}
