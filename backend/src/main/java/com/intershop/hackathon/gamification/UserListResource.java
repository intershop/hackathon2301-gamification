package com.intershop.hackathon.gamification;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class UserListResource
{

    @GET
    @Produces(RestConstants.MEDIA_TYPE_JSON_API)
    public String hello() {
        return "Hey from 'users' resource";
    }
}