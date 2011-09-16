package org.digitalact.domain.users.entity;

/**
 * Lista możliwych ról użytkowników.
 * @author Marcin Pieciukiewicz
 */
public enum PersonRole {

    USER("Użytkownik"),
    ADMIN("Administrator");

    private final String description;

    private PersonRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
