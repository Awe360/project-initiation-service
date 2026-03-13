package com.saas.projectinitiation.mapper;

import com.saas.projectinitiation.dto.request.RiskCategoryRequest;
import com.saas.projectinitiation.dto.response.RiskCategoryResponse;
import com.saas.projectinitiation.model.RiskCategory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RiskCategoryMapper {

    public RiskCategory mapToEntity(RiskCategoryRequest request, UUID tenantId) {
        RiskCategory riskCategory = new RiskCategory();
        riskCategory.setTypeName(request.getTypeName());
        riskCategory.setDescription(request.getDescription());
        riskCategory.setTenantId(tenantId);
        return riskCategory;
    }

    public RiskCategoryResponse mapToDto(RiskCategory riskCategory) {
        RiskCategoryResponse response = new RiskCategoryResponse();
        response.setId(riskCategory.getId());
        response.setTypeName(riskCategory.getTypeName());
        response.setDescription(riskCategory.getDescription());
        response.setTenantId(riskCategory.getTenantId());
        response.setCreatedAt(riskCategory.getCreatedAt());
        response.setCreatedBy(riskCategory.getCreatedBy());
        response.setUpdatedAt(riskCategory.getUpdatedAt());
        response.setUpdatedBy(riskCategory.getUpdatedBy());
        return response;
    }
}

