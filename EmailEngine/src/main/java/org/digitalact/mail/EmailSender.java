package org.digitalact.mail;

/**
 * Klasa służąca do wysyłania e-maili.
 * @author Marcin Pieciukiewicz
 */
public interface EmailSender {

    /**
     * Wysyła email do użytkownika.
     * Standardowa implementacja robi to Asynchronicznie.
     * @param emailAddress adres email
     * @param mailData dane określające zawartość emaila
     */
    void sendEmail(String emailAddress, EmailData mailData);
}
