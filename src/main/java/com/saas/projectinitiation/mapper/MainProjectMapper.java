package com.saas.projectinitiation.mapper;

import com.saas.projectinitiation.dto.request.MainProjectRequest;
import com.saas.projectinitiation.dto.response.FinancialDetailsResponse;
import com.saas.projectinitiation.dto.response.MainProjectResponse;
import com.saas.projectinitiation.model.*;
import com.saas.projectinitiation.utility.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MainProjectMapper {

    private final SecurityUtil securityUtil;

    public MainProject toEntity(UUID tenantId, MainProjectRequest request,
                                Portfolio portfolio, Program program, ProjectType projectType, Document document) {
        MainProject entity = new MainProject();
        entity.setTenantId(tenantId);
        entity.setProjectName(request.getProjectName());
        entity.setProjectNumber(request.getProjectNumber());
        entity.setProjectCategory(request.getProjectCategory());
        entity.setPortfolio(portfolio);
        entity.setPortfolioId(portfolio.getId());
        entity.setProgram(program);
        entity.setProgramId(program.getId());
        entity.setProjectType(projectType);
        entity.setProjectTypeId(projectType.getId());
        entity.setClientName(request.getClientName());
        entity.setTotalContractAmount(request.getTotalContractAmount());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setProjectManagerId(request.getProjectManagerId());
        entity.setStartDate(request.getStartDate());
        entity.setCompletionDate(request.getCompletionDate());
        entity.setContractTypeId(request.getContractTypeId());
        entity.setDescription(request.getDescription());
        entity.setAttachmentDocument(document);

        if (request.getFinancialDetails() != null && !request.getFinancialDetails().isEmpty()) {
            List<FinancialDetails> financialDetailsList = request.getFinancialDetails().stream()
                    .map(fdReq -> {
                        FinancialDetails fd = new FinancialDetails();
                        fd.setFinancerName(fdReq.getFinancerName());
                        fd.setLoanNumber(fdReq.getLoanNumber());
                        fd.setOverheadEstimatedCost(fdReq.getOverheadEstimatedCost());
                        fd.setLocalPortion(fdReq.getLocalPortion());
                        fd.setForeignCost(fdReq.getForeignCost());
                        fd.setCurrency(fdReq.getCurrency());
                        fd.setTotalEstimatedCost(fdReq.getTotalEstimatedCost());
                        fd.setProject(entity);
                        fd.setProjectId(entity.getId());
                        fd.setTenantId(tenantId);
                        return fd;
                    })
                    .collect(Collectors.toList());
            entity.setFinancialDetails(financialDetailsList);
        }

        return entity;
    }

    public void updateEntityFromRequest(UUID tenantId, MainProject entity, MainProjectRequest request) {
        entity.setProjectName(request.getProjectName());
        entity.setProjectCategory(request.getProjectCategory());
        entity.setPortfolioId(request.getPortfolioId());
        entity.setProgramId(request.getProgramId());
        entity.setProjectTypeId(request.getProjectTypeId());
        entity.setClientName(request.getClientName());
        entity.setTotalContractAmount(request.getTotalContractAmount());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setProjectManagerId(request.getProjectManagerId());
        entity.setStartDate(request.getStartDate());
        entity.setCompletionDate(request.getCompletionDate());
        entity.setContractTypeId(request.getContractTypeId());
        entity.setDescription(request.getDescription());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setUpdatedBy(securityUtil.getName());

        entity.getFinancialDetails().clear();
        if (request.getFinancialDetails() != null && !request.getFinancialDetails().isEmpty()) {
            List<FinancialDetails> updatedList = request.getFinancialDetails().stream()
                    .map(fdReq -> {
                        FinancialDetails fd = new FinancialDetails();
                        fd.setFinancerName(fdReq.getFinancerName());
                        fd.setLoanNumber(fdReq.getLoanNumber());
                        fd.setOverheadEstimatedCost(fdReq.getOverheadEstimatedCost());
                        fd.setLocalPortion(fdReq.getLocalPortion());
                        fd.setForeignCost(fdReq.getForeignCost());
                        fd.setCurrency(fdReq.getCurrency());
                        fd.setTotalEstimatedCost(fdReq.getTotalEstimatedCost());
                        fd.setProject(entity);
                        fd.setProjectId(entity.getId());
                        fd.setTenantId(tenantId);
                        return fd;
                    })
                    .collect(Collectors.toList());
            entity.getFinancialDetails().addAll(updatedList);
        }
    }

    public MainProjectResponse toResponse(MainProject entity) {
        MainProjectResponse res = new MainProjectResponse();
        res.setId(entity.getId());
        res.setTenantId(entity.getTenantId());
        res.setCreatedAt(entity.getCreatedAt());
        res.setUpdatedAt(entity.getUpdatedAt());
        res.setCreatedBy(entity.getCreatedBy());
        res.setUpdatedBy(entity.getUpdatedBy());

        res.setProjectName(entity.getProjectName());
        res.setProjectNumber(entity.getProjectNumber());
        res.setProjectCategory(entity.getProjectCategory());
        res.setPortfolioId(entity.getPortfolioId());
        res.setProgramId(entity.getProgramId());
        res.setProjectTypeId(entity.getProjectTypeId());
        res.setClientName(entity.getClientName());
        res.setTotalContractAmount(entity.getTotalContractAmount());
        res.setDepartmentId(entity.getDepartmentId());
        res.setProjectManagerId(entity.getProjectManagerId());
        res.setStartDate(entity.getStartDate());
        res.setCompletionDate(entity.getCompletionDate());
        res.setContractTypeId(entity.getContractTypeId());
        res.setDescription(entity.getDescription());
        res.setEstimatedCost(entity.getEstimatedCost());

        if (entity.getAttachmentDocument() != null) {
            Document doc = entity.getAttachmentDocument();
            res.setAttachmentDocumentId(doc.getId());
            res.setAttachmentFileName(doc.getFileName());
            res.setAttachmentFileType(doc.getFileType());
            res.setAttachmentFileSize(doc.getFileSize());
        }

        if (entity.getFinancialDetails() != null && !entity.getFinancialDetails().isEmpty()) {
            List<FinancialDetailsResponse> fdResponses = entity.getFinancialDetails().stream()
                    .map(fd -> {
                        FinancialDetailsResponse fdRes = new FinancialDetailsResponse();
                        fdRes.setFinancerName(fd.getFinancerName());
                        fdRes.setLoanNumber(fd.getLoanNumber());
                        fdRes.setOverheadEstimatedCost(fd.getOverheadEstimatedCost());
                        fdRes.setLocalPortion(fd.getLocalPortion());
                        fdRes.setForeignCost(fd.getForeignCost());
                        fdRes.setCurrency(fd.getCurrency());
                        fdRes.setTotalEstimatedCost(fd.getTotalEstimatedCost());
                        return fdRes;
                    })
                    .collect(Collectors.toList());
            res.setFinancialDetails(fdResponses);
        }

        return res;
    }
}