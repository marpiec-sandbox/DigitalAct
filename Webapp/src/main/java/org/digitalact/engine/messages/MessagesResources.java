package org.digitalact.engine.messages;

import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

/**
 *
 * @author Marcin Pieciukiewicz
 */
public class MessagesResources {

    private static ResourceBundle messages;

    private String resourceBundleName;
    
    public MessagesResources(String resourceBundleName) {
        this.resourceBundleName = resourceBundleName;
    }

    public String getMessage(String key) {
        if (messages == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            messages = context.getApplication().getResourceBundle(context, resourceBundleName);
        }

        return messages.getString(key);
    }
}
