package com.saas.projectinitiation.mapper;

import com.saas.projectinitiation.dto.request.PortfolioRequest;
import com.saas.projectinitiation.dto.response.PortfolioResponse;
import com.saas.projectinitiation.model.Portfolio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PortfolioMapper {

    public Portfolio toEntity(UUID tenantId, PortfolioRequest request) {
        Portfolio entity = new Portfolio();
        entity.setTenantId(tenantId);
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        return entity;
    }

    public PortfolioResponse toResponse(Portfolio entity) {
        PortfolioResponse response = new PortfolioResponse();
        response.setId(entity.getId());
        response.setTenantId(entity.getTenantId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        response.setCreatedBy(entity.getCreatedBy());
        response.setUpdatedBy(entity.getUpdatedBy());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setStatus(entity.getStatus() != null ? entity.getStatus().name() : null);
        return response;
    }
}

