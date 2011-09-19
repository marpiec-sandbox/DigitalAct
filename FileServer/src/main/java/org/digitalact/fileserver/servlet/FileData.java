package org.digitalact.fileserver.servlet;

import java.io.Serializable;

/**
 * Obiekt przechowyjący dane z pliku oraz jego sumę kontrolną.
 */
public class FileData implements Serializable {

    private byte[] fileData;
    private String checksum;

    public FileData(byte[] fileData, String checksum) {
        this.fileData = fileData;
        this.checksum = checksum;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public String getChecksum() {
        return checksum;
    }
}
