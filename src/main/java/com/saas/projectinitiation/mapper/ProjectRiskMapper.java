package com.saas.projectinitiation.mapper;

import com.saas.projectinitiation.dto.request.ProjectRiskRequest;
import com.saas.projectinitiation.dto.response.ProjectRiskResponse;
import com.saas.projectinitiation.model.*;
import com.saas.projectinitiation.utility.SecurityUtil;
import com.saas.projectinitiation.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProjectRiskMapper {

    private final ValidationUtil validationUtil;
    private final SecurityUtil securityUtil;

    public ProjectRisk toEntity(UUID tenantId, ProjectRiskRequest req, Document document) {
        Portfolio portfolio = validationUtil.findPortfolioById(tenantId, req.getPortfolioId());
        Program program = req.getProgramId() != null ? validationUtil.findProgramById(tenantId, req.getProgramId()) : null;
        MainProject project = validationUtil.findProjectById(tenantId, req.getProjectId());
        RiskCategory riskCategory = validationUtil.findRiskCategoryById(tenantId, req.getRiskCategoryId());

        ProjectRisk entity = new ProjectRisk();
        entity.setTenantId(tenantId);
        entity.setPortfolio(portfolio);
        entity.setProgram(program);
        entity.setProject(project);
        entity.setSubProjectId(req.getSubProjectId());
        entity.setRiskCategory(riskCategory);
        entity.setRiskFactor(req.getRiskFactor());
        entity.setMitigationPlan(req.getMitigationPlan());
        entity.setCauses(req.getCauses());
        entity.setAttachmentDocument(document);

        return entity;
    }

    public void updateEntityFromRequest(UUID tenantId, ProjectRisk entity, ProjectRiskRequest req) {
        Portfolio portfolio = validationUtil.findPortfolioById(tenantId, req.getPortfolioId());
        Program program = req.getProgramId() != null ? validationUtil.findProgramById(tenantId, req.getProgramId()) : null;
        MainProject project = validationUtil.findProjectById(tenantId, req.getProjectId());
        RiskCategory riskCategory = validationUtil.findRiskCategoryById(tenantId, req.getRiskCategoryId());

        entity.setPortfolio(portfolio);
        entity.setProgram(program);
        entity.setProject(project);
        entity.setSubProjectId(req.getSubProjectId());
        entity.setRiskCategory(riskCategory);
        entity.setRiskFactor(req.getRiskFactor());
        entity.setMitigationPlan(req.getMitigationPlan());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setCauses(req.getCauses());
        entity.setUpdatedBy(securityUtil.getName());
    }

    public ProjectRiskResponse toResponse(ProjectRisk entity) {
        ProjectRiskResponse res = new ProjectRiskResponse();
        res.setId(entity.getId());
        res.setTenantId(entity.getTenantId());
        res.setCreatedAt(entity.getCreatedAt());
        res.setUpdatedAt(entity.getUpdatedAt());
        res.setCreatedBy(entity.getCreatedBy());
        res.setUpdatedBy(entity.getUpdatedBy());

        res.setPortfolioId(entity.getPortfolioId());
        res.setPortfolioName(entity.getPortfolio() != null ? entity.getPortfolio().getName() : null);
        res.setProgramId(entity.getProgramId());
        res.setProgramName(entity.getProgram() != null ? entity.getProgram().getName() : null);
        res.setProjectId(entity.getProjectId());
        res.setProjectName(entity.getProject() != null ? entity.getProject().getProjectName() : null);
        res.setSubProjectId(entity.getSubProjectId());
        res.setCauses(entity.getCauses());
        res.setRiskCategoryId(entity.getRiskCategoryId());
        res.setRiskCategoryName(entity.getRiskCategory() != null ? entity.getRiskCategory().getTypeName() : null);
        res.setRiskFactor(entity.getRiskFactor());
        res.setMitigationPlan(entity.getMitigationPlan());

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