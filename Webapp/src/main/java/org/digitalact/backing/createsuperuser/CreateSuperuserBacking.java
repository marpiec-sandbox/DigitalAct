package org.digitalact.backing.createsuperuser;

import org.digitalact.domain.users.exception.UserAlreadyRegisteredException;
import org.digitalact.domain.users.command.UserRegistrationCommand;

import javax.inject.Inject;
import javax.inject.Named;
import org.digitalact.engine.messages.MessagesUtils;
import org.digitalact.engine.params.Pages;
import org.digitalact.engine.validation.BeanValidator;
import org.springframework.context.annotation.Scope;

/**
 * Backing bean odpowiedzialny za rejestrację superużytkownika.
 * @author Marcin Pieciukiewicz
 */
@Named
@Scope("request")
public class CreateSuperuserBacking {

    @Inject
    private CreateSuperuserForm form;
    
    @Inject
    private BeanValidator beanValidator;

    @Inject
    private MessagesUtils messagesUtils;
    
    @Inject
    private UserRegistrationCommand userRegistrationCommand;

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
