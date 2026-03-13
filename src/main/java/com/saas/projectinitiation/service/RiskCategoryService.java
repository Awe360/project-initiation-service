package com.saas.projectinitiation.service;

import com.saas.projectinitiation.dto.request.RiskCategoryRequest;
import com.saas.projectinitiation.dto.response.RiskCategoryResponse;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.mapper.RiskCategoryMapper;
import com.saas.projectinitiation.model.RiskCategory;
import com.saas.projectinitiation.repository.RiskCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RiskCategoryService {

    private final RiskCategoryRepository riskCategoryRepository;
    private final RiskCategoryMapper riskCategoryMapper;

    @Transactional
    public RiskCategoryResponse createRiskCategory(UUID tenantId, RiskCategoryRequest request) {
        RiskCategory riskCategory = riskCategoryMapper.mapToEntity(request, tenantId);
        riskCategory = riskCategoryRepository.save(riskCategory);
        return riskCategoryMapper.mapToDto(riskCategory);
    }

    public List<RiskCategoryResponse> getAllRiskCategories(UUID tenantId) {
        return riskCategoryRepository.findByTenantId(tenantId).stream()
                .map(riskCategoryMapper::mapToDto)
                .toList();
    }

    public RiskCategoryResponse getRiskCategoryById(UUID tenantId, UUID id) {
        RiskCategory riskCategory = riskCategoryRepository.findById(id)
                .filter(rc -> rc.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Risk category not found with id '" + id + "'"));
        return riskCategoryMapper.mapToDto(riskCategory);
    }

    @Transactional
    public RiskCategoryResponse updateRiskCategory(UUID tenantId, UUID id, RiskCategoryRequest request) {
        RiskCategory riskCategory = riskCategoryRepository.findById(id)
                .filter(rc -> rc.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Risk category not found with id '" + id + "'"));

        riskCategory.setTypeName(request.getTypeName());
        riskCategory.setDescription(request.getDescription());

        riskCategory = riskCategoryRepository.save(riskCategory);
        return riskCategoryMapper.mapToDto(riskCategory);
    }

    @Transactional
    public void deleteRiskCategory(UUID tenantId, UUID id) {
        RiskCategory riskCategory = riskCategoryRepository.findById(id)
                .filter(rc -> rc.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Risk category not found with id '" + id + "'"));
        riskCategoryRepository.delete(riskCategory);
    }
}





