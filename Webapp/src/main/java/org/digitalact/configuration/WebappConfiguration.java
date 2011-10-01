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

    /** Tymczasowe miejsce zapisywania plików, póki nie będzie to zrealizowane w fileserverze.*/
    String getUploadedFileDirectory();
}
