package org.digitalact.domain.documents.query;

import org.digitalact.domain.documents.dao.DocumentPageDao;
import org.digitalact.domain.documents.entity.DocumentPage;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Marcin
 * Date: 21.09.11
 * Time: 20:47
 * To change this template use File | Settings | File Templates.
 */
@Named
public class DocumentPageQueryImpl implements DocumentPageQuery {

    @Inject
    private DocumentPageDao documentPageDao;

    @Override
    public List<DocumentPage> findPagesForAct(String act) {
        return documentPageDao.findByAct(act);
    }
}
