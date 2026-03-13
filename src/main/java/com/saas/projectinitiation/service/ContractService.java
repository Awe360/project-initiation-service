package com.saas.projectinitiation.service;

import com.saas.projectinitiation.dto.request.ContractRequest;
import com.saas.projectinitiation.dto.response.ContractResponse;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.mapper.ContractMapper;
import com.saas.projectinitiation.model.Contract;
import com.saas.projectinitiation.model.ContractSequence;
import com.saas.projectinitiation.model.Document;
import com.saas.projectinitiation.repository.ContractRepository;
import com.saas.projectinitiation.repository.ContractSequenceRepository;
import com.saas.projectinitiation.utility.ValidationUtil;
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
public class ContractService {

    private final ContractRepository repository;
    private final ContractSequenceRepository contractSequenceRepository;
    private final ContractMapper mapper;
    private final DocumentService documentService;
    private final ValidationUtil validationUtil;

    @Transactional
    public String getNextContractNumberPreview() {
        ContractSequence seq = contractSequenceRepository.findById(1).orElseGet(() -> {
            ContractSequence newSeq = new ContractSequence();
            return contractSequenceRepository.save(newSeq);
        });

        long next = seq.getLastNumber() + 1;
        seq.setLastNumber(next);
        contractSequenceRepository.save(seq);

        String year = String.valueOf(Year.now().getValue());
        return String.format("CON-%s-%06d", year, next);
    }

    @Transactional
    public ContractResponse create(UUID tenantId, ContractRequest request, MultipartFile attachment) throws IOException {

        Document document = null;
        if (attachment != null && !attachment.isEmpty()) {
            document = documentService.upload(tenantId, attachment);
        }

        Contract entity = mapper.toEntity(tenantId, request, document);
        entity = repository.save(entity);

        return mapper.toResponse(entity);
    }

    public ContractResponse update(UUID tenantId, UUID id, ContractRequest request, MultipartFile attachment) throws IOException {
        Contract entity = repository.findById(id)
                .filter(c -> tenantId.equals(c.getTenantId()))
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

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

    public List<ContractResponse> getAllByProject(UUID tenantId, UUID projectId) {
        return repository.findByTenantIdAndProjectId(tenantId, projectId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ContractResponse getById(UUID tenantId, UUID id) {
        Contract entity = repository.findById(id)
                .filter(c -> tenantId.equals(c.getTenantId()))
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        return mapper.toResponse(entity);
    }

    public List<ContractResponse>getByContractName(UUID tenantId, String contractName) {
        return repository.findByTenantId(tenantId).stream()
                .filter(c -> c.getContractName() != null && c.getContractName().toLowerCase().contains(contractName.toLowerCase()))
                .map(mapper::toResponse)
                .toList();
    }
    public List<ContractResponse>getAll(UUID tenantId) {
        return repository.findByTenantId(tenantId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    public void delete(UUID tenantId, UUID id) {
        Contract entity = repository.findById(id)
                .filter(c -> tenantId.equals(c.getTenantId()))
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        repository.delete(entity);
    }

    public UUID getAttachmentDocumentId(UUID tenantId, UUID contractId) {
        return repository.findById(contractId)
                .filter(c -> tenantId.equals(c.getTenantId()))
                .map(c -> c.getAttachmentDocument() != null ? c.getAttachmentDocument().getId() : null)
                .orElse(null);
    }
}