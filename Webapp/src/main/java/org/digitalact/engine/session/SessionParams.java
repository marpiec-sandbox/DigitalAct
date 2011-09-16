package org.digitalact.engine.session;

import javax.faces.context.FacesContext;
import org.digitalact.domain.users.entity.Person;

import javax.servlet.http.HttpSession;

/**
 * @author Marcin Pieciukiewicz
 */
public final class SessionParams {

    public static final String PERSON = "person";

    private static HttpSession session() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static void setPerson(Person person) {
        session().setAttribute(PERSON, person);
    }

    public static Person getPerson() {
        return (Person) session().getAttribute(PERSON);
    }

}
