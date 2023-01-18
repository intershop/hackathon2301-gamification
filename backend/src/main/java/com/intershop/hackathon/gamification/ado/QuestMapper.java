package com.intershop.hackathon.gamification.ado;

import java.util.function.Function;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.azd.common.types.Author;
import org.azd.workitemtracking.types.WorkItem;
import org.azd.workitemtracking.types.WorkItemFields;

import com.intershop.hackathon.gamification.orm.Quest;

@Singleton
public class QuestMapper implements Function<WorkItem, Quest>
{
    @Inject TopicMapper topicMapper;

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
        quest.setAssignedTo(resolveUser(fields.getSystemAssignedTo()));
        quest.setCreatedBy(resolveUser(fields.getSystemCreatedBy()));
        quest.setState(fields.getSystemState());
        quest.setSeverity((String)fields.getOtherFields().get("Microsoft.VSTS.Common.Severity"));
        quest.setTopic(topicMapper.apply(workItem));
        return quest;
    }

    private String resolveUser(Author author)
    {
        if (author != null)
        {
            return author.getUniqueName();
//            return new User(author.getUniqueName());
        }
        return null;
    }
}
