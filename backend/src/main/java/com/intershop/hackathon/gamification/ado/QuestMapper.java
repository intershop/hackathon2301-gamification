package com.intershop.hackathon.gamification.ado;

import java.util.function.Function;

import javax.inject.Singleton;

import org.azd.common.types.Author;
import org.azd.workitemtracking.types.WorkItem;
import org.azd.workitemtracking.types.WorkItemFields;

import com.intershop.hackathon.gamification.User;
import com.intershop.hackathon.gamification.model.Quest;

@Singleton
public class QuestMapper implements Function<WorkItem, Quest>
{
    @Override public Quest apply(WorkItem workItem)
    {
        if (workItem == null)
        {
            return null;
        }

        WorkItemFields fields = workItem.getFields();
        var quest = new Quest();
        quest.setId(String.valueOf(workItem.getId()));
        quest.setTitle(workItem.getFields().getSystemTitle());
        quest.setAssignedTo(fields.getSystemAssignedTo().getUniqueName());
        quest.setCreatedBy(fields.getSystemCreatedBy().getUniqueName());
//        quest.setAssignedTo(resolveUser(fields.getSystemAssignedTo()));
//        quest.setCreatedBy(resolveUser(fields.getSystemCreatedBy()));
        quest.setState(fields.getSystemState());
        return quest;
    }

    private User resolveUser(Author author)
    {
        if (author != null)
        {
            return new User(author.getUniqueName());
        }
        return null;
    }
}
