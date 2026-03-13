package com.saas.projectinitiation.mapper;

import com.saas.projectinitiation.dto.request.ProjectTypeRequest;
import com.saas.projectinitiation.dto.response.ProjectTypeResponse;
import com.saas.projectinitiation.model.ProjectType;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProjectTypeMapper {

    public ProjectType mapToEntity(ProjectTypeRequest request, UUID tenantId) {
        ProjectType projectType = new ProjectType();
        projectType.setProjectType(request.getProjectType());
        projectType.setTenantId(tenantId);
        return projectType;
    }

    public ProjectTypeResponse mapToDto(ProjectType projectType) {
        ProjectTypeResponse response = new ProjectTypeResponse();
        response.setId(projectType.getId());
        response.setProjectType(projectType.getProjectType());
        response.setTenantId(projectType.getTenantId());
        response.setCreatedAt(projectType.getCreatedAt());
        response.setCreatedBy(projectType.getCreatedBy());
        response.setUpdatedAt(projectType.getUpdatedAt());
        response.setUpdatedBy(projectType.getUpdatedBy());
        return response;
    }
}

