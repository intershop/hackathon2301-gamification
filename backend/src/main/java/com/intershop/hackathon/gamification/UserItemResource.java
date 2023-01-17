package com.intershop.hackathon.gamification;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users/{id}")
public class UserItemResource
{

    @GET
    @Produces(RestConstants.MEDIA_TYPE_JSON_API)
    public String hello() {
        return "Hey from 'users' item resource";
    }
}