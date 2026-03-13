package com.saas.projectinitiation.service;

import com.saas.projectinitiation.dto.request.ProjectTemplateRequest;
import com.saas.projectinitiation.dto.response.ProjectTemplateResponse;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.mapper.ProjectTemplateMapper;
import com.saas.projectinitiation.model.Document;
import com.saas.projectinitiation.model.ProjectTemplate;
import com.saas.projectinitiation.repository.ProjectTemplateRepository;
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
public class ProjectTemplateService {

    private final ProjectTemplateRepository repository;
    private final ProjectTemplateMapper mapper;
    private final DocumentService documentService;

    public ProjectTemplateResponse create(UUID tenantId, ProjectTemplateRequest request, MultipartFile attachment) throws IOException {
        Document document = null;
        if (attachment != null && !attachment.isEmpty()) {
            document = documentService.upload(tenantId, attachment);
        }

        ProjectTemplate entity = mapper.toEntity(tenantId, request.getTemplateNameId(), document);
        entity = repository.save(entity);

        return mapper.toResponse(entity);
    }

    public ProjectTemplateResponse update(UUID tenantId, UUID id, ProjectTemplateRequest request, MultipartFile attachment) throws IOException {
        ProjectTemplate entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Project template not found"));

        if (attachment != null && !attachment.isEmpty()) {
            Document newDoc = documentService.upload(tenantId, attachment);
            entity.setAttachmentDocument(newDoc);
        } else if ("remove".equals(request.getAttachmentAction())) {
            entity.setAttachmentDocument(null);
        }

        mapper.updateEntityFromRequest(tenantId, entity, request.getTemplateNameId());
        entity = repository.save(entity);

        return mapper.toResponse(entity);
    }

    public List<ProjectTemplateResponse> getAll(UUID tenantId) {
        return repository.findByTenantId(tenantId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ProjectTemplateResponse getById(UUID tenantId, UUID id) {
        ProjectTemplate entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Project template not found"));
        return mapper.toResponse(entity);
    }

    public void delete(UUID tenantId, UUID id) {
        ProjectTemplate entity = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Project template not found"));
        repository.delete(entity);
    }

    public UUID getAttachmentDocumentId(UUID tenantId, UUID templateId) {
        return repository.findByTenantIdAndId(tenantId, templateId)
                .map(t -> t.getAttachmentDocument() != null ? t.getAttachmentDocument().getId() : null)
                .orElse(null);
    }
}