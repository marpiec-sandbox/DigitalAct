package org.digitalact.emailtask;

/**
 * Klasa umożliwiająca stworzenie zadania potwierdzanego emailem.
 *
 * @author Marcin Pieciukiewicz
 */
public interface MailTaskCreator {

    /**
     * Tworzy zadanie potwierdzanie emailem.
     * @param email na jaki adres ma zostać wysłana wiadomość
     * @param personId id użytkownika, dla którego będzie wykonane zadanie
     * @param task obiekt zadania
     * @param emailData dane do wysłania emaila
     */
    void createTask(String email, Long personId, Task task, TaskEmailData emailData);

}
