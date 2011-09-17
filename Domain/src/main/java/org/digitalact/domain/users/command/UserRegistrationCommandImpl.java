package org.digitalact.domain.users.command;

import org.digitalact.domain.users.exception.UserAlreadyRegisteredException;
import java.util.ArrayList;
import java.util.List;
import org.digitalact.domain.users.dao.PersonDao;
import org.digitalact.domain.users.entity.Person;
import org.digitalact.domain.users.entity.PersonRole;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.Date;
import org.digitalact.domain.users.exception.UserAlreadyActivatedException;
import org.digitalact.domain.users.emailtask.ConfirmAccountEmailData;
import org.digitalact.emailtask.MailTaskCreator;
import org.digitalact.domain.users.emailtask.ConfirmAccountTask;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 * Klasa umożliwiająca rejestrowanie użykowników.
 * @author Marcin Pieciukiewicz
 */
@Named
public class UserRegistrationCommandImpl implements UserRegistrationCommand {

    @Inject
    private PersonDao personDao;

    @Inject
    private PasswordEncoder passwordEncoder;
    
    @Inject
    private MailTaskCreator confirmationService;

    @Override
    public void registerSuperUser(String name, String email, String password) throws UserAlreadyRegisteredException {

        checkIfNoUserRegistered();
        Person person = constructSuperuser(name, email, password);
        personDao.save(person);
        requestEmailConfirmation(email, person);
    }

    @Override
    public void registerUser(String name, String email, String password) throws UserAlreadyRegisteredException {

        checkIfUserAlreadyRegistered(email);
        Person person = constructPerson(name, email, password);
        person.setRoles(createDefaultUserRoles());
        personDao.save(person);
        requestEmailConfirmation(email, person);
    }
    
    private void requestEmailConfirmation(String email, Person person) {//TODO: zmienic person na personId
        
        ConfirmAccountTask task = new ConfirmAccountTask(person.getPersonId());
        ConfirmAccountEmailData emailData = prepareEmailData(person);
        
        confirmationService.createTask(email, person.getPersonId(), task, emailData);
        
    }

    private ConfirmAccountEmailData prepareEmailData(Person person) {//TODO: zmienic person na personId
        ConfirmAccountEmailData emailData = new ConfirmAccountEmailData();
        emailData.setUsername(person.getName());
        return emailData;
    }

    private Person constructSuperuser(String name, String email, String password) {
        Person person = constructPerson(email, name, password);
        person.setRoles(Arrays.asList(PersonRole.values()));
        return person;
    }

    private Person constructPerson(String name, String email, String password) {
        Person person = new Person();
        person.setCreationTime(new Date());
        person.setEmail(email);
        person.setEnabled(false);
        person.setName(name);
        String encodedPassword = passwordEncoder.encodePassword(password, email);
        person.setPassword(encodedPassword);

        return person;
    }

    private List<PersonRole> createDefaultUserRoles() {
        List<PersonRole> roles = new ArrayList<PersonRole>();
        roles.add(PersonRole.USER);
        return roles;
    }
    
    private void checkIfNoUserRegistered() throws UserAlreadyRegisteredException {

        if (!personDao.readAll().isEmpty()) {
            throw new UserAlreadyRegisteredException();
        }
    }

    private void checkIfUserAlreadyRegistered(String email) throws UserAlreadyRegisteredException {

        if (personDao.findByEmail(email)!=null) {
            throw new UserAlreadyRegisteredException();
        }
    }

    @Override
    public void activateAccount(long personId) throws UserAlreadyActivatedException {
        Person person = personDao.readByPrimaryKey(personId);
        
        Date currentActivationTime = person.getActivationTime();
        if(currentActivationTime!=null) {
            throw new UserAlreadyActivatedException(currentActivationTime);
        }
        
        person.setActivationTime(new Date());
        person.setEnabled(true);
        
        personDao.save(person);
   }



}
