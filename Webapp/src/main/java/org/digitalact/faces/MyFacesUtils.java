package org.digitalact.faces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Klasa narzędziowa dostarczająca proste narzędzia do pracy z JSF.
 */
public final class MyFacesUtils {

    private MyFacesUtils() {
    }

    /**
     * Dodaje komunikat walidacji to komunikatów JSF.
     * @param clientId id komponentu którego dotyczy walidacja
     * @param message komunikat walidacji
     */
    public static void addValidationMessage(String clientId, String message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        //Jeżeli pusty to zignoruj bo jest to prawdopodobnie test jednostkowy
        if(facesContext!=null) {
            FacesMessage facesMessage = new FacesMessage(message);
            facesContext.addMessage(clientId, facesMessage);
        }
    }

}