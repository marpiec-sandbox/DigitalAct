package org.digitalact.fileserver.servlet;

/**
 * Klasa odpowiedzialna za wczytywanie plików do pamięci.
 */
public interface FileLoader {

    FileData loadFile(String absoultePath);

}
