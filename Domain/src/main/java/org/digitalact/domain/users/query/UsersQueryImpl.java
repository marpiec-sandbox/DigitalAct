package org.digitalact.domain.users.query;

import org.digitalact.domain.users.dao.PersonDao;

import javax.inject.Inject;
import javax.inject.Named;
import org.digitalact.domain.users.entity.Person;

/**
 * Klasa umożliwiająca wyszukanie i pobranie użytkowników.
 * @author Marcin Pieciukiewicz
 */
@Named
public class UsersQueryImpl implements UserQuery {

    @Inject
    private PersonDao personDao;

    @Override
    public long countRegisteredUsers() {
        return personDao.count();
    }

    @Override
    public Person findByEmail(String email) {
        return personDao.findByEmail(email);
    }
}
