package org.digitalact.domain.notes.command;

/**
 * Klasa zapewniajaca tworzenie i modyfikowanie notatek.
 * @author Marcin Pieciukiewicz
 */
public interface StickyNoteCommand {

    /**
     * Tworzy nową notatkę dla podanej osoby.
     * @param personId id osoby
     * @return id stworzonej notatki
     */
    long createEmptyStickyNote(Long personId);

    /**
     * Zapisuje zmiany w notatce.
     * @param noteId id notatki do zmiany
     * @param saveNoteData nowe dane notatki
     */
    void saveNote(Long noteId, SaveNoteData saveNoteData);
}
