package org.digitalact.emailtask.exception;

/**
 * Wyjątek oznaczający, że termin ważności zadania wygasł.
 * @author Marcin Pieciukiewicz
 */
public class TaskExpiredException extends Exception {

    private String taskExpiredLink;

    /**
     * Konstruktor wyjątku.
     * @param taskExpiredLink link do strony oznaczającej przeterminowanie zadania
     */
    public TaskExpiredException(String taskExpiredLink) {
        this.taskExpiredLink = taskExpiredLink;
    }

    public String getTaskExpiredLink() {
        return taskExpiredLink;
    }
    
}
