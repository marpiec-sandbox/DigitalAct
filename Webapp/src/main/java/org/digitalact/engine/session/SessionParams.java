package org.digitalact.engine.session;

import javax.faces.context.FacesContext;
import org.digitalact.domain.users.entity.Person;

import javax.servlet.http.HttpSession;

/**
 * Klasa umożliwiająca łatwy dostęp do parametrów sesji użytkownika.
 * @author Marcin Pieciukiewicz
 */
public final class SessionParams {

    public static final String PERSON = "person";

    private SessionParams() {
    }

    private static HttpSession session() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    /**
     * Ostawia obiekt person w sesji.
     * @param person obiekt person
     */
    public static void setPerson(Person person) {
        session().setAttribute(PERSON, person);
    }

    /**
     * Pobiera obiekt person z sesji użytkownika.
     * @return obiekt person
     */
    public static Person getPerson() {
        return (Person) session().getAttribute(PERSON);
    }

}
