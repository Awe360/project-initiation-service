package com.saas.projectinitiation.mapper;

import com.saas.projectinitiation.dto.request.ContractRequest;
import com.saas.projectinitiation.dto.response.ContractResponse;
import com.saas.projectinitiation.model.Contract;
import com.saas.projectinitiation.model.ContractType;
import com.saas.projectinitiation.model.Document;
import com.saas.projectinitiation.model.MainProject;
import com.saas.projectinitiation.utility.SecurityUtil;
import com.saas.projectinitiation.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ContractMapper {

    private final ValidationUtil validationUtil;
    private final SecurityUtil securityUtil;

    public Contract toEntity(UUID tenantId, ContractRequest request, Document document) {
        validationUtil.findProjectById(tenantId, request.getProjectId());
        ContractType contractType = validationUtil.findContractTypeById(tenantId, request.getContractTypeId());
        MainProject project = validationUtil.findProjectById(tenantId, request.getProjectId());

        Contract entity = new Contract();
        entity.setTenantId(tenantId);
        entity.setContractName(request.getContractName());
        entity.setContractNumber(request.getContractNumber());
        entity.setProject(project);
        entity.setClientName(request.getClientName());
        entity.setContractType(contractType);
        entity.setContractTypeId(request.getContractTypeId());
        entity.setTotalAmount(request.getTotalAmount());
        entity.setAdvancePayment(request.getAdvancePayment());
        entity.setCommencementDate(request.getCommencementDate());
        entity.setExpireDate(request.getExpireDate());
        entity.setContractPeriod(request.getContractPeriod());
        entity.setContractSigningDate(request.getContractSigningDate());
        entity.setStatus(request.getStatus());
        entity.setAttachmentDocument(document);

        return entity;
    }

    public void updateEntityFromRequest(UUID tenantId, Contract entity, ContractRequest request) {
        validationUtil.findProjectById(tenantId, request.getProjectId());
        ContractType contractType = validationUtil.findContractTypeById(tenantId, request.getContractTypeId());
        MainProject project = validationUtil.findProjectById(tenantId, request.getProjectId());

        entity.setContractName(request.getContractName());
        entity.setProject(project);
        entity.setClientName(request.getClientName());
        entity.setContractType(contractType);
        entity.setContractTypeId(request.getContractTypeId());
        entity.setTotalAmount(request.getTotalAmount());
        entity.setAdvancePayment(request.getAdvancePayment());
        entity.setCommencementDate(request.getCommencementDate());
        entity.setExpireDate(request.getExpireDate());
        entity.setContractPeriod(request.getContractPeriod());
        entity.setContractSigningDate(request.getContractSigningDate());
        entity.setStatus(request.getStatus());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setUpdatedBy(securityUtil.getName());
    }

    public ContractResponse toResponse(Contract entity) {
        ContractResponse res = new ContractResponse();
        res.setId(entity.getId());
        res.setTenantId(entity.getTenantId());
        res.setCreatedAt(entity.getCreatedAt());
        res.setUpdatedAt(entity.getUpdatedAt());
        res.setCreatedBy(entity.getCreatedBy());
        res.setUpdatedBy(entity.getUpdatedBy());

        res.setContractName(entity.getContractName());
        res.setContractNumber(entity.getContractNumber());
        res.setProjectId(entity.getProject().getId());
        res.setClientName(entity.getClientName());
        res.setContractTypeId(entity.getContractType().getId());
        res.setTotalAmount(entity.getTotalAmount());
        res.setAdvancePayment(entity.getAdvancePayment());
        res.setCommencementDate(entity.getCommencementDate());
        res.setExpireDate(entity.getExpireDate());
        res.setContractPeriod(entity.getContractPeriod());
        res.setContractSigningDate(entity.getContractSigningDate());
        res.setStatus(entity.getStatus());
        res.setProjectName(entity.getProject().getProjectName());

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