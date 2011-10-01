package org.digitalact.upload;

/**
 * Klasa odpowiedzialna za zapisanie wgranego pliku na
 * dysku i dokonanie odpowiedniego pliku w bazie danych.
 * @author Marcin Pieciukiewicz
 */
public interface UploadedFileCommand {

    /**
     * Zapisuje podany plik na dysku i dokonuje wpisu w bazie danych.
     * @param uploadedFileParam dane pliku
     */
     void storeUploadedFile(UploadedFileParam uploadedFileParam);

}
