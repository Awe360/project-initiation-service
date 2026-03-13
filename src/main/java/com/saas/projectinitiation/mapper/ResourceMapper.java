package com.saas.projectinitiation.mapper;

import com.saas.projectinitiation.dto.eventDto.ResourceEvent;
import com.saas.projectinitiation.dto.response.ResourceResponse;
import com.saas.projectinitiation.enums.ResourceStatus;
import com.saas.projectinitiation.model.Resource;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class ResourceMapper {

    public Resource mapToEntity(String requestName,
                                 String roleName,
                                 ResourceEvent eventResponse) {

        Resource resource = new Resource();
        resource.setResourceName(requestName);
        resource.setTenantId(eventResponse.getTenantId());
        resource.setTenantAbbreviatedName(eventResponse.getTenantAbbreviatedName());
        resource.setDescription("");
        resource.setCreatedBy(eventResponse.getCreatedBy());

        Set<String> roles = new HashSet<>();
        String adminRole = "admin";
        roles.add(adminRole);
        if (roleName != null) {
            roles.add(roleName);
        }
        resource.setRequiredRoles(roles);
        resource.setStatus(ResourceStatus.ACTIVE);

        return resource;
    }

    public ResourceResponse mapToDto(Resource resource) {

        ResourceResponse response = new ResourceResponse();
        response.setId(resource.getId());
        response.setResourceName(resource.getResourceName());
        response.setDescription(resource.getDescription());
        response.setStatus(resource.getStatus().name());
        response.setTenantId(resource.getTenantId());
        response.setCreatedAt(resource.getCreatedAt());
        response.setCreatedBy(resource.getCreatedBy());
        response.setUpdatedAt(resource.getUpdatedAt());
        response.setUpdatedBy(resource.getUpdatedBy());

        if (resource.getRequiredRoles() == null || resource.getRequiredRoles().isEmpty()) {
            response.setRequiredRoles(Collections.singleton("Not Assigned"));
        } else {
            response.setRequiredRoles(resource.getRequiredRoles());
        }

        return response;
    }
}
