package org.digitalact.domain.users.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.digitalact.constants.MyConstants;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Osoba, użytkownik aplikacji.
 * @author Marcin Pieciukiewicz
 */
@Entity
@SequenceGenerator(sequenceName = "PERSON_SEQ", name = "PERSON_SEQ_GEN", allocationSize = 1)
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_SEQ_GEN")
    private Long personId;
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
    @NotBlank
    @Column(length = MyConstants.Constrains.MAX_PASSWORD_LENGTH)
    private String password;
    @NotBlank
    private String name;
    @NotNull
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creationTime;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date activationTime;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date deletionTime;
    @NotNull
    private boolean enabled;


    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "person_role",
            joinColumns = {@JoinColumn(name = "person_id")})
    @Column(name = "person_role")
    private List<PersonRole> roles;


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(Date activationTime) {
        this.activationTime = activationTime;
    }
    
    public Date getDeletionTime() {
        return deletionTime;
    }

    public void setDeletionTime(Date deletionTime) {
        this.deletionTime = deletionTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public List<PersonRole> getRoles() {
        return roles;
    }

    public void setRoles(List<PersonRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
