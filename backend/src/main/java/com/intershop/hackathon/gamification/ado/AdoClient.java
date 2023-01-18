package com.intershop.hackathon.gamification.ado;

import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.azd.exceptions.AzDException;
import org.azd.interfaces.WorkItemTrackingDetails;
import org.azd.utils.AzDClientApi;
import org.azd.workitemtracking.types.WorkItem;
import org.azd.workitemtracking.types.WorkItemList;
import org.azd.workitemtracking.types.WorkItemQueryResult;
import org.azd.workitemtracking.types.WorkItemReference;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.intershop.hackathon.gamification.User;
import com.intershop.hackathon.gamification.model.Quest;

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
                            "SELECT [System.Id] FROM workitems WHERE [System.TeamProject] = @project " +
//                                  "AND [System.WorkItemType] = 'Bug' AND [System.State] = 'New' " +
                                  "AND [System.Tags] CONTAINS 'hackathon-2023' " +
                                  "order by [Microsoft.VSTS.Common.Priority] asc, [System.CreatedDate] desc");

            int[] wiIDs = queryResult.getWorkItems()
                                     .stream()
//                                     .limit(10) // TODO handle amount of items dynamically
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

    public Optional<WorkItem> getWorkItem(String ticketNumber)
    {
        var work = getAdoClient().getWorkItemTrackingApi();
        try {
            return Optional.of(work.getWorkItem(Integer.parseInt(ticketNumber)));
        }
        catch (AzDException e)
        {
            // TODO error handling
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<WorkItem> updateWorkItem(String id, User user)
    {
        var work = getAdoClient().getWorkItemTrackingApi();
        try {
            Optional<WorkItem> wi = getWorkItem(id);

            if (wi.isPresent())
            {
                var workItem = wi.get();
                var fieldsToUpdate = new HashMap<String, Object>(){{
                    put("System.AssignedTo", user.email);
                    put("System.State", "Active");
                }};
                workItem = work.updateWorkItem(Integer.parseInt(id), fieldsToUpdate);

                return Optional.of(workItem);
            }
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

}
