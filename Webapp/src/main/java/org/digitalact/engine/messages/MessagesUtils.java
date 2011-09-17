package org.digitalact.engine.messages;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.digitalact.exception.DomainException;
import org.digitalact.constants.MyConstants;

/**
 * Narzędzia ułatwiające pracę z komunikatami JSF.
 * @author Marcin Pieciukiewicz
 */
@Named
public final class MessagesUtils {

    private MessagesResources messages = new MessagesResources(MyConstants.MESSAGES_VAR_NAME);

    /**
     * Wykunuje publikację komuniatu dla podanego wyjątku, w komunikatach JSF.
     * @param exception wyjątek który jest źródłem komunikatu
     */
    public void publish(DomainException exception) {
        String message = messages.getMessage(MyConstants.DOMAIN_EXCEPTION_PREFIX + exception.getClass().getSimpleName());
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(message));
    }
}
