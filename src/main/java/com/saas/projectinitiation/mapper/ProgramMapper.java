package com.saas.projectinitiation.mapper;

import com.saas.projectinitiation.dto.request.ProgramRequest;
import com.saas.projectinitiation.dto.response.ProgramResponse;
import com.saas.projectinitiation.model.Portfolio;
import com.saas.projectinitiation.model.Program;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProgramMapper {

    public Program toEntity(UUID tenantId, ProgramRequest request, Portfolio portfolio) {
        Program entity = new Program();
        entity.setTenantId(tenantId);
        entity.setName(request.getName());
        entity.setRemark(request.getRemark());
        entity.setPortfolio(portfolio);
        return entity;
    }

    public ProgramResponse toResponse(Program entity) {
        ProgramResponse response = new ProgramResponse();
        response.setId(entity.getId());
        response.setTenantId(entity.getTenantId());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        response.setCreatedBy(entity.getCreatedBy());
        response.setUpdatedBy(entity.getUpdatedBy());
        response.setName(entity.getName());
        response.setPortfolioId(entity.getPortfolioId());
        response.setPortfolioName(entity.getPortfolio() != null ? entity.getPortfolio().getName() : null);
        response.setRemark(entity.getRemark());
        response.setStatus(entity.getStatus() != null ? entity.getStatus().name() : null);
        return response;
    }
}

