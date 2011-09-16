package org.digitalact.mail;

/**
 * Enum zawierający listę możliwych szablonów wiadomości email.
 * @author Marcin Pieciukiewicz
 */
public enum EmailTemplate {
    
    CONFIRM_ACCOUNT("account-creation-confirmation.vsl");
    
    private static final String TEMPLATES_DIR = "templates/email/";
    
    private String templateFile;

    private EmailTemplate(String templateFile) {
        this.templateFile = TEMPLATES_DIR+templateFile;
    }

    public String getTemplateFile() {
        return templateFile;
    }

}
