package com.intershop.hackathon.gamification.model;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.azd.common.types.Author;
import org.azd.workitemtracking.types.WorkItem;

import com.intershop.hackathon.gamification.User;
import com.intershop.hackathon.gamification.ado.AdoClient;
import com.intershop.hackathon.gamification.ado.QuestUpdateMapper;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class QuestRepository implements PanacheRepositoryBase<Quest, String>
{
    @Inject AdoClient ado;
    @Inject QuestUpdateMapper questUpdateMapper;

    /**
     * Creates and persists the given quest in the underlying data store.
     *
     * @param quest the quest to create
     * @return the persisted quest
     */
    @Transactional
    public Quest create(Quest quest)
    {
        Objects.requireNonNull(quest);
        quest.persist();
        return quest;
    }


    @Transactional
    public boolean remove(int id)
    {
        Optional<Quest> quest = findByIdOptional(String.valueOf(id));
        if (quest.isPresent())
        {
            delete(quest.get());
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     *
     */
    @Transactional
    public Quest claimQuest(Quest quest, User user)
    {
        Objects.requireNonNull(quest);
        Objects.requireNonNull(user);

        if ("New".equals(quest.getState()))
        {
            Optional<WorkItem> workItem = ado.updateWorkItem(quest.getId(), user);

            if (workItem.isPresent())
            {
                quest = questUpdateMapper.apply(workItem.get(), quest);
            }
        }
        return quest;
    }
}
