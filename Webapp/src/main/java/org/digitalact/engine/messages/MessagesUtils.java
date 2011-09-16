package org.digitalact.engine.messages;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.digitalact.exception.DomainException;
import org.digitalact.constants.MyConstants;

/**
 *
 * @author Marcin Pieciukiewicz
 */
@Named
public final class MessagesUtils {

    private MessagesResources messages = new MessagesResources(MyConstants.MESSAGES_VAR_NAME);

    public void publish(DomainException exception) {
        String message = messages.getMessage(MyConstants.DOMAIN_EXCEPTION_PREFIX + exception.getClass().getSimpleName());
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(message));
    }
}
