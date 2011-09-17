package org.digitalact.engine.messages;

import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

/**
 * Klasa ułatwiająca dostęp do tekstów w propertiesach.
 * @author Marcin Pieciukiewicz
 */
public class MessagesResources {

    private static ResourceBundle messages;

    private String resourceBundleName;

    /**
     * Konstruktor.
     * @param resourceBundleName nazwa bundle name który zawiera komunikaty
     */
    public MessagesResources(String resourceBundleName) {
        this.resourceBundleName = resourceBundleName;
    }

    /**
     * Zwraca treść komunikatu zawartego w pliku properties.
     * @param key klucz komunikatu
     * @return treść komunikatu
     */
    public String getMessage(String key) {
        if (messages == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            messages = context.getApplication().getResourceBundle(context, resourceBundleName);
        }

        return messages.getString(key);
    }
}
