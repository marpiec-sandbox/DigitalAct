package org.digitalact.domain.notes.query;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.digitalact.domain.notes.dao.StickyNoteDao;
import org.digitalact.domain.notes.entity.StickyNote;
import org.digitalact.domain.users.entity.Person;

/**
 * Klasa umożliwiająca wyszukiwanie i pobieranie notatek.
 * @author Marcin Pieciukiewicz
 */
@Named
public class StickyNoteQueryImpl implements StickyNoteQuery {
    
    @Inject
    private StickyNoteDao stickyNoteDao;
    
    @Override
    public List<StickyNote> findStickyNotesForPerson(Person person) {
        return stickyNoteDao.findByOwnerPersonAndDeletionTimeNull(person);
    }

    @Override
    public StickyNote findById(long stickyNoteId) {
        return stickyNoteDao.readByPrimaryKey(stickyNoteId);
    }
    
}
