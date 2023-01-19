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

@Path("/quests/{id}")
public class QuestItemResource
{
    @Inject AdoClient ado;
    @Inject QuestMapper questMapper;

    @GET
    @Produces(RestConstants.MEDIA_TYPE_JSON_API)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getQuest(@PathParam("id") String id)
    {
        Optional<WorkItem> workItem = ado.getWorkItem(id);
        if (workItem.isPresent())
        {
            return Response.ok(questMapper.apply(workItem.get())).build();
        }
        return Response.status(404, "Quest not found").build();
    }
}