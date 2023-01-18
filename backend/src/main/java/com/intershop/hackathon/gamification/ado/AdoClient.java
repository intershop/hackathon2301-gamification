package com.intershop.hackathon.gamification.ado;

import java.util.*;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.azd.exceptions.AzDException;
import org.azd.interfaces.WorkItemTrackingDetails;
import org.azd.utils.AzDClientApi;
import org.azd.workitemtracking.types.WorkItem;
import org.azd.workitemtracking.types.WorkItemList;
import org.azd.workitemtracking.types.WorkItemQueryResult;
import org.azd.workitemtracking.types.WorkItemReference;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.intershop.hackathon.gamification.User;

@ApplicationScoped
public class AdoClient
{
    @ConfigProperty(name = "intershop.ado.organization")
    String organisation;
    @ConfigProperty(name = "intershop.ado.token")
    String personalAccessToken; // base64 encoded - decode before use;
    @ConfigProperty(name = "intershop.ado.project")
    String project;

    public AzDClientApi getAdoClient()
    {
        return new AzDClientApi(organisation, project, getPersonalAccessToken());
    }

    public Collection<WorkItem> getWorkItems()
    {
        var work = getAdoClient().getWorkItemTrackingApi();
        try {
            WorkItemQueryResult queryResult = work.queryByWiql("",
                            "Select [System.Id], [System.Title], [System.State] From WorkItems Where [System.WorkItemType] = 'Bug' " + "AND [State] = 'New' order by [Microsoft.VSTS.Common.Priority] asc, [System.CreatedDate] desc");

            int[] wiIDs = queryResult.getWorkItems()
                                     .stream()
                                     .limit(10) // TODO handle amount of items dynamically
                                     .mapToInt(w -> w.getId())
                                     .toArray();

            List<WorkItem> workItems = work.getWorkItems(wiIDs).getWorkItems();
            return workItems;
        }
        catch (AzDException e)
        {
            // TODO error handling
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public Optional<WorkItem> getWorkItem(int ticketNumber)
    {
        var work = getAdoClient().getWorkItemTrackingApi();
        try {
            return Optional.of(work.getWorkItem(ticketNumber));
        }
        catch (AzDException e)
        {
            // TODO error handling
            e.printStackTrace();
        }

        return Optional.empty();
    }


    protected String getOrganization()
    {
        return organisation;
    }

    protected String getPersonalAccessToken()
    {
        return new String(Base64.getDecoder().decode(personalAccessToken));
    }

    protected String getProject()
    {
        return project;
    }

    public WorkItem postWorkItem(String email, int workItemID, WorkItem workItemChanged)
    {
        WorkItem workItem = null;
        var work = getAdoClient().getWorkItemTrackingApi();
        try {
            var fieldsToUpdate = new HashMap<String, Object>(){{
                /*put("System.AssignedTo", "test@xmail.com");
                put("System.AreaPath", "you-team-area-path");*/
                put("System.State", workItemChanged.getFields().getSystemState()); // New, Active, OnHold
            }};

            work.updateWorkItem(workItemID, fieldsToUpdate);

            workItem = work.getWorkItem(workItemID);

        }
        catch (AzDException e)
        {
            // TODO error handling
            e.printStackTrace();
        }
        return workItem;
    }
}
