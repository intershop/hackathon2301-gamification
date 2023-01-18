package com.intershop.hackathon.gamification;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Path("/users")
public class UserListResource
{
    @Inject UserRepository userRepository;
    @Inject LevelCalculator levelCalculator;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers()
    {
        List<User> users = userRepository.findAll().list();
        users.stream().forEach(u -> updateLevels(u));
        return users;
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("username") String username)
    {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent())
        {
            return Response.ok(updateLevels(user.get())).build();
        }
        return Response.status(404).build();
    }

    protected User updateLevels(User user)
    {
        if (user != null)
        {
            user.level = levelCalculator.getLevel(user.experience_points);
        }
        return user;
    }
}