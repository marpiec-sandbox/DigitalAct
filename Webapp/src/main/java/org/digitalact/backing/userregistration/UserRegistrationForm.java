package org.digitalact.backing.userregistration;

import org.apache.commons.lang.StringUtils;
import org.digitalact.constants.MyConstants;
import org.hibernate.validator.constraints.Email;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Formularz zawierający dane użytkownika do zarejestrowania.
 * @author Marcin Pieciukiewicz
 */
@Named
@Scope("request")
public class UserRegistrationForm {

    @NotNull
    @Size(min= MyConstants.Constrains.MIN_DEFAULT_STRING_INPUT)
    private String name;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min=MyConstants.Constrains.MIN_DEFAULT_STRING_INPUT)
    private String password;
    @NotNull
    @Size(min=MyConstants.Constrains.MIN_DEFAULT_STRING_INPUT)
    private String passwordRetype;

    /**
     * Weryfikuje czy wprowadzone hasła są takie same.
     * @return true jeżeli są takie same
     */
    @AssertTrue(message="Podane hasła nie są równe.")
    public boolean isPasswordsEquals() {
        return StringUtils.equals(password, passwordRetype);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordRetype(String passwordRetype) {
        this.passwordRetype = passwordRetype;
    }

    public String getPasswordRetype() {
        return passwordRetype;
    }
}
