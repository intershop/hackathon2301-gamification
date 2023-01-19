package com.intershop.hackathon.gamification;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.azd.workitemtracking.types.WorkItem;

import com.intershop.hackathon.gamification.ado.AdoClient;
import com.intershop.hackathon.gamification.ado.QuestUpdateMapper;
import com.intershop.hackathon.gamification.orm.Quest;
import com.intershop.hackathon.gamification.orm.QuestRepository;
import com.intershop.hackathon.gamification.orm.User;

@ApplicationScoped
public class QuestUpdater
{
    @Inject AdoClient ado;
    @Inject QuestRepository questRepository;
    @Inject QuestUpdateMapper questUpdateMapper;
    LevelCalculator levelCalculator = new LevelCalculator();

    public void checkForStatusUpdates()
    {
        List<Quest> questList = questRepository.findAll().list();

        for (Quest quest: questList)
        {
            if (!"Closed".equals(quest.getState())) // TODO filter in repo
            {
                Optional<WorkItem> workItemOptional = ado.getWorkItem(quest.getId());

                updateQuest(quest, workItemOptional.orElse(null));
            }
        }
    }

    public Quest updateQuest(Quest quest, WorkItem workItem)
    {
        if (workItem != null)
        {
            String state = quest.getState();
            quest = questUpdateMapper.apply(workItem, quest);
            creditExperiencePoints(quest, state);
        }

        return quest;
    }

    protected void creditExperiencePoints(Quest quest, String previousState)
    {
        if ("Closed".equals(quest.getState()) && !previousState.equals(quest.getState()))
        {
            int claimedPoints = LevelCalculator.getExpPoints(
                levelCalculator.mapDifficultyLevel(quest.getSeverity())
            );

            User assignedTo = quest.getAssignedTo();
            if (assignedTo != null)
            {
                assignedTo.experience_points += claimedPoints;
            }
        }
    }
}
