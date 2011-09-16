/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.digitalact.emailtask;

import org.digitalact.constants.MyConstants;
import org.digitalact.mail.EmailData;
import org.digitalact.mail.EmailTemplate;

/**
 * Klasa oznaczająca dane do emaila wysyłanego w celu potwierdzenia zadania.
 * @author Marcin Pieciukiewicz
 */
public abstract class TaskEmailData extends EmailData {
    
    private String taskConfirmationURL;
    
    private String taskCode;

    public TaskEmailData(EmailTemplate template) {
        super(template);
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
        taskConfirmationURL = MyConstants.MailTask.EXECUTION_URL + taskCode;
    }

    public String getTaskConfirmationURL() {
        return taskConfirmationURL;
    }

}
