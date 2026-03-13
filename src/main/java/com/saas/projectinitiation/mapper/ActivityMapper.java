package com.saas.projectinitiation.mapper;

import com.saas.projectinitiation.dto.request.ActivityRequest;
import com.saas.projectinitiation.dto.response.ActivityResponse;
import com.saas.projectinitiation.model.Activity;
import com.saas.projectinitiation.model.MainProject;
import com.saas.projectinitiation.model.Milestone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ActivityMapper {

    public Activity toEntity(UUID tenantId, ActivityRequest request, Milestone milestone, MainProject project) {
        Activity entity = new Activity();
        entity.setTenantId(tenantId);
        entity.setName(request.getName());
        entity.setMilestone(milestone);
        entity.setProject(project);
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setDescription(request.getDescription());
        entity.setIsSubActivity(request.getIsSubActivity() != null ? request.getIsSubActivity() : false);
        return entity;
    }

    public ActivityResponse toResponse(Activity entity) {
        ActivityResponse response = new ActivityResponse();
        response.setId(entity.getId());
        response.setTenantId(entity.getTenantId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        response.setCreatedBy(entity.getCreatedBy());
        response.setUpdatedBy(entity.getUpdatedBy());
        response.setName(entity.getName());
        response.setMilestoneId(entity.getMilestoneId());
        response.setMilestoneName(entity.getMilestone() != null ? entity.getMilestone().getMilestoneName() : null);
        response.setProjectId(entity.getProjectId());
        response.setProjectName(entity.getProject() != null ? entity.getProject().getProjectName() : null);
        response.setStartDate(entity.getStartDate());
        response.setEndDate(entity.getEndDate());
        response.setDuration(entity.getDuration());
        response.setDescription(entity.getDescription());
        response.setIsSubActivity(entity.getIsSubActivity());
        response.setParentActivityId(entity.getParentActivityId());
        return response;
    }
}

