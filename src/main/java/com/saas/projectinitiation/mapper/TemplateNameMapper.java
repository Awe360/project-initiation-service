package com.saas.projectinitiation.mapper;

import com.saas.projectinitiation.dto.request.TemplateNameRequest;
import com.saas.projectinitiation.dto.response.TemplateNameResponse;
import com.saas.projectinitiation.model.TemplateName;
import com.saas.projectinitiation.utility.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TemplateNameMapper {

    private final SecurityUtil securityUtil;

    public TemplateName toEntity(UUID tenantId, TemplateNameRequest request) {
        TemplateName entity = new TemplateName();
        entity.setTenantId(tenantId);
        entity.setName(request.getName().trim());
        entity.setDescription(request.getDescription());
        return entity;
    }

    public void updateEntityFromRequest(TemplateName entity, TemplateNameRequest request) {
        entity.setName(request.getName().trim());
        entity.setDescription(request.getDescription());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setUpdatedBy(securityUtil.getName());
    }

    public TemplateNameResponse toResponse(TemplateName entity) {
        TemplateNameResponse response = new TemplateNameResponse();
        response.setId(entity.getId());
        response.setTenantId(entity.getTenantId());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        response.setCreatedBy(entity.getCreatedBy());
        response.setUpdatedBy(entity.getUpdatedBy());
        return response;
    }
}