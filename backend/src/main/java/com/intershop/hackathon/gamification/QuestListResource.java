package com.intershop.hackathon.gamification;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/quests")
public class QuestListResource
{

    @GET
    @Produces(RestConstants.MEDIA_TYPE_JSON_API)
    public String hello() {
        return "Hey from 'quests' resource";
    }
}