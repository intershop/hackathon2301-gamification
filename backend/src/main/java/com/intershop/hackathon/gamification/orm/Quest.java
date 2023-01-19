package com.intershop.hackathon.gamification.orm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "quest", uniqueConstraints = {
                @UniqueConstraint(name = "quest_unique_constraint", columnNames = {"id"})

})
@Schema(description = "A quest to be solved.")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Quest extends PanacheEntityBase
{
    @Id
    @Column(updatable = false)
    private String id;

    @Column(nullable = false)
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "created_by"))
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "assigned_to"))
    private User assignedTo;

    @Column(nullable = false)
    private String state;
    @Column
    private String severity;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date creationDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date closingDate;
    @Column
    private String topic;

    public Quest()
    {
        super();
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public User getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(User createdBy)
    {
        this.createdBy = createdBy;
    }

    public User getAssignedTo()
    {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo)
    {
        this.assignedTo = assignedTo;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public Date getClosingDate()
    {
        return closingDate;
    }

    public void setClosingDate(Date closingDate)
    {
        this.closingDate = closingDate;
    }

    public String getSeverity()
    {
        return severity;
    }

    public void setSeverity(String severity)
    {
        this.severity = severity;
    }

    public String getTopic()
    {
        return topic;
    }

    public void setTopic(String topic)
    {
        this.topic = topic;
    }
}
