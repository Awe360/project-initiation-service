package com.saas.projectinitiation.service;

import com.saas.projectinitiation.dto.request.ProgramRequest;
import com.saas.projectinitiation.dto.response.ProgramResponse;
import com.saas.projectinitiation.enums.ProgramStatus;
import com.saas.projectinitiation.exception.ResourceExistsException;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.mapper.ProgramMapper;
import com.saas.projectinitiation.model.Portfolio;
import com.saas.projectinitiation.model.Program;
import com.saas.projectinitiation.repository.PortfolioRepository;
import com.saas.projectinitiation.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProgramService {

    private final ProgramRepository repository;
    private final PortfolioRepository portfolioRepository;
    private final ProgramMapper mapper;

    public ProgramResponse create(UUID tenantId, ProgramRequest request) {
        if (repository.existsByTenantIdAndName(tenantId, request.getName())) {
            throw new ResourceExistsException("Program with name '" + request.getName() + "' already exists");
        }
        
        Portfolio portfolio = portfolioRepository.findByTenantIdAndId(tenantId, request.getPortfolioId())
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found"));

        Program entity = mapper.toEntity(tenantId, request, portfolio);
        entity.setStatus(ProgramStatus.ACTIVE);
        Program savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }

    public List<ProgramResponse> getAll(UUID tenantId) {
        return repository.findByTenantId(tenantId).stream()
                .map(mapper::toResponse)
                .toList();
    }


    public List<ProgramResponse> getAllByPortfolio(UUID tenantId, UUID portfolioId) {
        return repository.findByTenantIdAndPortfolioId(tenantId, portfolioId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ProgramResponse getById(UUID tenantId, UUID id) {
        Program entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Program not found"));
        return mapper.toResponse(entity);
    }

    public ProgramResponse update(UUID tenantId, UUID id, ProgramRequest request) {
        Program entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Program not found"));

        if (!entity.getName().equals(request.getName()) &&
                repository.existsByTenantIdAndName(tenantId, request.getName())) {
            throw new ResourceExistsException("Program with name '" + request.getName() + "' already exists");
        }

        Portfolio portfolio = portfolioRepository.findByTenantIdAndId(tenantId, request.getPortfolioId())
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found"));

        entity.setName(request.getName());
        entity.setRemark(request.getRemark());
        entity.setPortfolio(portfolio);
        Program savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }

    public void delete(UUID tenantId, UUID id) {
        Program entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Program not found"));
        repository.delete(entity);
    }
}