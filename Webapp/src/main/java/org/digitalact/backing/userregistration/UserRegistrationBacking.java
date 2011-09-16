package org.digitalact.backing.userregistration;

import org.digitalact.engine.validation.BeanValidator;
import org.digitalact.domain.users.exception.UserAlreadyRegisteredException;
import org.digitalact.domain.users.command.UserRegistrationCommand;

import javax.inject.Inject;
import javax.inject.Named;
import org.digitalact.engine.messages.MessagesUtils;
import org.digitalact.engine.params.Pages;
import org.springframework.context.annotation.Scope;

/**
 * @author Marcin Pieciukiewicz
 */
@Named
@Scope("request")
public class UserRegistrationBacking {

    @Inject
    private UserRegistrationForm form;

    @Inject
    private BeanValidator beanValidator;

    @Inject
    private UserRegistrationCommand userRegistrationCommand;

    @Inject
    private MessagesUtils messagesUtils;
    
    public String registerUser() {
        if (beanValidator.validateWithMessages(form)) {
            try {
                userRegistrationCommand.registerUser(form.getName(), form.getEmail(), form.getPassword());
                return Pages.USER_REGISTERED.redirect();
            } catch (UserAlreadyRegisteredException ex) {
                messagesUtils.publish(ex);
            }
        }
        return null;
    }
}
