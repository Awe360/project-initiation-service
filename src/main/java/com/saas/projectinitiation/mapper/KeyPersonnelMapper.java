package com.saas.projectinitiation.mapper;

import com.saas.projectinitiation.dto.request.KeyPersonnelRequest;
import com.saas.projectinitiation.dto.response.KeyPersonnelResponse;
import com.saas.projectinitiation.model.KeyPersonnel;
import com.saas.projectinitiation.model.MainProject;
import com.saas.projectinitiation.model.Portfolio;
import com.saas.projectinitiation.model.Program;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class KeyPersonnelMapper {

    public KeyPersonnel mapToEntity(KeyPersonnelRequest request, UUID tenantId, 
                                    Portfolio portfolio, Program program, MainProject project) {
        KeyPersonnel keyPersonnel = new KeyPersonnel();
        keyPersonnel.setPortfolio(portfolio);
        keyPersonnel.setProgram(program);
        keyPersonnel.setProject(project);
        keyPersonnel.setStakeholderId(request.getStakeholderId());
        keyPersonnel.setTenantId(tenantId);
        return keyPersonnel;
    }

    public KeyPersonnelResponse mapToDto(KeyPersonnel keyPersonnel) {
        KeyPersonnelResponse response = new KeyPersonnelResponse();
        response.setId(keyPersonnel.getId());
        response.setPortfolioId(keyPersonnel.getPortfolioId());
        response.setPortfolioName(keyPersonnel.getPortfolio() != null ? keyPersonnel.getPortfolio().getName() : null);
        response.setProgramId(keyPersonnel.getProgramId());
        response.setProgramName(keyPersonnel.getProgram() != null ? keyPersonnel.getProgram().getName() : null);
        response.setProjectId(keyPersonnel.getProjectId());
        response.setProjectName(keyPersonnel.getProject() != null ? keyPersonnel.getProject().getProjectName() : null);
        response.setStakeholderId(keyPersonnel.getStakeholderId());
        response.setTenantId(keyPersonnel.getTenantId());
        response.setCreatedAt(keyPersonnel.getCreatedAt());
        response.setCreatedBy(keyPersonnel.getCreatedBy());
        response.setUpdatedAt(keyPersonnel.getUpdatedAt());
        response.setUpdatedBy(keyPersonnel.getUpdatedBy());
        return response;
    }
}

