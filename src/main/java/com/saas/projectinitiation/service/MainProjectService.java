package com.saas.projectinitiation.service;

import com.saas.projectinitiation.dto.request.MainProjectRequest;
import com.saas.projectinitiation.dto.response.MainProjectResponse;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.mapper.MainProjectMapper;
import com.saas.projectinitiation.model.*;
import com.saas.projectinitiation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Year;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MainProjectService {

    private final MainProjectRepository repository;
    private final ProjectSequenceRepository sequenceRepository;
    private final MainProjectMapper mapper;
    private final DocumentService documentService;
    private final PortfolioRepository portfolioRepository;
    private final ProgramRepository programRepository;
    private final ProjectTypeRepository projectTypeRepository;

    @Transactional
    public String getNextProjectNumberPreview() {
        ProjectSequence seq = sequenceRepository.findById(1).orElseGet(() -> {
            ProjectSequence newSeq = new ProjectSequence();
            return sequenceRepository.save(newSeq);
        });

        long next = seq.getLastNumber() + 1;
        seq.setLastNumber(next);
        sequenceRepository.save(seq);

        String year = String.valueOf(Year.now().getValue());
        return String.format("PRJ-%s-%06d", year, next);
    }

    @Transactional
    public MainProjectResponse create(UUID tenantId, MainProjectRequest request, MultipartFile attachment) throws IOException {


        Portfolio portfolio = portfolioRepository.findById(request.getPortfolioId())
                .filter(p -> p.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found"));

        Program program = programRepository.findById(request.getProgramId())
                .filter(p -> p.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Program not found"));

        ProjectType projectType = projectTypeRepository.findById(request.getProjectTypeId())
                .filter(pt -> pt.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Project Type not found"));

        Document document = null;
        if (attachment != null && !attachment.isEmpty()) {
            document = documentService.upload(tenantId, attachment);
        }

        MainProject entity = mapper.toEntity(tenantId, request, portfolio, program, projectType, document);
        entity = repository.save(entity);

        return mapper.toResponse(entity);
    }

    public MainProjectResponse update(UUID tenantId, UUID id, MainProjectRequest request, MultipartFile attachment) throws IOException {
        MainProject entity = repository.findById(id)
                .filter(p -> tenantId.equals(p.getTenantId()))
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        if (attachment != null && !attachment.isEmpty()) {
            Document newDoc = documentService.upload(tenantId, attachment);
            entity.setAttachmentDocument(newDoc);
        } else if ("remove".equals(request.getAttachmentAction())) {
            entity.setAttachmentDocument(null);
        }

        mapper.updateEntityFromRequest(tenantId, entity, request);
        entity = repository.save(entity);

        return mapper.toResponse(entity);
    }

    public List<MainProjectResponse> getAll(UUID tenantId) {
        return repository.findByTenantId(tenantId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public MainProjectResponse getById(UUID tenantId, UUID id) {
        MainProject entity = repository.findById(id)
                .filter(p -> tenantId.equals(p.getTenantId()))
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        return mapper.toResponse(entity);
    }

    public List<MainProjectResponse> getByProgram(UUID tenantId, UUID programId) {
        return repository.findByTenantIdAndProgramId(tenantId, programId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public void delete(UUID tenantId, UUID id) {
        MainProject entity = repository.findById(id)
                .filter(p -> tenantId.equals(p.getTenantId()))
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        repository.delete(entity);
    }

    public UUID getAttachmentDocumentId(UUID tenantId, UUID projectId) {
        return repository.findById(projectId)
                .filter(p -> tenantId.equals(p.getTenantId()))
                .map(p -> p.getAttachmentDocument() != null ? p.getAttachmentDocument().getId() : null)
                .orElse(null);
    }


    @Transactional
    public void updateProjectExecutionStatus(UUID tenantId, UUID projectId, String newStatus) {
        MainProject project = repository.findByTenantIdAndId(tenantId, projectId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Project not found for tenantId: " + tenantId + " and projectId: " + projectId));
        project.setExecutionStatus(newStatus);

    }
}