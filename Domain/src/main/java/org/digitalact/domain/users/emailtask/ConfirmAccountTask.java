package org.digitalact.domain.users.emailtask;

import org.digitalact.emailtask.Task;


/**
 * Zadanie dokonujące aktywacji konta po potwierdzeniu emaila.
 * @author Marcin Pieciukiewicz
 */
public class ConfirmAccountTask extends Task {

    private static final String EXPIRED_LINK = "registration/confirmation-link-expired.html";
    private static final String SUCCESS_LINK = "registration/account-confirmed.html";

    private long personId;

    public ConfirmAccountTask(long personId) {
        super(EXPIRED_LINK, SUCCESS_LINK);
        this.personId = personId;
    }

    public long getPersonId() {
        return personId;
    }
    
}
