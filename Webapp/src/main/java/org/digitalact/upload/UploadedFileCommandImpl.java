package org.digitalact.upload;

import com.sun.xml.internal.txw2.Document;
import org.apache.commons.io.FileUtils;
import org.digitalact.configuration.WebappConfiguration;
import org.digitalact.constants.MyConstants;
import org.digitalact.domain.documents.command.DocumentPageCommand;
import org.digitalact.domain.documents.entity.DocumentPage;
import org.digitalact.domain.documents.entity.DocumentType;
import org.digitalact.utils.MyRandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;

/**
 * Klasa odpowiedzialna za zapisanie wgranego pliku na
 * dysku i dokonanie odpowiedniego pliku w bazie danych.
 * @author Marcin Pieciukiewicz
 */
@Named
public class UploadedFileCommandImpl implements UploadedFileCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadedFileCommandImpl.class);

    @Inject
    private WebappConfiguration webappConfiguration;

    @Inject
    private DocumentPageCommand documentPageCommand;

    @Override
    public void storeUploadedFile(UploadedFileParam uploadedFileParam) {
        try {
            String uploadedFileDirectory = webappConfiguration.getUploadedFileDirectory();
            File file;
            String fileLocation;

            do {
                fileLocation = createFileName();
                file = new File(uploadedFileDirectory +"/"+fileLocation);
            } while (file.exists());

            file.createNewFile();

            FileUtils.writeByteArrayToFile(file, uploadedFileParam.getFile());

            documentPageCommand.saveNewDocumentPage("a", 1, uploadedFileParam.getFilename(), fileLocation, DocumentType.ROZPORZADZENIE, uploadedFileParam.getFile(), uploadedFileParam.getContentType());

        } catch (IOException e) {
            LOGGER.error("Problem saving file", e);
            throw new IllegalStateException("Problem saving file", e);
        }
    }

    private String createFileName() {
        return MyRandomStringUtils.generateAlphanumericString(MyConstants.FILE_NAME_LENGTH);
    }
}
