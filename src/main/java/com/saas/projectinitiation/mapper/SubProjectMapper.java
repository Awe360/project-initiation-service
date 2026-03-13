package com.saas.projectinitiation.mapper;

import com.saas.projectinitiation.dto.request.SubProjectRequest;
import com.saas.projectinitiation.dto.response.SubProjectResponse;
import com.saas.projectinitiation.model.MainProject;
import com.saas.projectinitiation.model.ProjectType;
import com.saas.projectinitiation.model.SubProject;
import com.saas.projectinitiation.utility.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SubProjectMapper {

    private final SecurityUtil securityUtil;

    public SubProject toEntity(UUID tenantId, SubProjectRequest.SubProjectItem item,
                               MainProject project, ProjectType projectType) {
        SubProject entity = new SubProject();
        entity.setTenantId(tenantId);
        entity.setSubProjectName(item.getSubProjectName());
        entity.setProject(project);
        entity.setProjectId(project.getId());
        entity.setProjectType(projectType);
        entity.setProjectTypeId(projectType.getId());
        entity.setStartDate(item.getStartDate());
        entity.setEndDate(item.getEndDate());
        entity.setLocation(item.getLocation());
        entity.setLocalEstimatedCost(item.getLocalEstimatedCost());
        entity.setForeignEstimatedCost(item.getForeignEstimatedCost());
        entity.setEstimatedCost(item.getEstimatedCost());
        entity.setDescription(item.getDescription());
        return entity;
    }

    public void updateEntity(SubProject entity, SubProjectRequest.SubProjectItem item) {
        entity.setSubProjectName(item.getSubProjectName());
        entity.setProjectId(item.getProjectId());
        entity.setProjectTypeId(item.getProjectTypeId());
        entity.setStartDate(item.getStartDate());
        entity.setEndDate(item.getEndDate());
        entity.setLocation(item.getLocation());
        entity.setLocalEstimatedCost(item.getLocalEstimatedCost());
        entity.setForeignEstimatedCost(item.getForeignEstimatedCost());
        entity.setEstimatedCost(item.getEstimatedCost());
        entity.setDescription(item.getDescription());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setUpdatedBy(securityUtil.getName());
    }

    public SubProjectResponse toResponse(SubProject entity) {
        SubProjectResponse res = new SubProjectResponse();
        res.setId(entity.getId());
        res.setTenantId(entity.getTenantId());
        res.setCreatedAt(entity.getCreatedAt());
        res.setUpdatedAt(entity.getUpdatedAt());
        res.setCreatedBy(entity.getCreatedBy());
        res.setUpdatedBy(entity.getUpdatedBy());

        res.setSubProjectName(entity.getSubProjectName());
        res.setProjectId(entity.getProjectId());
        res.setProjectTypeId(entity.getProjectTypeId());
        res.setStartDate(entity.getStartDate());
        res.setEndDate(entity.getEndDate());
        res.setLocation(entity.getLocation());
        res.setLocalEstimatedCost(entity.getLocalEstimatedCost());
        res.setForeignEstimatedCost(entity.getForeignEstimatedCost());
        res.setEstimatedCost(entity.getEstimatedCost());
        res.setDescription(entity.getDescription());
        return res;
    }
}