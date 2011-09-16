package org.digitalact.domain.users.emailtask;

import org.digitalact.mail.EmailTemplate;
import org.digitalact.emailtask.TaskEmailData;

/**
 * Dene potrzebne do wysłania emaila potwierdzajacego założenie konta.
 * @author Marcin Pieciukiewicz
 */
public class ConfirmAccountEmailData extends TaskEmailData {

    private String username;

    public ConfirmAccountEmailData() {
        super(EmailTemplate.CONFIRM_ACCOUNT);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
