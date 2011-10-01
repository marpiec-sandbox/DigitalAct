package org.digitalact.domain.documents.builder;

import org.digitalact.domain.documents.entity.DocumentPage;
import org.digitalact.domain.documents.entity.DocumentType;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Marcin
 * Date: 21.09.11
 * Time: 20:49
 * To change this template use File | Settings | File Templates.
 */
public final class DocumentPageBuilder {

    private DocumentPageBuilder() {
    }

    public static DocumentPage build(String act, int pageNumber, String originalFilename, String fileLocation,
                                     String fileType,
                                     String fileChecksum, DocumentType documentType) {
        DocumentPage documentPage = new DocumentPage();

        documentPage.setAct(act);
        documentPage.setCreationTime(new Date());
        documentPage.setPageNumber(pageNumber);
        documentPage.setFileChecksum(fileChecksum);
        documentPage.setOriginalFileName(originalFilename);
        documentPage.setFileLocation(fileLocation);
        documentPage.setFileType(fileType);
        documentPage.setFileServer("default");
        documentPage.setFolder("folder1");
        documentPage.setPageName("Strona numer "+pageNumber);
        documentPage.setDocumentType(documentType);
        documentPage.setVersion(0);

        return documentPage;
    }

}
