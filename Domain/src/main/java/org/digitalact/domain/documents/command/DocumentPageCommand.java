package org.digitalact.domain.documents.command;

import org.digitalact.domain.documents.entity.DocumentType;

public interface DocumentPageCommand {

    public long saveNewDocumentPage(String act, int pageNumber, String originalFileName,
                                    String fileLocation, DocumentType documentType, byte[] fileContent,
                                    String fileType);
}
