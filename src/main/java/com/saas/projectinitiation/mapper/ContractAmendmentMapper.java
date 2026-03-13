package com.saas.projectinitiation.mapper;

import com.saas.projectinitiation.dto.request.ContractAmendmentRequest;
import com.saas.projectinitiation.dto.response.ContractAmendmentResponse;
import com.saas.projectinitiation.model.Contract;
import com.saas.projectinitiation.model.ContractAmendment;
import com.saas.projectinitiation.model.Document;
import com.saas.projectinitiation.utility.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ContractAmendmentMapper {

    private final SecurityUtil securityUtil;

    public ContractAmendment toEntity(Contract contract, ContractAmendmentRequest request, Document document, UUID tenantId) {
        ContractAmendment entity = new ContractAmendment();
        entity.setContract(contract);
        entity.setTenantId(tenantId);
        entity.setContractId(contract.getId());
        entity.setAmendmentType(request.getAmendmentType());
        entity.setAmendmentDate(LocalDate.now());
        entity.setAmendedCommencementDate(request.getAmendedCommencementDate());
        entity.setAmendedExpireDate(request.getAmendedExpireDate());
        entity.setAmendedContractPeriod(request.getAmendedContractPeriod());
        entity.setAmendmentReason(request.getAmendmentReason());
        entity.setAmendmentCost(request.getAmendmentCost());
        entity.setAttachmentDocument(document);
        return entity;
    }

    public void updateEntityFromRequest(ContractAmendment entity, ContractAmendmentRequest request) {
        entity.setAmendmentType(request.getAmendmentType());
        entity.setAmendedCommencementDate(request.getAmendedCommencementDate());
        entity.setAmendedExpireDate(request.getAmendedExpireDate());
        entity.setAmendedContractPeriod(request.getAmendedContractPeriod());
        entity.setAmendmentReason(request.getAmendmentReason());
        entity.setAmendmentCost(request.getAmendmentCost());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setUpdatedBy(securityUtil.getName());
    }

    public ContractAmendmentResponse toResponse(ContractAmendment entity) {
        ContractAmendmentResponse res = new ContractAmendmentResponse();
        res.setId(entity.getId());
        res.setTenantId(entity.getTenantId());
        res.setCreatedAt(entity.getCreatedAt());
        res.setUpdatedAt(entity.getUpdatedAt());
        res.setCreatedBy(entity.getCreatedBy());
        res.setUpdatedBy(entity.getUpdatedBy());

        res.setContractId(entity.getContractId());
        res.setAmendmentType(entity.getAmendmentType());
        res.setAmendmentDate(entity.getAmendmentDate());
        res.setAmendedCommencementDate(entity.getAmendedCommencementDate());
        res.setAmendedExpireDate(entity.getAmendedExpireDate());
        res.setAmendedContractPeriod(entity.getAmendedContractPeriod());
        res.setAmendmentReason(entity.getAmendmentReason());
        res.setProjectName(entity.getContract().getProject().getProjectName());
        res.setAmendmentCost(entity.getAmendmentCost());
        res.setProjectId(entity.getContract().getProject().getId());
        res.setContractName(entity.getContract().getContractName());

        if (entity.getAttachmentDocument() != null) {
            Document doc = entity.getAttachmentDocument();
            res.setAttachmentDocumentId(doc.getId());
            res.setAttachmentFileName(doc.getFileName());
            res.setAttachmentFileType(doc.getFileType());
            res.setAttachmentFileSize(doc.getFileSize());
        }

        return res;
    }
}