package com.saas.projectinitiation.service;

import com.saas.projectinitiation.dto.request.ProjectRiskRequest;
import com.saas.projectinitiation.dto.response.ProjectRiskResponse;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.mapper.ProjectRiskMapper;
import com.saas.projectinitiation.model.Document;
import com.saas.projectinitiation.model.ProjectRisk;
import com.saas.projectinitiation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectRiskService {

    private final ProjectRiskRepository repository;
    private final PortfolioRepository portfolioRepository;
    private final ProgramRepository programRepository;
    private final MainProjectRepository projectRepository;
    private final RiskCategoryRepository riskCategoryRepository;
    private final ProjectRiskMapper mapper;
    private final DocumentService documentService;

    public ProjectRiskResponse create(UUID tenantId, ProjectRiskRequest request, MultipartFile attachment) throws IOException {
        Document document = null;
        if (attachment != null && !attachment.isEmpty()) {
            document = documentService.upload(tenantId, attachment);
        }

        ProjectRisk entity = mapper.toEntity(tenantId, request, document);
        entity = repository.save(entity);

        return mapper.toResponse(entity);
    }

    public ProjectRiskResponse update(UUID tenantId, UUID id, ProjectRiskRequest request, MultipartFile attachment) throws IOException {
        ProjectRisk entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Project risk not found"));

        if (attachment != null && !attachment.isEmpty()) {
            Document newDoc = documentService.upload(tenantId, attachment);
            entity.setAttachmentDocument(newDoc);
  }
        else if ("remove".equals(request.getAttachmentAction())) {
            entity.setAttachmentDocument(null);
        }

        mapper.updateEntityFromRequest(tenantId, entity, request);
        entity = repository.save(entity);

        return mapper.toResponse(entity);
    }

    public List<ProjectRiskResponse> getAll(UUID tenantId) {
        return repository.findByTenantId(tenantId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ProjectRiskResponse getById(UUID tenantId, UUID id) {
        ProjectRisk entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Project risk not found"));
        return mapper.toResponse(entity);
    }

    public void delete(UUID tenantId, UUID id) {
        ProjectRisk entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Project risk not found"));
        repository.delete(entity);
    }

    public UUID getAttachmentDocumentId(UUID tenantId, UUID riskId) {
        return repository.findByTenantIdAndId(tenantId, riskId)
                .map(r -> r.getAttachmentDocument() != null ? r.getAttachmentDocument().getId() : null)
                .orElse(null);
    }
}