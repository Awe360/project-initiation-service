package com.saas.projectinitiation.mapper;

import com.saas.projectinitiation.dto.request.AssignPriorityRequest;
import com.saas.projectinitiation.dto.response.AssignPriorityResponse;
import com.saas.projectinitiation.model.AssignPriority;
import com.saas.projectinitiation.model.MainProject;
import com.saas.projectinitiation.model.Portfolio;
import com.saas.projectinitiation.model.Program;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AssignPriorityMapper {

    public AssignPriority mapToEntity(AssignPriorityRequest request, UUID tenantId, 
                                     Portfolio portfolio, Program program, MainProject project) {
        AssignPriority assignPriority = new AssignPriority();
        assignPriority.setPortfolio(portfolio);
        assignPriority.setProgram(program);
        assignPriority.setProject(project);
        assignPriority.setProjectEstimateCost(request.getProjectEstimateCost());
        assignPriority.setProjectDuration(request.getProjectDuration());
        assignPriority.setCurrentPhase(request.getCurrentPhase());
        assignPriority.setPriority(request.getPriority());
        assignPriority.setRemark(request.getRemark());
        assignPriority.setTenantId(tenantId);
        return assignPriority;
    }

    public AssignPriorityResponse mapToDto(AssignPriority assignPriority) {
        AssignPriorityResponse response = new AssignPriorityResponse();
        response.setId(assignPriority.getId());
        response.setPortfolioId(assignPriority.getPortfolioId());
        response.setPortfolioName(assignPriority.getPortfolio() != null ? assignPriority.getPortfolio().getName() : null);
        response.setProgramId(assignPriority.getProgramId());
        response.setProgramName(assignPriority.getProgram() != null ? assignPriority.getProgram().getName() : null);
        response.setProjectId(assignPriority.getProjectId());
        response.setProjectName(assignPriority.getProject() != null ? assignPriority.getProject().getProjectName() : null);
        response.setProjectEstimateCost(assignPriority.getProjectEstimateCost());
        response.setProjectDuration(assignPriority.getProjectDuration());
        response.setCurrentPhase(assignPriority.getCurrentPhase());
        response.setPriority(assignPriority.getPriority());
        response.setRemark(assignPriority.getRemark());
        response.setTenantId(assignPriority.getTenantId());
        response.setCreatedAt(assignPriority.getCreatedAt());
        response.setCreatedBy(assignPriority.getCreatedBy());
        response.setUpdatedAt(assignPriority.getUpdatedAt());
        response.setUpdatedBy(assignPriority.getUpdatedBy());
        return response;
    }
}

