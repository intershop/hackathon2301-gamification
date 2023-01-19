package com.intershop.hackathon.gamification.ado;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.azd.common.types.Author;
import org.azd.workitemtracking.types.WorkItem;
import org.azd.workitemtracking.types.WorkItemFields;

import com.intershop.hackathon.gamification.LevelCalculator;
import com.intershop.hackathon.gamification.orm.Quest;
import com.intershop.hackathon.gamification.orm.User;
import com.intershop.hackathon.gamification.orm.UserRepository;

@Singleton
public class QuestUpdateMapper implements BiFunction<WorkItem, Quest, Quest>
{
    @Inject TopicMapper topicMapper;
    @Inject UserRepository userRepository;

    @Override
    public Quest apply(WorkItem workItem, Quest quest)
    {
        if (workItem != null)
        {
            WorkItemFields fields = workItem.getFields();
            quest.setTitle(workItem.getFields().getSystemTitle());
            quest.setAssignedTo(resolveUser(fields.getSystemAssignedTo()));
            quest.setCreatedBy(resolveUser(fields.getSystemCreatedBy()));
            quest.setState(fields.getSystemState());
            quest.setSeverity((String)fields.getOtherFields().get("Microsoft.VSTS.Common.Severity"));
            quest.setTopic(topicMapper.apply(workItem));
        }

        return quest;
    }

    private User resolveUser(Author author)
    {
        if (author != null)
        {
            return userRepository.findByEmail(author.getUniqueName()).orElse(null);
        }
        return null;
    }
}
