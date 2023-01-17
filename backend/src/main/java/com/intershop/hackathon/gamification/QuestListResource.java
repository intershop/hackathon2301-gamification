package com.intershop.hackathon.gamification;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.intershop.hackathon.gamification.ado.AdoClient;
import com.intershop.hackathon.gamification.model.QuestRO;

@Path("/quests")
public class QuestListResource
{

    @Inject
    AdoClient ado;

    @GET
    @Produces(RestConstants.MEDIA_TYPE_JSON_API)
    public Response getQuests()
    {
        QuestRO test = new QuestRO("0815", "dummy quest" );
        return Response.ok(test).build();
    }
}