package com.intershop.hackathon.gamification;

import java.util.Optional;

import com.intershop.hackathon.gamification.ado.AdoClient;
import com.intershop.hackathon.gamification.ado.QuestMapper;
import com.intershop.hackathon.gamification.ado.QuestUpdateMapper;
import com.intershop.hackathon.gamification.orm.Error;
import com.intershop.hackathon.gamification.orm.Quest;
import com.intershop.hackathon.gamification.orm.QuestRepository;
import com.intershop.hackathon.gamification.orm.User;
import com.intershop.hackathon.gamification.orm.UserRepository;

import org.azd.workitemtracking.types.WorkItem;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users/{username}/quests")
public class UserItemQuestResource
{
    @Inject AdoClient ado;
    @Inject UserRepository userRepository;
    @Inject QuestRepository questRepository;
    @Inject QuestMapper questMapper;
    @Inject QuestUpdateMapper questUpdateMapper;

    @POST
    @Produces(RestConstants.MEDIA_TYPE_JSON_API)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response postUser(@PathParam("username") String username, @Valid WorkItem workItem)
    {
        return claimQuest(username, String.valueOf(workItem.getId()));
    }

    @GET
    @Produces(RestConstants.MEDIA_TYPE_JSON_API)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response postUser(@PathParam("username") String username, @QueryParam("workitemid") String workItemId)
    {
        return claimQuest(username, workItemId);
    }

    protected Response claimQuest(String username, String workItemId)
    {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent())
        {
            return Response.status(404).entity(new Error("user_not_found", "User '" + username + "' not found")).build();
        }

        Optional<WorkItem> workItemOptional = ado.getWorkItem(workItemId);
        if (!workItemOptional.isPresent())
        {
            return Response.status(404).entity(new Error("quest_not_found", "Quest '" + workItemId + "' not found")).build();
        }

        Optional<Quest> questOptional = questRepository.findByIdOptional(workItemId);
        if (!questOptional.isPresent())
        { // should never happen, but who knows ;)
            questOptional = Optional.of(questRepository.create(questMapper.apply(workItemOptional.get())));
        }

        /*ExampleBody: {"id": 82713, "fields": {"System.State": "New"}} */
        Optional<WorkItem> workItemUpdated = ado.updateWorkItem(workItemId, userOptional.get());
        if (workItemUpdated.isPresent())
        {
            return Response.ok(questUpdateMapper.apply(workItemUpdated.get(), questOptional.get())).build();
        }

        return Response.status(400).entity(new Error("internal_error", "An error occurred while processing the data")).build();
    }
}