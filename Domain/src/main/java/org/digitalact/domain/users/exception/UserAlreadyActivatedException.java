package org.digitalact.domain.users.exception;

import java.util.Date;
import org.digitalact.exception.DomainException;

/**
 * Wyjątek oznaczający, że podane konto użytkownika zostało już wcześniej aktywowane.
 * @author Marcin Pieciukiewicz
 */
public class UserAlreadyActivatedException extends DomainException {
    
    private Date activationTime;

    /**
     * Konstruktor wyjątku.
     * @param activationTime czas poprzedniej aktywacji konta.
     */
    public UserAlreadyActivatedException(Date activationTime) {
        this.activationTime = activationTime;
    }

    public Date getActivationTime() {
        return activationTime;
    }
}
