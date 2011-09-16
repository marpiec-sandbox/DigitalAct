package org.digitalact.emailtask.exception;

import org.digitalact.emailtask.Task;

/**
 * Wyjątek oznaczający, że termin ważności zadania wygasł.
 * @author Marcin Pieciukiewicz
 */
public class TaskExpiredException extends Exception {

    private String taskExpiredLink;

    public TaskExpiredException(String taskExpiredLink) {
        this.taskExpiredLink = taskExpiredLink;
    }

    public String getTaskExpiredLink() {
        return taskExpiredLink;
    }
    
}
