package org.digitalact.configuration;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import javax.inject.Named;

/**
 * Konfiguracja głównej aplikacji webowej.
 */
@Named
public class WebappConfigurationImpl implements WebappConfiguration {

    private static final String CONFIG_FILE = "digitalact/digitalact.properties";
    private Configuration configuration;

    public WebappConfigurationImpl() {
        try {
            configuration = new PropertiesConfiguration(CONFIG_FILE);
        } catch (ConfigurationException e) {
            throw new IllegalStateException("Exception during reading configuration", e);
        }
    }

    @Override
    public String getApplicationUrl() {
        return configuration.getString("application_url");
    }

    @Override
    public String getFileServersCount() {
        return configuration.getString("file_servers_count");
    }

    @Override
    public String getFileServerAddress(int serverId) {
        return configuration.getString("file_server_address_"+serverId);
    }

    @Override
    public String getUploadedFileDirectory() {
        return configuration.getString("uploaded_file_directory");
    }

    @Override
    public String getDatabaseConnectionUrl() {
        return configuration.getString("database_connection_url");
    }

    @Override
    public String getDatabaseUsername() {
        return configuration.getString("database_username");
    }

    @Override
    public String getDatabasePassword() {
        return configuration.getString("database_password");
    }

}
