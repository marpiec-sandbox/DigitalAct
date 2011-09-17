package org.digitalact.utils;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

/**
 * Klasa narzędziowa ułatwiająca wykorzystanie servletów.
 *
 * @author Marcin Pieciukiewicz
 */
public final class MyServletUtils {

    private MyServletUtils() {
    }

    /**
     * Wywołuje wstrzyknięcie beanów springa do servletu.
     * @param httpServlet servlet do którego mają zostać wstrzyknięte zależności
     * @param servletContext kontekst servletu
     */
    public static void applyBeanInjection(HttpServlet httpServlet, ServletContext servletContext) {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(httpServlet, servletContext);
    }
}
