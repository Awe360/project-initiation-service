package com.saas.projectinitiation.service;

import com.saas.projectinitiation.dto.request.PortfolioRequest;
import com.saas.projectinitiation.dto.response.PortfolioResponse;
import com.saas.projectinitiation.exception.ResourceExistsException;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.mapper.PortfolioMapper;
import com.saas.projectinitiation.model.Portfolio;
import com.saas.projectinitiation.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PortfolioService {

    private final PortfolioRepository repository;
    private final PortfolioMapper mapper;

    public PortfolioResponse create(UUID tenantId, PortfolioRequest request) {
        if (repository.existsByTenantIdAndName(tenantId, request.getName())) {
            throw new ResourceExistsException("Portfolio with name '" + request.getName() + "' already exists");
        }
        Portfolio entity = mapper.toEntity(tenantId, request);
        Portfolio savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }

    public List<PortfolioResponse> getAll(UUID tenantId) {
        return repository.findByTenantId(tenantId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    public PortfolioResponse getById(UUID tenantId, UUID id) {
        Portfolio entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found"));
        return mapper.toResponse(entity);
    }

    public PortfolioResponse getByName(UUID tenantId, String name) {
        Portfolio entity = repository.findByTenantIdAndName(tenantId, name)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found"));
        return mapper.toResponse(entity);
    }

    public PortfolioResponse update(UUID tenantId, UUID id, PortfolioRequest request) {
        Portfolio entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found"));
        
        if (!entity.getName().equals(request.getName()) && 
            repository.existsByTenantIdAndName(tenantId, request.getName())) {
            throw new ResourceExistsException("Portfolio with name '" + request.getName() + "' already exists");
        }
        
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        Portfolio savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }

    public void delete(UUID tenantId, UUID id) {
        Portfolio entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found"));
        repository.delete(entity);
    }
}

