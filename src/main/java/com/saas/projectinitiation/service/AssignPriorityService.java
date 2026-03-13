package com.saas.projectinitiation.service;

import com.saas.projectinitiation.dto.request.AssignPriorityRequest;
import com.saas.projectinitiation.dto.response.AssignPriorityResponse;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.mapper.AssignPriorityMapper;
import com.saas.projectinitiation.model.AssignPriority;
import com.saas.projectinitiation.model.MainProject;
import com.saas.projectinitiation.model.Portfolio;
import com.saas.projectinitiation.model.Program;
import com.saas.projectinitiation.repository.AssignPriorityRepository;
import com.saas.projectinitiation.repository.MainProjectRepository;
import com.saas.projectinitiation.repository.PortfolioRepository;
import com.saas.projectinitiation.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssignPriorityService {

    private final AssignPriorityRepository assignPriorityRepository;
    private final PortfolioRepository portfolioRepository;
    private final ProgramRepository programRepository;
    private final MainProjectRepository projectRepository;
    private final AssignPriorityMapper assignPriorityMapper;

    @Transactional
    public AssignPriorityResponse createAssignPriority(UUID tenantId, AssignPriorityRequest request) {
        Portfolio portfolio = portfolioRepository.findById(request.getPortfolioId())
                .filter(p -> p.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found with id '" + request.getPortfolioId() + "'"));

        Program program = null;
        if (request.getProgramId() != null) {
            program = programRepository.findById(request.getProgramId())
                    .filter(p -> p.getTenantId().equals(tenantId))
                    .orElseThrow(() -> new ResourceNotFoundException("Program not found with id '" + request.getProgramId() + "'"));
        }

        MainProject project = projectRepository.findById(request.getProjectId())
                .filter(p -> p.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id '" + request.getProjectId() + "'"));

        AssignPriority assignPriority = assignPriorityMapper.mapToEntity(request, tenantId, portfolio, program, project);
        assignPriority = assignPriorityRepository.save(assignPriority);
        return assignPriorityMapper.mapToDto(assignPriority);
    }

    public List<AssignPriorityResponse> getAllAssignPriorities(UUID tenantId) {
        return assignPriorityRepository.findByTenantId(tenantId).stream()
                .map(assignPriorityMapper::mapToDto)
                .toList();
    }

    public AssignPriorityResponse getAssignPriorityById(UUID tenantId, UUID id) {
        AssignPriority assignPriority = assignPriorityRepository.findById(id)
                .filter(ap -> ap.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("AssignPriority not found with id '" + id + "'"));
        return assignPriorityMapper.mapToDto(assignPriority);
    }

    @Transactional
    public AssignPriorityResponse updateAssignPriority(UUID tenantId, UUID id, AssignPriorityRequest request) {
        AssignPriority assignPriority = assignPriorityRepository.findById(id)
                .filter(ap -> ap.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("AssignPriority not found with id '" + id + "'"));

        Portfolio portfolio = portfolioRepository.findById(request.getPortfolioId())
                .filter(p -> p.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found with id '" + request.getPortfolioId() + "'"));

        Program program = null;
        if (request.getProgramId() != null) {
            program = programRepository.findById(request.getProgramId())
                    .filter(p -> p.getTenantId().equals(tenantId))
                    .orElseThrow(() -> new ResourceNotFoundException("Program not found with id '" + request.getProgramId() + "'"));
        }

        MainProject project = projectRepository.findById(request.getProjectId())
                .filter(p -> p.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id '" + request.getProjectId() + "'"));

        assignPriority.setPortfolio(portfolio);
        assignPriority.setProgram(program);
        assignPriority.setProject(project);
        assignPriority.setProjectEstimateCost(request.getProjectEstimateCost());
        assignPriority.setProjectDuration(request.getProjectDuration());
        assignPriority.setCurrentPhase(request.getCurrentPhase());
        assignPriority.setPriority(request.getPriority());
        assignPriority.setRemark(request.getRemark());

        assignPriority = assignPriorityRepository.save(assignPriority);
        return assignPriorityMapper.mapToDto(assignPriority);
    }

    @Transactional
    public void deleteAssignPriority(UUID tenantId, UUID id) {
        AssignPriority assignPriority = assignPriorityRepository.findById(id)
                .filter(ap -> ap.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("AssignPriority not found with id '" + id + "'"));
        assignPriorityRepository.delete(assignPriority);
    }
}


