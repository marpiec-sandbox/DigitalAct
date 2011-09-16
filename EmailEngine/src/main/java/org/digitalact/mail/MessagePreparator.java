package org.digitalact.mail;

import java.util.HashMap;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import org.apache.velocity.app.VelocityEngine;
import org.digitalact.constants.MyConstants;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * Klasa przygotowująca wiadomość email do wysłania.
 * @author Marcin Pieciukiewicz
 */
class MessagePreparator implements MimeMessagePreparator {

    private static final String DATA_VARIABLE_NAME = "data";
    
    private String emailAddress;
    private EmailData emailData;
    private VelocityEngine velocityEngine;

    public MessagePreparator(String emailAddress, EmailData emailData, VelocityEngine velocityEngine) {
        this.emailAddress = emailAddress;
        this.emailData = emailData;
        this.velocityEngine = velocityEngine;
    }
    
    @Override
    public void prepare(MimeMessage mimeMessage) throws Exception {
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        message.setTo(emailAddress);
        message.setFrom(MyConstants.SYSTEM_EMAIL_SENDER_ADDRESS);
        
        String text = prepareEmailFromTemplate();
        message.setText(text, true);
     }

    private String prepareEmailFromTemplate() {
        
        Map model = new HashMap();
        model.put(DATA_VARIABLE_NAME, emailData);
        
        String templateFile = emailData.getTemplate().getTemplateFile();
        
        return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateFile, model);
    }
    
    
}
