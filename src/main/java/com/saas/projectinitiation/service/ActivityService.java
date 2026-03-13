package com.saas.projectinitiation.service;

import com.saas.projectinitiation.dto.request.ActivityRequest;
import com.saas.projectinitiation.dto.request.CopyActivitiesRequest;
import com.saas.projectinitiation.dto.response.ActivityResponse;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.mapper.ActivityMapper;
import com.saas.projectinitiation.model.Activity;
import com.saas.projectinitiation.model.MainProject;
import com.saas.projectinitiation.model.Milestone;
import com.saas.projectinitiation.repository.ActivityRepository;
import com.saas.projectinitiation.repository.MainProjectRepository;
import com.saas.projectinitiation.repository.MilestoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ActivityService {

    private final ActivityRepository repository;
    private final MilestoneRepository milestoneRepository;
    private final MainProjectRepository projectRepository;
    private final ActivityMapper mapper;

    public ActivityResponse create(UUID tenantId, ActivityRequest request) {
        Milestone milestone = milestoneRepository.findByTenantIdAndId(tenantId, request.getMilestoneId())
                .orElseThrow(() -> new ResourceNotFoundException("Milestone not found"));

        MainProject project = projectRepository.findByTenantIdAndId(tenantId, request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("MainProject not found"));

        Activity entity = mapper.toEntity(tenantId, request, milestone, project);
        
        if (Boolean.TRUE.equals(request.getIsSubActivity()) && request.getParentActivityId() != null) {
            Activity parent = repository.findByTenantIdAndId(tenantId, request.getParentActivityId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent activity not found"));
            entity.setParentActivity(parent);
        }

        Activity savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }

    public List<ActivityResponse> getAll(UUID tenantId) {
        return repository.findByTenantId(tenantId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<ActivityResponse> getByProject(UUID tenantId, UUID projectId) {
        return repository.findByTenantIdAndProjectId(tenantId, projectId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<ActivityResponse> getByMilestone(UUID tenantId, UUID milestoneId) {
        return repository.findByTenantIdAndMilestoneId(tenantId, milestoneId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ActivityResponse getById(UUID tenantId, UUID id) {
        Activity entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found"));
        return mapper.toResponse(entity);
    }


    public List<ActivityResponse> copySelected(UUID tenantId, CopyActivitiesRequest request) {
        List<Activity> sourceActivities = repository.findByTenantIdAndProjectIdAndIdIn(
                tenantId, request.getSourceProjectId(), request.getActivityIds());

        List<Activity> mainActivitiesToCopy = sourceActivities.stream()
                .filter(a -> !a.getIsSubActivity())
                .collect(Collectors.toList());

        List<Activity> subActivitiesToCopy = sourceActivities.stream()
                .filter(Activity::getIsSubActivity)
                .collect(Collectors.toList());

        MainProject destProject = projectRepository.findByTenantIdAndId(tenantId, request.getDestinationProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Destination project not found"));
        Milestone destMilestone = milestoneRepository.findByTenantIdAndId(tenantId, request.getDestinationMilestoneId())
                .orElseThrow(() -> new ResourceNotFoundException("Destination milestone not found"));

        Map<UUID, Activity> copiedMainActivitiesMap = new HashMap<>();

        for (Activity originalMain : mainActivitiesToCopy) {
            Activity copy = new Activity();
            copy.setTenantId(tenantId);
            copy.setName(originalMain.getName());
            copy.setStartDate(originalMain.getStartDate());
            copy.setEndDate(originalMain.getEndDate());
            copy.setDuration(originalMain.getDuration());
            copy.setProject(destProject);
            copy.setMilestone(destMilestone);
            copy.setDescription(originalMain.getDescription());
            copy.setIsSubActivity(false);
            copy.setParentActivity(null); // Main activities have no parent
            Activity savedCopy = repository.save(copy);
            copiedMainActivitiesMap.put(originalMain.getId(), savedCopy);
        }

        List<Activity> finalCopiedActivities = new ArrayList<>(copiedMainActivitiesMap.values());
        for (Activity originalSub : subActivitiesToCopy) {
            UUID originalParentId = originalSub.getParentActivity().getId();
            Activity newParent = copiedMainActivitiesMap.get(originalParentId);

            if (newParent != null) {
                Activity copy = new Activity();
                copy.setTenantId(tenantId);
                copy.setName(originalSub.getName());
                copy.setStartDate(originalSub.getStartDate());
                copy.setEndDate(originalSub.getEndDate());
                copy.setDuration(originalSub.getDuration());
                copy.setProject(destProject);
                copy.setMilestone(destMilestone);
                copy.setDescription(originalSub.getDescription());
                copy.setIsSubActivity(true);
                copy.setParentActivity(newParent);
                Activity savedCopy = repository.save(copy);
                finalCopiedActivities.add(savedCopy);
            }
        }

        return finalCopiedActivities.stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    public ActivityResponse update(UUID tenantId, UUID id, ActivityRequest request) {
        Activity entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found"));

        Milestone milestone = milestoneRepository.findByTenantIdAndId(tenantId, request.getMilestoneId())
                .orElseThrow(() -> new ResourceNotFoundException("Milestone not found"));

        MainProject project = projectRepository.findByTenantIdAndId(tenantId, request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("MainProject not found"));

        entity.setName(request.getName());
        entity.setMilestone(milestone);
        entity.setProject(project);
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setDescription(request.getDescription());
        entity.setIsSubActivity(request.getIsSubActivity());

        if (Boolean.TRUE.equals(request.getIsSubActivity()) && request.getParentActivityId() != null) {
            Activity parent = repository.findByTenantIdAndId(tenantId, request.getParentActivityId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent activity not found"));
            entity.setParentActivity(parent);
        } else {
            entity.setParentActivity(null);
        }

        Activity savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }

    @Transactional
    public void updateActivityExecutionStatus(UUID tenantId, UUID activityId, String newStatus) {
        Activity activity = repository.findByTenantIdAndId(tenantId, activityId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Activity not found for tenantId: " + tenantId + " and activityId: " + activityId));
        activity.setExecutionStatus(newStatus);

    }

    public void delete(UUID tenantId, UUID id) {
        Activity entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found"));
        repository.delete(entity);
    }
}


