package org.digitalact.fileserver.servlet;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Klasa odpowiedzialna za wczytywanie plików do pamięci.
 */
public class FileLoaderImpl implements FileLoader {

    @Override
    public FileData loadFile(String absoultePath) {

        try {
            File file = new File(absoultePath);
            if(file.exists()) {
                byte[] fileContent = FileUtils.readFileToByteArray(file);
                String checksum = DigestUtils.sha256Hex(fileContent);

                FileData fileData = new FileData(fileContent, checksum);

                return fileData;
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new IllegalStateException("Error reading file ["+absoultePath+"]", e);
        }
    }
}
