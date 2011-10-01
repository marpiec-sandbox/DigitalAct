package org.digitalact.domain.documents.dao;

import org.digitalact.domain.documents.entity.DocumentPage;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.synyx.hades.dao.GenericDao;

import java.util.List;

/**
 * Dao do obslugi stron, klasy DocumentPage.
 * @author Marcin Pieciukiewicz
 */
@Repository
@Transactional
public interface DocumentPageDao extends GenericDao<DocumentPage, Long> {
    List<DocumentPage> findByAct(String act);
}
