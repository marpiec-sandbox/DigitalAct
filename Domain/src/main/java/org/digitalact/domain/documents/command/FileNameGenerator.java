package org.digitalact.domain.documents.command;

import org.digitalact.constants.MyConstants;
import org.digitalact.utils.MyRandomStringUtils;

import java.io.File;

/**
 * Klasa pomocnicza służąca generowaniu nowej, losowej nazwy pliku.
 */
public class FileNameGenerator {

    private String uploadedFileDirectory;
    private File file = null;
    private String fileLocation = null;

    /**
     * Konstrujtor.
     * @param uploadedFileDirectory katalog, w którym ma zostać utworzony plik.
     */
    public FileNameGenerator(String uploadedFileDirectory) {
        this.uploadedFileDirectory = uploadedFileDirectory;
    }

    /**
     * Generuje nową nazwę pliku.
     */
    public void generateNewFileName() {
        do {
            fileLocation = createFileName();
            file = new File(uploadedFileDirectory + "/" + fileLocation);
        } while (file.exists());
    }

    private String createFileName() {
        return MyRandomStringUtils.generateLowercaseAlphanumericString(MyConstants.FILE_NAME_LENGTH);
    }

    public File getFile() {
        return file;
    }

    public String getFileLocation() {
        return fileLocation;
    }
}
