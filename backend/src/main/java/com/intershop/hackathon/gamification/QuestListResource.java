package com.intershop.hackathon.gamification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.azd.common.types.Author;
import org.azd.workitemtracking.types.WorkItem;
import org.azd.workitemtracking.types.WorkItemFields;

import com.intershop.hackathon.gamification.ado.AdoClient;
import com.intershop.hackathon.gamification.model.QuestRO;
import com.intershop.hackathon.gamification.model.UserRO;

@Path("/quests")
public class QuestListResource
{

    @Inject
    AdoClient ado;

    @GET
    @Produces(RestConstants.MEDIA_TYPE_JSON_API)
    public Response getQuests()
    {
        Collection<WorkItem> workItems = ado.getWorkItems();

        Map<String, Collection<QuestRO>> questMap = new HashMap<>();
        for (WorkItem wi: workItems)
        {
            WorkItemFields fields = wi.getFields();
            var quest = new QuestRO(wi.getId(),  fields.getSystemTitle());
            quest.setAssignedTo(resolveUser(fields.getSystemAssignedTo()));
            quest.setCreatedBy(resolveUser(fields.getSystemCreatedBy()));
            quest.setState(fields.getSystemState());

            String topic = getTopic(fields);
            Collection<QuestRO> questsByTopic = questMap.get(topic);

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
            return new User(author.getUniqueName());
        }
        return null;
    }

    private String getTopic(WorkItemFields fields)
    {
        return "topic1";
    }
}