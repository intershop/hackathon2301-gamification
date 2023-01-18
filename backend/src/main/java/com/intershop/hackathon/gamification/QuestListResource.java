package com.intershop.hackathon.gamification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.azd.common.types.Author;
import org.azd.workitemtracking.types.WorkItem;
import org.azd.workitemtracking.types.WorkItemFields;

import com.intershop.hackathon.gamification.ado.AdoClient;
import com.intershop.hackathon.gamification.ado.QuestMapper;
import com.intershop.hackathon.gamification.ado.TopicMapper;
import com.intershop.hackathon.gamification.model.Quest;
import com.intershop.hackathon.gamification.model.QuestRepository;

@Path("/quests")
public class QuestListResource
{

    private final QuestRepository questRepository;
    @Inject
    AdoClient ado;

    @Inject QuestMapper questMapper;
    @Inject TopicMapper topicMapper;

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
            var quest = questMapper.apply(wi);
            var topic = topicMapper.apply(wi);
            Collection<Quest> questsByTopic = questMap.get(topic);

            if (questsByTopic == null)
            {
                questsByTopic = new ArrayList<>();
                questMap.put(topic, questsByTopic);
            }
            questsByTopic.add(quest);
            questRepository.create(quest);
        }

        return Response.ok(questMap).build();
    }


}