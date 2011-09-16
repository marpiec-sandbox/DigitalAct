package org.digitalact.domain.notes.dao;

import java.util.List;
import org.digitalact.domain.notes.entity.StickyNote;
import org.digitalact.domain.users.entity.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.synyx.hades.dao.GenericDao;

/**
 * Dao do obslugi notetek, klasy StickyNoteDao.
 * @author Marcin Pieciukiewicz
 */
@Repository
@Transactional
public interface StickyNoteDao extends GenericDao<StickyNote, Long> {

    /**
     * Znajduje wszystkie notatki podanej osoby, które nie zostały usunięte.
     * @param person Osoba dla której maja zostac wyszukane notatki
     * @return Lista notatek
     */
    List<StickyNote> findByOwnerPersonAndDeletionTimeNull(Person person);
}
