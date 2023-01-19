package com.intershop.hackathon.gamification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.azd.common.types.Author;
import org.azd.workitemtracking.types.WorkItem;
import org.azd.workitemtracking.types.WorkItemFields;

import com.intershop.hackathon.gamification.ado.AdoClient;
import com.intershop.hackathon.gamification.ado.QuestMapper;
import com.intershop.hackathon.gamification.ado.QuestUpdateMapper;
import com.intershop.hackathon.gamification.orm.Quest;
import com.intershop.hackathon.gamification.orm.QuestRepository;
import com.intershop.hackathon.gamification.orm.User;

@Path("/quests")
public class QuestListResource
{

    private final QuestRepository questRepository;
    @Inject
    AdoClient ado;

    @Inject QuestMapper questMapper;
    @Inject QuestUpdateMapper questUpdateMapper;
    @Inject QuestUpdater questUpdater;

    public QuestListResource(QuestRepository questRepository)
    {
        this.questRepository = questRepository;
    }

    @GET
    @Produces(RestConstants.MEDIA_TYPE_JSON_API)
    public Response getQuests()
    {
        Map<String, Collection<Quest>> questMap = new HashMap<>();

        Collection<WorkItem> workItems = ado.getWorkItems();
        for (WorkItem wi: workItems)
        {
            Optional<Quest> questOpt = questRepository.findByIdOptional(String.valueOf(wi.getId()));
            Quest quest;
            if (questOpt.isPresent()) // TODO extract DB change from GET
            {
                quest = questUpdater.updateQuest(questOpt.get(), wi);
            }
            else
            {
                quest = questMapper.apply(wi);
                questRepository.create(quest);
            }
            var topic = quest.getTopic();
            Collection<Quest> questsByTopic = questMap.get(topic);

            if (questsByTopic == null)
            {
                questsByTopic = new ArrayList<>();
                questMap.put(topic, questsByTopic);
            }
            questsByTopic.add(quest);
        }

        return Response.ok(questMap).build();
    }

    User resolveUser(Author author)
    {
        if (author != null)
        {
            User newUser = new User();
            newUser.username = author.getUniqueName();
            return newUser;
        }
        return null;
    }

    private String getTopic(WorkItemFields fields)
    {
        return "topic1";
    }
}