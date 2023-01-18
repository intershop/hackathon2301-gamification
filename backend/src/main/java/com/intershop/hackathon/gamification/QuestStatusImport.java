package com.intershop.hackathon.gamification;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.azd.workitemtracking.types.WorkItem;

import com.intershop.hackathon.gamification.ado.AdoClient;
import com.intershop.hackathon.gamification.ado.QuestUpdateMapper;
import com.intershop.hackathon.gamification.orm.Quest;
import com.intershop.hackathon.gamification.orm.QuestRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

public class QuestStatusImport
{
    @Inject AdoClient ado;
    @Inject QuestRepository questRepository;
    @Inject QuestUpdateMapper questUpdateMapper;
    @Inject LevelCalculator levelCalculator;

    public void checkForStatusUpdates()
    {
        List<Quest> questList = questRepository.findAll().list();

        for (Quest quest: questList)
        {
            String currentState = quest.getState();
            Optional<WorkItem> workItemOptional = ado.getWorkItem(quest.getId());

            if (workItemOptional.isPresent())
            {
                // TODO update only if needed
                quest = questUpdateMapper.apply(workItemOptional.get(), quest);
            }
        }
    }

    protected void creditExperiencePoints(Quest quest, String previousState)
    {
        if ("Closed".equals(quest.getState()) && !previousState.equals(quest.getState()))
        {
            int level = levelCalculator.getLevel(
                levelCalculator.mapDifficultyLevel(quest.getSeverity())
            );

//            String assignedTo = quest.getAssignedTo(); // TODO
        }
    }
}
