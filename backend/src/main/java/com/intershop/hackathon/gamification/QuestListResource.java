package com.intershop.hackathon.gamification;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, Collection<QuestRO>> questMap = new HashMap<>();
        questMap.put("topic 1",
                        List.of(
                            new QuestRO("11", "dummy quest 11" ),
                            new QuestRO("12", "dummy quest 12" )
                        ));
        questMap.put("topic 2",
                        List.of(
                           new QuestRO("21", "dummy quest 21" ),
                           new QuestRO("22", "dummy quest 22" ),
                           new QuestRO("23", "dummy quest 23" )
                        ));
        return Response.ok(questMap).build();
    }
}