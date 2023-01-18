package com.intershop.hackathon.gamification;

import java.util.Optional;

import com.intershop.hackathon.gamification.ado.AdoClient;
import org.azd.workitemtracking.types.WorkItem;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users/{id}/quests")
public class UserItemQuestResource
{
    @Inject
    AdoClient ado;

    @POST
    @Produces(RestConstants.MEDIA_TYPE_JSON_API)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response postUser(@PathParam("id") String email, @Valid WorkItem workItem)
    {
        /*EampleBody: {"id": 82713, "fields": {"System.State": "New"}} */
        User user = new User(email);
        user.setEmail(email);
        Optional<WorkItem> workItemUpdated = ado.updateWorkItem(String.valueOf(workItem.getId()), user);
        if (workItemUpdated.isPresent())
        {
            return Response.ok(workItemUpdated.get()).build();
        }
        return Response.status(400, "Error while updating").build();
    }
}