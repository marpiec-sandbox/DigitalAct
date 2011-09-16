package org.digitalact.notes;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.digitalact.domain.notes.command.StickyNoteCommand;
import org.digitalact.domain.notes.entity.StickyNote;
import org.digitalact.domain.notes.query.StickyNoteQuery;
import org.digitalact.domain.users.entity.Person;
import org.digitalact.engine.session.SessionParams;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Marcin Pieciukiewicz
 */
@Named
@Scope("view")
public class NotesBacking implements Serializable {
    
    //Pomyslec o jakims cache'u
    @Inject
    private StickyNoteQuery stickyNoteQuery;
    
    @Inject
    private StickyNoteCommand stickyNoteCommand;

    private List<StickyNote> notes;
    
    private StickyNote lastCreatedNote;
    
    @PostConstruct
    public void init() {
        loadNotesForCurrentUser();
    }

    private void loadNotesForCurrentUser() {
        Person person = SessionParams.getPerson();
        notes = stickyNoteQuery.findStickyNotesForPerson(person);
    }
    
    public void createNewNote() {
        //TODO dodac walidacje
        Person person = SessionParams.getPerson();
        long newStickyNoteId = stickyNoteCommand.createEmptyStickyNote(person.getPersonId());
        loadLastCreatedNote(newStickyNoteId);
    }
    
    private void loadLastCreatedNote(long newStickyNoteId) {
        lastCreatedNote = stickyNoteQuery.findById(newStickyNoteId);
    }

    public List<StickyNote> getNotes() {
        return notes;
    }

    public StickyNote getLastCreatedNote() {
        return lastCreatedNote;
    }
}
