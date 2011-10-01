package org.digitalact.domain.documents.command;

import org.digitalact.domain.documents.entity.DocumentType;

/**
 * Klasa odpowiedzialna za modyfikacje na stronach dokumentów.
 */
public interface DocumentPageCommand {

    /**
     * Zapisuje podany plik na dysku i dokonuje wpisu w bazie danych.
     * @param param dane wgranego pliku
     */
     public void storeUploadedFile(String uploadedFileDirectory, UploadedFileParam param);
}
