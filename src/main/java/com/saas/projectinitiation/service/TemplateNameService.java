package com.saas.projectinitiation.service;

import com.saas.projectinitiation.dto.request.TemplateNameRequest;
import com.saas.projectinitiation.dto.response.TemplateNameResponse;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.mapper.TemplateNameMapper;
import com.saas.projectinitiation.model.TemplateName;
import com.saas.projectinitiation.repository.ProjectTemplateRepository;
import com.saas.projectinitiation.repository.TemplateNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TemplateNameService {

    private final TemplateNameRepository repository;
    private final TemplateNameMapper mapper;
    private final ProjectTemplateRepository projectTemplateRepository;

    public TemplateNameResponse create(UUID tenantId, TemplateNameRequest request) {
        if (repository.existsByTenantIdAndNameIgnoreCase(tenantId, request.getName().trim())) {
            throw new IllegalArgumentException("Template name already exists for this tenant");
        }

        TemplateName entity = mapper.toEntity(tenantId, request);
        entity = repository.save(entity);

        return mapper.toResponse(entity);
    }

    public TemplateNameResponse update(UUID tenantId, UUID id, TemplateNameRequest request) {
        TemplateName entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Template name not found"));

        if (!entity.getName().equalsIgnoreCase(request.getName().trim()) &&
                repository.existsByTenantIdAndNameIgnoreCase(tenantId, request.getName().trim())) {
            throw new IllegalArgumentException("Template name already exists for this tenant");
        }

        mapper.updateEntityFromRequest(entity, request);
        entity = repository.save(entity);

        return mapper.toResponse(entity);
    }

    @Transactional(readOnly = true)
    public List<TemplateNameResponse> getAll(UUID tenantId) {
        return repository.findByTenantId(tenantId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public TemplateNameResponse getById(UUID tenantId, UUID id) {
        TemplateName entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Template name not found"));
        return mapper.toResponse(entity);
    }

    public void delete(UUID tenantId, UUID id) {
        TemplateName entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Template name not found"));

         if (projectTemplateRepository.existsByTenantIdAndTemplateNameId(tenantId, id)) {
             throw new IllegalStateException("Cannot delete template name in use");
         }

        repository.delete(entity);
    }
}