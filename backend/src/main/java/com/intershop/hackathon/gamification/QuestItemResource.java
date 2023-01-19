package com.intershop.hackathon.gamification;

import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.azd.workitemtracking.types.WorkItem;

import com.intershop.hackathon.gamification.ado.AdoClient;
import com.intershop.hackathon.gamification.ado.QuestMapper;
import com.intershop.hackathon.gamification.orm.Quest;
import com.intershop.hackathon.gamification.orm.QuestRepository;

@Path("/quests/{id}")
public class QuestItemResource
{
    private final QuestRepository questRepository;
    @Inject AdoClient ado;
    @Inject QuestMapper questMapper;
    @Inject QuestUpdater questUpdater;

    public QuestItemResource(QuestRepository questRepository)
    {
        this.questRepository = questRepository;
    }

    @GET
    @Produces(RestConstants.MEDIA_TYPE_JSON_API)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getQuest(@PathParam("id") String id)
    {
        Optional<WorkItem> workItemOptional = ado.getWorkItem(id);
        if (workItemOptional.isPresent())
        {
            WorkItem workItem = workItemOptional.get();
            Optional<Quest> questOptional = questRepository.findByIdOptional(String.valueOf(workItem.getId()));

            Quest quest;
            if (questOptional.isPresent())
            {
                quest = questOptional.get();
                quest = questUpdater.updateQuest(quest, workItem);
            }
            else {
                quest = questMapper.apply(workItem);
                questRepository.create(quest);
            }

            return Response.ok(quest).build();
        }
        return Response.status(404, "Quest not found").build();
    }
}