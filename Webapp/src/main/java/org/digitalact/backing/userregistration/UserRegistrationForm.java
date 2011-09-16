package org.digitalact.backing.userregistration;

import javax.inject.Named;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.springframework.context.annotation.Scope;

/**
 * @author Marcin Pieciukiewicz
 */
@Named
@Scope("request")
public class UserRegistrationForm {

    @NotNull
    @Size(min=3)
    private String name;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min=3)
    private String password;
    @NotNull
    @Size(min=3)
    private String passwordRetype;

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

    public String getPasswordRetype() {
        return passwordRetype;
    }

    public void setPasswordRetype(String passwordRetype) {
        this.passwordRetype = passwordRetype;
    }

}
