package org.digitalact.domain.notes.command;

import java.util.Date;
import javax.inject.Inject;
import javax.inject.Named;
import org.digitalact.domain.notes.dao.StickyNoteDao;
import org.digitalact.domain.notes.entity.StickyNote;
import org.digitalact.domain.notes.entity.StickyNoteStatus;
import org.digitalact.domain.notes.entity.StickyNoteVisualProperties;
import org.digitalact.domain.users.dao.PersonDao;
import org.digitalact.domain.users.entity.Person;

/**
 * Klasa zapewniajaca tworzenie i modyfikowanie notatek.
 * @author Marcin Pieciukiewicz
 */
@Named
public class StickyNoteCommandImpl implements StickyNoteCommand {

    public static final int SOME_RANDOM_VALUE = 200;

    @Inject
    private PersonDao personDao;
    
    @Inject
    private StickyNoteDao stickyNoteDao;
            
    @Override
    public long createEmptyStickyNote(Long personId) {
        
        Person person = personDao.readByPrimaryKey(personId);
        
        if(person == null) { //TODO zmienic na jakis Assert
            throw new IllegalArgumentException("Invalid personId");
        }
        StickyNote stickyNote = constructNewNote(person);
        stickyNote = stickyNoteDao.save(stickyNote);
        return stickyNote.getStickyNoteId();
    }

    private StickyNote constructNewNote(Person person) {
        StickyNote stickyNote = new StickyNote();
        stickyNote.setOwnerPerson(person);
        stickyNote.setCreationTime(new Date());
        stickyNote.setStatus(StickyNoteStatus.NEW);

        injectDefaultVisualProperties(stickyNote);

        return stickyNote;
    }

    private void injectDefaultVisualProperties(StickyNote stickyNote) {
        StickyNoteVisualProperties visualProperties = new StickyNoteVisualProperties();
        visualProperties.setColor("red");
        visualProperties.setHeight((int) (Math.random() * SOME_RANDOM_VALUE + SOME_RANDOM_VALUE));
        visualProperties.setWidth((int) (Math.random() * SOME_RANDOM_VALUE + SOME_RANDOM_VALUE));
        visualProperties.setTop((int) (Math.random() * SOME_RANDOM_VALUE + SOME_RANDOM_VALUE));
        visualProperties.setLeft((int) (Math.random() * SOME_RANDOM_VALUE + SOME_RANDOM_VALUE));
        visualProperties.setStickyNote(stickyNote);
        stickyNote.setVisualProperties(visualProperties);
    }

    @Override
    public void saveNote(Long noteId, SaveNoteData data) {
        StickyNote stickyNote = stickyNoteDao.readByPrimaryKey(noteId);

        if(stickyNote==null) {//TODO zmienic na jakis Assert
            throw new IllegalArgumentException("Illegal noteId");
        }

        updateNoteData(stickyNote, data);

        stickyNoteDao.save(stickyNote);

    }

    private void updateNoteData(StickyNote stickyNote, SaveNoteData data) {

        StickyNoteVisualProperties visualProperties = stickyNote.getVisualProperties();

        visualProperties.setHeight(data.getHeight());
        visualProperties.setWidth(data.getWidth());
        visualProperties.setTop(data.getTop());
        visualProperties.setLeft(data.getLeft());
        visualProperties.setColor(data.getColor());

        stickyNote.setText(data.getText());

    }

}
