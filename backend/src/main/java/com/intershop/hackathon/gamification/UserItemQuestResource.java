package com.intershop.hackathon.gamification;

import com.intershop.hackathon.gamification.ado.AdoClient;
import org.azd.workitemtracking.types.WorkItem;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users/{id}/quests")
public class UserItemQuestResource
{
    @Inject
    AdoClient ado;
    @POST
    @Produces(RestConstants.MEDIA_TYPE_JSON_API)
    @Consumes(MediaType.APPLICATION_JSON)
    public WorkItem postUser(@PathParam("id") String email, @Valid WorkItem workItem) {
        /*EampleBody: {"id": 82713, "fields": {"System.State": "New"}} */
        WorkItem workItemUpdated = ado.postWorkItem(email, workItem.getId(), workItem);
        return workItemUpdated;
    }
}