package org.digitalact.domain.notes.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Wizualne właściwości notatki, jej położenie, kolor itp.
 * @author Marcin Pieciukiewicz
 */
@Entity
@Table(name = "STICKY_NOTE_VP")
@SequenceGenerator(sequenceName = "STICKY_NOTE_VP_SEQ", name = "STICKY_NOTE_VP_SEQ_GEN", allocationSize = 1)
public class StickyNoteVisualProperties implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STICKY_NOTE_VP_SEQ_GEN")
    private Long stickyNoteVPId;

    @OneToOne
    @JoinColumn(name = "sticky_note_id")
    private StickyNote stickyNote;

    @NotNull
    @Column(name = "POSITION_TOP")
    private int top;

    @NotNull
    @Column(name = "POSITION_LEFT")
    private int left;

    @NotNull
    private int width;

    @NotNull
    private int height;

    @NotNull
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public Long getStickyNoteVPId() {
        return stickyNoteVPId;
    }

    public void setStickyNoteVPId(Long stickyNoteVPId) {
        this.stickyNoteVPId = stickyNoteVPId;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public StickyNote getStickyNote() {
        return stickyNote;
    }

    public void setStickyNote(StickyNote stickyNote) {
        this.stickyNote = stickyNote;
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
