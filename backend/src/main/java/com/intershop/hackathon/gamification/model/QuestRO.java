package com.intershop.hackathon.gamification.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;

@Schema(description = "A quest to be solved.")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QuestRO
{
    private String id;
    private String title;
    private UserRO createdBy;
    private UserRO assignedTo;
    private String state;

    public QuestRO(String id, String title)
    {
        this.id = id;
        this.title = title;
    }

    public String getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public UserRO getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(UserRO createdBy)
    {
        this.createdBy = createdBy;
    }

    public UserRO getAssignedTo()
    {
        return assignedTo;
    }

    public void setAssignedTo(UserRO assignedTo)
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
