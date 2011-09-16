package org.digitalact.domain.notes.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import org.digitalact.domain.users.entity.Person;

/**
 * Notatka.
 * @author Marcin Pieciukiewicz
 */
@Entity
@SequenceGenerator(sequenceName = "STICKY_NOTE_SEQ", name = "STICKY_NOTE_SEQ_GEN", allocationSize = 1)
public class StickyNote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STICKY_NOTE_SEQ_GEN")
    private Long stickyNoteId;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date deadLineTime;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "person_owner_id")
    private Person ownerPerson;
    
    @Column(length = 1024)
    private String text;
    
    @NotNull
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creationTime;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date deletionTime;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StickyNoteStatus status;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "stickyNote")
    private StickyNoteVisualProperties visualProperties;

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
    
    public Date getDeletionTime() {
        return deletionTime;
    }

    public void setDeletionTime(Date deletionTime) {
        this.deletionTime = deletionTime;
    }

    public Date getDeadLineTime() {
        return deadLineTime;
    }

    public void setDeadLineTime(Date deadLineTime) {
        this.deadLineTime = deadLineTime;
    }

    public StickyNoteStatus getStatus() {
        return status;
    }

    public void setStatus(StickyNoteStatus status) {
        this.status = status;
    }

    public Long getStickyNoteId() {
        return stickyNoteId;
    }

    public void setStickyNoteId(Long stickyNoteId) {
        this.stickyNoteId = stickyNoteId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Person getOwnerPerson() {
        return ownerPerson;
    }

    public void setOwnerPerson(Person ownerPerson) {
        this.ownerPerson = ownerPerson;
    }

    public StickyNoteVisualProperties getVisualProperties() {
        return visualProperties;
    }

    public void setVisualProperties(StickyNoteVisualProperties visualProperties) {
        this.visualProperties = visualProperties;
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
