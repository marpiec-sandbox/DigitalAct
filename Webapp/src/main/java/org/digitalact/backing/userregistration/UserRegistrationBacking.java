package org.digitalact.backing.userregistration;

import org.digitalact.domain.users.command.UserRegistrationCommand;
import org.digitalact.domain.users.exception.UserAlreadyRegisteredException;
import org.digitalact.engine.messages.MessagesUtils;
import org.digitalact.engine.params.Pages;
import org.digitalact.engine.validation.BeanValidator;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Backing bean odpowiedzialny za rejestrację nowych użytkowników.
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

    /**
     * Wykonuje rejestrację użytkownika.
     * @return redirect do odpowiedniej strony lub null w przypadku bledu walidacji
     */
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

    /**
     * Wykonuje rejestrację superużytkownika.
     * @return redirect do odpowiedniej strony lub null w przypadku bledu walidacji
     */
    public String registerSuperuser() {
        if (beanValidator.validateWithMessages(form)) {
            try {
                userRegistrationCommand.registerSuperUser(form.getName(), form.getEmail(), form.getPassword());
                return Pages.SUPERUSER_CREATED.redirect();
            } catch (UserAlreadyRegisteredException ex) {
                messagesUtils.publish(ex);
            }
        }
        return null;
    }
}
