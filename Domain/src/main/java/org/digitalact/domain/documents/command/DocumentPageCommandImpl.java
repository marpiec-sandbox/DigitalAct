package org.digitalact.domain.documents.command;

import org.apache.commons.codec.digest.DigestUtils;
import org.digitalact.domain.documents.builder.DocumentPageBuilder;
import org.digitalact.domain.documents.dao.DocumentPageDao;
import org.digitalact.domain.documents.entity.DocumentPage;
import org.digitalact.domain.documents.entity.DocumentType;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by IntelliJ IDEA.
 * User: Marcin
 * Date: 21.09.11
 * Time: 20:48
 * To change this template use File | Settings | File Templates.
 */
@Named
public class DocumentPageCommandImpl implements DocumentPageCommand {

    @Inject
    private DocumentPageDao documentPageDao;

    @Override
    public long saveNewDocumentPage(String act, int pageNumber, String originalFileName,
                                    String fileLocation, DocumentType documentType, byte[] fileContent,
                                    String fileType) {

        //TODO uplad file
        String fileChecksum = DigestUtils.sha256Hex(fileContent);

        DocumentPage documentPage = DocumentPageBuilder.build(act, pageNumber, originalFileName, fileLocation, fileType, fileChecksum, documentType);
        documentPage = documentPageDao.save(documentPage);

        return documentPage.getDocumentPageId();
    }
}
