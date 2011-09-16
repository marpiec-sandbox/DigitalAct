package org.digitalact.domain.users.dao;

import org.digitalact.domain.users.entity.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.synyx.hades.dao.GenericDao;

/**
 * Dao obsługujące konta użytkowników.
 * @author Marcin Pieciukiewicz
 */
@Repository
@Transactional
public interface PersonDao extends GenericDao<Person, Long> {

    /**
     * Znajduje użytkownika po podanym adresie email.
     * @param email email użytkownika
     * @return użytkownika
     */
    Person findByEmail(String email);
}
