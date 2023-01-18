package com.intershop.hackathon.gamification.ado;

import java.util.function.Function;

import javax.inject.Singleton;

import org.azd.workitemtracking.types.WorkItem;

@Singleton
public class TopicMapper implements Function<WorkItem, String>
{
    @Override public String apply(WorkItem workItem)
    {
        if (workItem == null)
        {
            return null;
        }
        
        String itPath = workItem.getFields().getSystemIterationPath();
        String[] pathSegments = itPath.split("\\\\");
        return pathSegments.length > 1  ? pathSegments[1] : itPath;
    }
}
