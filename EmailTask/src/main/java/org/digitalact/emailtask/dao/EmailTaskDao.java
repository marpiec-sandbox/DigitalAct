package org.digitalact.emailtask.dao;

import org.digitalact.emailtask.entity.EmailTask;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.synyx.hades.dao.GenericDao;

/**
 * Dao do zarządzania zadaniami potwierdzanymi przez email. Obiektami EmailTask.
 * @author Marcin Pieciukiewicz
 */
@Repository
@Transactional
public interface EmailTaskDao extends GenericDao<EmailTask, Long> {

    /**
     * Znajduje zadanie po podanym kodzie.
     * @param taskCode kod zadania
     * @return znalezione zadanie
     */
    EmailTask findByTaskCode(String taskCode);
}
