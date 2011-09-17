package org.digitalact.domain.users.emailtask;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.digitalact.domain.users.command.UserRegistrationCommand;
import org.digitalact.domain.users.exception.UserAlreadyActivatedException;
import org.digitalact.emailtask.MailTaskExecutor;
import org.digitalact.emailtask.TaskHandler;

/**
 * Klasa obsługująca zadanie aktywacji konta.
 * @author Marcin Pieciukiewicz
 */
@Named
public class ConfirmAccountTaskHandler extends TaskHandler<ConfirmAccountTask> {

    @Inject 
    private MailTaskExecutor mailTaskExecutor;
    
    @Inject
    private UserRegistrationCommand userRegistrationCommand;

    /**
     * Konstruktor.
     */
    public ConfirmAccountTaskHandler() {
        super(ConfirmAccountTask.class);
    }
    
    @PostConstruct
    @Override
    public void registerHandler() {
        mailTaskExecutor.registerHandler(this);
    }

    @Override
    public void handle(ConfirmAccountTask task) throws UserAlreadyActivatedException {
        userRegistrationCommand.activateAccount(task.getPersonId());
    }
    
}
