package com.saas.projectinitiation.utility;

import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.model.*;
import com.saas.projectinitiation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ValidationUtil {

    private final PortfolioRepository portfolioRepository;
    private final ProgramRepository programRepository;
    private final MainProjectRepository mainProjectRepository;
    private final RiskCategoryRepository riskCategoryRepository;
    private final ProjectRiskRepository projectRiskRepository;
    private final ContractTypeRepository contractTypeRepository;
    private final TemplateNameRepository templateNameRepository;



    public ProjectRisk findProjectRiskByTenantIdAndId(UUID tenantId, UUID id) {
        return projectRiskRepository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Project risk not found with id '" + id + "'"));
    }


    public Portfolio findPortfolioById(UUID tenantId, UUID portfolioId) {
        return portfolioRepository.findByTenantIdAndId(tenantId, portfolioId)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found with id '" + portfolioId + "'"));
    }

    public Program findProgramById(UUID tenantId, UUID programId) {
        return programRepository.findByTenantIdAndId(tenantId, programId)
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with id '" + programId + "'"));
    }

    public MainProject findProjectById(UUID tenantId, UUID projectId) {
        return mainProjectRepository.findByTenantIdAndId(tenantId, projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id '" + projectId + "'"));
    }

    public RiskCategory findRiskCategoryById(UUID tenantId, UUID riskCategoryId) {
        return riskCategoryRepository.findByTenantIdAndId(tenantId, riskCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Risk category not found with id '" + riskCategoryId + "'"));
    }

    public ContractType findContractTypeById(UUID tenantId, UUID contractTypeId) {
        return contractTypeRepository.findByTenantIdAndId(tenantId, contractTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract type not found with id '" + contractTypeId + "'"));
    }

    public TemplateName findTemplateNameById(UUID tenantId, UUID id) {
        return templateNameRepository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Template name not found"));
    }


}