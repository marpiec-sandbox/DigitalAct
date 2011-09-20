package org.digitalact.fileserver.servlet;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Konfiguracja wykorzystywana przez fileserver.
 * Plik z konfiguracją powinien znajdować się tu:
 * %tomcat%/lib/digitalact/fileserver.properties
 */
public class FileServerConfiguration {

    private static final String CONFIG_FILE = "digitalact/fileserver.properties";
    private Configuration configuration;

    public FileServerConfiguration() {
        try {
            configuration = new PropertiesConfiguration(CONFIG_FILE);
        } catch (ConfigurationException e) {
            throw new IllegalStateException("Exception during reading configuration", e);
        }
    }

    public String getFilesDirectory() {
        return configuration.getString("files_directory");
    }
}
