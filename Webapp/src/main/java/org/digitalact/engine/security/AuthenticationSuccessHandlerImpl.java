package org.digitalact.engine.security;

import org.digitalact.domain.users.entity.Person;
import org.digitalact.engine.session.SessionParams;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.inject.Named;
import org.digitalact.domain.users.query.UserQuery;

@Named
public class AuthenticationSuccessHandlerImpl extends SimpleUrlAuthenticationSuccessHandler {

    @Inject
    private UserQuery userQuery;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpRequest,
                                        HttpServletResponse httpResponse, Authentication authentication) throws IOException,
            ServletException {

        putAuthenticatedUserDataInSession(authentication, httpRequest.getSession());
        super.onAuthenticationSuccess(httpRequest, httpResponse, authentication);
    }


    private void putAuthenticatedUserDataInSession(Authentication authentication, HttpSession session) {
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();

        Person person = userQuery.findByEmail(username);

        session.setAttribute(SessionParams.PERSON, person);
    }

}
