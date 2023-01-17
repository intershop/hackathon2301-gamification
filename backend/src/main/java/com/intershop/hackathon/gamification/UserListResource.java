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
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public User get(@PathParam("email") String email) {
        return userRepository.findByEmail(email);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User post(User user) {
        return userRepository.insert(
                new User("email")
        );
    }
}