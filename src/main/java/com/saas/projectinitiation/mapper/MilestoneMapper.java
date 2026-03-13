
package com.saas.projectinitiation.mapper;

import com.saas.projectinitiation.dto.request.MilestoneRequest;
import com.saas.projectinitiation.dto.response.MilestoneResponse;
import com.saas.projectinitiation.model.MainProject;
import com.saas.projectinitiation.model.Milestone;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MilestoneMapper {

    public Milestone toEntity(UUID tenantId, MilestoneRequest request, MainProject project) {
        Milestone entity = new Milestone();
        entity.setTenantId(tenantId);
        entity.setMilestoneName(request.getMilestoneName());
        entity.setDescription(request.getDescription());
        entity.setProject(project);
        entity.setSubProjectId(request.getSubProjectId());
        return entity;
    }

    public MilestoneResponse toResponse(Milestone entity) {
        MilestoneResponse response = new MilestoneResponse();
        response.setId(entity.getId());
        response.setTenantId(entity.getTenantId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        response.setCreatedBy(entity.getCreatedBy());
        response.setUpdatedBy(entity.getUpdatedBy());
        response.setMilestoneName(entity.getMilestoneName());
        response.setDescription(entity.getDescription());
        response.setSubProjectId(entity.getSubProjectId());
        response.setProjectId(entity.getProject().getId());
        response.setProjectName(entity.getProject().getProjectName());
        response.setCopiedFromMilestoneId(entity.getCopiedFromMilestoneId());
        return response;
    }
}