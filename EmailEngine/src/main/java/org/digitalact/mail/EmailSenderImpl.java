package org.digitalact.mail;

import javax.inject.Inject;
import javax.inject.Named;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;

/**
 * Klasa służąca do wysyłania e-maili.
 * @author Marcin Pieciukiewicz
 */
@Named
public class EmailSenderImpl implements EmailSender {
    
    @Inject
    private JavaMailSender mailSender;
    @Inject
    private VelocityEngine velocityEngine;
    
    @Async
    @Override
    public void sendEmail(String emailAddress, EmailData emailData) {
        
      MimeMessagePreparator preparator = new MessagePreparator(emailAddress, emailData, velocityEngine);
      this.mailSender.send(preparator);

    }


    
}
