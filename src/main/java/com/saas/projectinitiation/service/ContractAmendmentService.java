package com.saas.projectinitiation.service;

import com.saas.projectinitiation.dto.request.ContractAmendmentRequest;
import com.saas.projectinitiation.dto.response.ContractAmendmentResponse;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.mapper.ContractAmendmentMapper;
import com.saas.projectinitiation.model.Contract;
import com.saas.projectinitiation.model.ContractAmendment;
import com.saas.projectinitiation.model.Document;
import com.saas.projectinitiation.repository.ContractAmendmentRepository;
import com.saas.projectinitiation.repository.ContractRepository;
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
public class ContractAmendmentService {

    private final ContractAmendmentRepository amendmentRepository;
    private final ContractRepository contractRepository;
    private final ContractAmendmentMapper mapper;
    private final DocumentService documentService;

    public ContractAmendmentResponse create(UUID tenantId, ContractAmendmentRequest request, MultipartFile attachment) throws IOException {
        Contract contract = contractRepository.findById(request.getContractId())
                .filter(c -> c.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        Document document = null;
        if (attachment != null && !attachment.isEmpty()) {
            document = documentService.upload(tenantId, attachment);
        }

        ContractAmendment entity = mapper.toEntity(contract, request, document,tenantId);
        entity = amendmentRepository.save(entity);

        return mapper.toResponse(entity);
    }

    public ContractAmendmentResponse update(UUID tenantId, UUID id, ContractAmendmentRequest request, MultipartFile attachment) throws IOException {
        ContractAmendment entity = amendmentRepository.findByIdAndContractTenantId(id, tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Amendment not found"));

        if (attachment != null && !attachment.isEmpty()) {
            Document newDoc = documentService.upload(tenantId, attachment);
            entity.setAttachmentDocument(newDoc);
        } else if ("remove".equals(request.getAttachmentAction())) {
            entity.setAttachmentDocument(null);
        }

        mapper.updateEntityFromRequest(entity, request);
        entity = amendmentRepository.save(entity);

        return mapper.toResponse(entity);
    }

    public List<ContractAmendmentResponse> getByContractId(UUID tenantId, UUID contractId) {
        Contract contract = contractRepository.findById(contractId)
                .filter(c -> c.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        return amendmentRepository.findByContractId(contractId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ContractAmendmentResponse getById(UUID tenantId, UUID id) {
        ContractAmendment entity = amendmentRepository.findByIdAndContractTenantId(id, tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Amendment not found"));
        return mapper.toResponse(entity);
    }

    public List<ContractAmendmentResponse> getAll(UUID tenantId) {
        return amendmentRepository.findAll()
                .stream()
                .filter(a -> a.getTenantId().equals(tenantId))
                .map(mapper::toResponse)
                .toList();
    }

    public List<ContractAmendmentResponse> getByProjectId(UUID tenantId, UUID projectId) {
        List<UUID> contractIds = contractRepository.findByTenantIdAndProjectId(tenantId, projectId)
                .stream()
                .map(Contract::getId)
                .toList();

        if (contractIds.isEmpty()) {
            return List.of();
        }

        return amendmentRepository.findAllByContractIdIn(contractIds)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public void delete(UUID tenantId, UUID id) {
        ContractAmendment entity = amendmentRepository.findByIdAndContractTenantId(id, tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Amendment not found"));
        amendmentRepository.delete(entity);
    }

    public UUID getAttachmentDocumentId(UUID tenantId, UUID amendmentId) {
        return amendmentRepository.findByIdAndContractTenantId(amendmentId, tenantId)
                .map(a -> a.getAttachmentDocument() != null ? a.getAttachmentDocument().getId() : null)
                .orElse(null);
    }
}