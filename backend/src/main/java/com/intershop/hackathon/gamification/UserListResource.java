package com.intershop.hackathon.gamification;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/users")
public class UserListResource
{
    public final UserRepository userRepository;

    public UserListResource(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> all() {
        return userRepository.findAll();
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public User get(@PathParam("username") String username) {
        return userRepository.findById(username);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User post(User user) {
        return userRepository.insert(
                new User("email")
        );
    }
}