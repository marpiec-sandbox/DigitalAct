package org.digitalact.domain.notes.query;

import java.util.List;
import org.digitalact.domain.notes.entity.StickyNote;
import org.digitalact.domain.users.entity.Person;

/**
 * Klasa umożliwiająca wyszukiwanie i pobieranie notatek.
 * @author Marcin Pieciukiewicz
 */
public interface StickyNoteQuery {

    /**
     * Znajduje wszystkie notatki podanej osoby, które nie zostały usunięte.
     * @param person Osoba dla której maja zostac wyszukane notatki
     * @return Lista notatek
     */
    List<StickyNote> findStickyNotesForPerson(Person person);

    /**
     * Znajduje notatkę.
     * @param stickyNoteId Id notatki do znalezienia
     * @return znaleznina notatka
     */
    StickyNote findById(long stickyNoteId);
}
