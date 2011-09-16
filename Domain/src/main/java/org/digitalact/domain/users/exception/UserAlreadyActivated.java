package org.digitalact.domain.users.exception;

import java.util.Date;
import org.digitalact.exception.DomainException;

/**
 * Wyjątek oznaczający, że podane konto użytkownika zostało już wcześniej aktywowane.
 * @author Marcin Pieciukiewicz
 */
public class UserAlreadyActivated extends DomainException {
    
    private Date activationTime;
    
    public UserAlreadyActivated(Date activationTime) {
        this.activationTime = activationTime;
    }

    public Date getActivationTime() {
        return activationTime;
    }
}
