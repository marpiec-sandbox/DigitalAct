package org.digitalact.domain.documents.query;

import org.digitalact.domain.documents.entity.DocumentPage;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Marcin
 * Date: 21.09.11
 * Time: 20:47
 * To change this template use File | Settings | File Templates.
 */
public interface DocumentPageQuery {

    List<DocumentPage> findPagesForAct(String act);

}
