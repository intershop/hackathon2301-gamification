package com.intershop.hackathon.gamification;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/users")
public class UserListResource
{
    @Inject
    public UserRepository userRepository;

    public UserListResource()
    {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<User> all() {
        return userRepository.findAll();
    }

    @GET
    @Path("/{username}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public User get(@PathParam("username") String username) {
        return userRepository.findById(username);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User post(User user) {
        return userRepository.insert(
                new User()
        );
    }
}