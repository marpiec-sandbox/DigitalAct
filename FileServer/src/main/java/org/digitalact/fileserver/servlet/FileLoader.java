package org.digitalact.fileserver.servlet;

/**
 * Klasa odpowiedzialna za wczytywanie plików do pamięci.
 */
public interface FileLoader {

    /**
     * Metoda wczytuje plik z podanej ścieżki.
     * @param absoultePath ścieżka do pliku
     * @return obiekt FileData zawierający plik
     */
    FileData loadFile(String absoultePath);

}
