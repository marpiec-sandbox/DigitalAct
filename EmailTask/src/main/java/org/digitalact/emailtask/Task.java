package org.digitalact.emailtask;

/**
 * Klasa oznaczająca zadanie do wykonania i zawierająca dane niezbędne do jego wykonania.
 * @author Marcin Pieciukiewicz
 */
public abstract class Task {
    
    private String expiredLink;
    private String successLink;

    /**
     * Konstruktor.
     * @param expiredLink link do strony oznaczającej wygaśnięcie zadania
     * @param successLink link do strony potwierdzającej wykonanie zadania
     */
    public Task(String expiredLink, String successLink) {
        this.expiredLink = expiredLink;
        this.successLink = successLink;
    }

    public String getExpiredLink() {
        return expiredLink;
    }

    public String getSuccessLink() {
        return successLink;
    }
    
}
