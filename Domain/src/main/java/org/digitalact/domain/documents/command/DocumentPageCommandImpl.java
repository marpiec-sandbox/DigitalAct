package org.digitalact.domain.documents.command;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.digitalact.constants.MyConstants;
import org.digitalact.domain.documents.builder.DocumentPageBuilder;
import org.digitalact.domain.documents.dao.DocumentPageDao;
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
 * Klasa odpowiedzialna za modyfikacje na stronach dokumentów.
 */
@Named
public class DocumentPageCommandImpl implements DocumentPageCommand {

    @Inject
    private DocumentPageDao documentPageDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentPageCommandImpl.class);

    @Override
    public void storeUploadedFile(String uploadedFileDirectory, UploadedFileParam param) {
        try {

            FileNameGenerator fileNameGenerator = new FileNameGenerator(uploadedFileDirectory);
            fileNameGenerator.generateNewFileName();

            File file = fileNameGenerator.getFile();
            String fileLocation = fileNameGenerator.getFileLocation();

            writeFileOnDisk(param, file);
            saveFileInfoInDatabase(param, fileLocation);

        } catch (IOException e) {
            LOGGER.error("Problem saving file", e);
            throw new IllegalStateException("Problem saving file", e);
        }
    }

    private void saveFileInfoInDatabase(UploadedFileParam param, String fileLocation) {
        String fileChecksum = DigestUtils.sha256Hex(param.getFile());

        DocumentPage documentPage = DocumentPageBuilder.build("A", 1, param.getFilename(), fileLocation,
                param.getContentType(), fileChecksum, DocumentType.ROZPORZADZENIE);
        documentPage = documentPageDao.save(documentPage);
    }

    private void writeFileOnDisk(UploadedFileParam param, File file) throws IOException {
        file.createNewFile();
        FileUtils.writeByteArrayToFile(file, param.getFile());
    }



}
