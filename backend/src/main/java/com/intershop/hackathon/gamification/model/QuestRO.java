package com.intershop.hackathon.gamification.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.intershop.hackathon.gamification.User;

@Schema(description = "A quest to be solved.")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QuestRO
{
    private int id;
    private String title;
    private User createdBy;
    private User assignedTo;
    private String state;

    public QuestRO(int id, String title)
    {
        this.id = id;
        this.title = title;
    }

    public int getId()
    {
        return id;
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
}
