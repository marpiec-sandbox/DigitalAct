package org.digitalact.emailtask.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.digitalact.constants.MyConstants;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Encja reprezentująca zadanie do potwierdzenia przez email.
 * @author Marcin Pieciukiewicz
 */
@Entity
@SequenceGenerator(sequenceName = "EMAIL_TASK_SEQ", name = "EMAIL_TASK_SEQ_GEN", allocationSize = 1)
public class EmailTask implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMAIL_TASK_SEQ_GEN")
    private Long emailTaskId;

    @NotBlank
    private String email;
    
    @NotNull
    private Long personId;
    
    @Column(length = MyConstants.MailTask.CODE_LENGTH)
    private String taskCode;

    @Column(length = MyConstants.Constrains.MAX_FULL_CLASS_NAME)
    private String taskClass;

    @Column(length = MyConstants.MailTask.SERIALIZED_TASK_SIZE)
    @NotBlank
    private String serializedTask;
    
    @NotNull
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creationTime;
        
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date executionTime;
        
    @NotNull
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date expiriationTime;

    public Long getEmailTaskId() {
        return emailTaskId;
    }

    public void setEmailTaskId(Long emailTaskId) {
        this.emailTaskId = emailTaskId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSerializedTask() {
        return serializedTask;
    }

    public void setSerializedTask(String serializedTask) {
        this.serializedTask = serializedTask;
    }

    public String getTaskClass() {
        return taskClass;
    }

    public void setTaskClass(String taskClass) {
        this.taskClass = taskClass;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getExpiriationTime() {
        return expiriationTime;
    }

    public void setExpiriationTime(Date expiriationTime) {
        this.expiriationTime = expiriationTime;
    }

    public Date getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
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