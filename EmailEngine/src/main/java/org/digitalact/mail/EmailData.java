package org.digitalact.mail;

/**
 * Klasa abstarkcyjna do przechowywania informacji
 * o szablonie emaila i danych do wpisania w szablon.
 * @author Marcin Pieciukiewicz
 */
public abstract class EmailData {

    private EmailTemplate template;

    public EmailData(EmailTemplate template) {
        this.template = template;
    }

    public EmailTemplate getTemplate() {
        return template;
    }
    
}
