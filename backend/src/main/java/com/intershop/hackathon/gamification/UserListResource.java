package com.intershop.hackathon.gamification;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.azd.workitemtracking.types.WorkItem;

import com.intershop.hackathon.gamification.ado.AdoClient;
import com.intershop.hackathon.gamification.ado.QuestMapper;
import com.intershop.hackathon.gamification.orm.Quest;
import com.intershop.hackathon.gamification.orm.QuestRepository;
import com.intershop.hackathon.gamification.orm.User;
import com.intershop.hackathon.gamification.orm.UserRepository;

@Path("/users")
public class UserListResource
{
    @Inject UserRepository userRepository;
    @Inject QuestRepository questRepository;
    @Inject AdoClient ado;
    @Inject QuestUpdater questUpdater;
    @Inject QuestMapper questMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<User> getUsers()
    {
        List<User> users = userRepository.findAll().list();
        // DIRTY HACK ;)
        users.stream().forEach(c -> checkForQuestUpdates(c));

        return users;
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response get(@PathParam("username") String username)
    {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent())
        {
            var user = userOptional.get();
            checkForQuestUpdates(user);

            return Response.ok(user).build();
        }
        return Response.status(404).build();
    }

    private void checkForQuestUpdates(User user)
    {
        List<Quest> quests = questRepository.getQuestByUser(user);

        for (Quest quest: quests)
        {
            Optional<WorkItem> workItemOptional = ado.getWorkItem(quest.getId());
            if (workItemOptional.isPresent())
            {
                WorkItem workItem = workItemOptional.get();
                quest = questUpdater.updateQuest(quest, workItem);
            }
        }
    }
}