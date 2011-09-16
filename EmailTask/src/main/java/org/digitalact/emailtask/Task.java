package org.digitalact.emailtask;

/**
 * Klasa oznaczająca zadanie do wykonania i zawierająca dane niezbędne do jego wykonania.
 * @author Marcin Pieciukiewicz
 */
public abstract class Task {
    
    private String exiredLink;
    private String successLink;

    public Task(String exiredLink, String successLink) {
        this.exiredLink = exiredLink;
        this.successLink = successLink;
    }

    public String getExiredLink() {
        return exiredLink;
    }

    public String getSuccessLink() {
        return successLink;
    }
    
}
