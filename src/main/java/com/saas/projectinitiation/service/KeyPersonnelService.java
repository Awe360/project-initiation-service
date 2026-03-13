package com.saas.projectinitiation.service;

import com.saas.projectinitiation.dto.request.KeyPersonnelRequest;
import com.saas.projectinitiation.dto.response.KeyPersonnelResponse;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.mapper.KeyPersonnelMapper;
import com.saas.projectinitiation.model.KeyPersonnel;
import com.saas.projectinitiation.model.MainProject;
import com.saas.projectinitiation.model.Portfolio;
import com.saas.projectinitiation.model.Program;
import com.saas.projectinitiation.repository.KeyPersonnelRepository;
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
public class KeyPersonnelService {

    private final KeyPersonnelRepository keyPersonnelRepository;
    private final PortfolioRepository portfolioRepository;
    private final ProgramRepository programRepository;
    private final MainProjectRepository projectRepository;
    private final KeyPersonnelMapper keyPersonnelMapper;

    @Transactional
    public KeyPersonnelResponse createKeyPersonnel(UUID tenantId, KeyPersonnelRequest request) {
        Portfolio portfolio = portfolioRepository.findById(request.getPortfolioId())
                .filter(p -> p.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found with id '" + request.getPortfolioId() + "'"));

        Program program = programRepository.findById(request.getProgramId())
                .filter(p -> p.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with id '" + request.getProgramId() + "'"));

        MainProject project = projectRepository.findById(request.getProjectId())
                .filter(p -> p.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id '" + request.getProjectId() + "'"));

        KeyPersonnel keyPersonnel = keyPersonnelMapper.mapToEntity(request, tenantId, portfolio, program, project);
        keyPersonnel = keyPersonnelRepository.save(keyPersonnel);
        return keyPersonnelMapper.mapToDto(keyPersonnel);
    }

    public List<KeyPersonnelResponse> getAllKeyPersonnel(UUID tenantId) {
        return keyPersonnelRepository.findByTenantId(tenantId).stream()
                .map(keyPersonnelMapper::mapToDto)
                .toList();
    }

    public KeyPersonnelResponse getKeyPersonnelById(UUID tenantId, UUID id) {
        KeyPersonnel keyPersonnel = keyPersonnelRepository.findById(id)
                .filter(kp -> kp.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("KeyPersonnel not found with id '" + id + "'"));
        return keyPersonnelMapper.mapToDto(keyPersonnel);
    }

    @Transactional
    public KeyPersonnelResponse updateKeyPersonnel(UUID tenantId, UUID id, KeyPersonnelRequest request) {
        KeyPersonnel keyPersonnel = keyPersonnelRepository.findById(id)
                .filter(kp -> kp.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("KeyPersonnel not found with id '" + id + "'"));

        Portfolio portfolio = portfolioRepository.findById(request.getPortfolioId())
                .filter(p -> p.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found with id '" + request.getPortfolioId() + "'"));

        Program program = programRepository.findById(request.getProgramId())
                .filter(p -> p.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with id '" + request.getProgramId() + "'"));

        MainProject project = projectRepository.findById(request.getProjectId())
                .filter(p -> p.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id '" + request.getProjectId() + "'"));

        keyPersonnel.setPortfolio(portfolio);
        keyPersonnel.setProgram(program);
        keyPersonnel.setProject(project);
        keyPersonnel.setStakeholderId(request.getStakeholderId());

        keyPersonnel = keyPersonnelRepository.save(keyPersonnel);
        return keyPersonnelMapper.mapToDto(keyPersonnel);
    }

    @Transactional
    public void deleteKeyPersonnel(UUID tenantId, UUID id) {
        KeyPersonnel keyPersonnel = keyPersonnelRepository.findById(id)
                .filter(kp -> kp.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("KeyPersonnel not found with id '" + id + "'"));
        keyPersonnelRepository.delete(keyPersonnel);
    }
}


