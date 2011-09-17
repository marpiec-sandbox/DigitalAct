package org.digitalact.emailtask;

import java.util.Date;
import javax.inject.Inject;
import javax.inject.Named;

import org.digitalact.constants.MyConstants;
import org.digitalact.mail.EmailSender;
import org.digitalact.emailtask.dao.EmailTaskDao;
import org.digitalact.emailtask.entity.EmailTask;
import org.digitalact.utils.MyDateUtils;
import org.digitalact.utils.MyJsonSerializer;
import org.digitalact.utils.MyRandomStringUtils;

/**
 * Klasa umożliwiająca stworzenie zadania potwierdzanego emailem.
 *
 * @author Marcin Pieciukiewicz
 */
@Named
public class MailTaskCreatorImpl implements MailTaskCreator {

    @Inject
    private EmailTaskDao emailTaskDao;
    
    @Inject
    private EmailSender emailSender;
    
    @Override
    public void createTask(String email, Long personId, Task task, TaskEmailData emailData) {
        String taskCode = generateTaskCode();
        createAndSaveTask(email, personId, taskCode, task);
        prepareAndSendEmail(email, taskCode, emailData);
    }

    private void prepareAndSendEmail( String emailAddress, String taskCode, TaskEmailData emailData) {
        emailData.setTaskCode(taskCode);
        emailSender.sendEmail(emailAddress, emailData);
    }

    private void createAndSaveTask(String email, Long personId, String taskCode, Task task) {
        Date creationDate = new Date();
        Date expiriationDate = MyDateUtils.add24h(creationDate);
        
        EmailTask emailTask = constructEmailTask(creationDate, email, expiriationDate, personId, task, taskCode);
        emailTaskDao.save(emailTask);
    }

    private EmailTask constructEmailTask(Date creationDate, String email, Date expiriationDate, Long personId, Task task, String taskCode) {
        EmailTask emailTask = new EmailTask();
        emailTask.setCreationTime(creationDate);
        emailTask.setEmail(email);
        emailTask.setExpiriationTime(expiriationDate);
        emailTask.setPersonId(personId);
        emailTask.setTaskClass(task.getClass().getCanonicalName());
        emailTask.setSerializedTask(serialize(task));
        emailTask.setTaskCode(taskCode);
        return emailTask;
    }


    private String serialize(Task task) {
        return MyJsonSerializer.serialize(task);
    }

    private String generateTaskCode() {
        String taskCode;
        EmailTask emailTask = null;
        do {
            taskCode = MyRandomStringUtils.generateAlphanumericString(MyConstants.MailTask.CODE_LENGTH);
            emailTask = emailTaskDao.findByTaskCode(taskCode);
        } while (emailTask!=null);
        return taskCode;
    }

}
