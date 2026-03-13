package com.saas.projectinitiation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRiskResponse {
    private UUID id;
    private UUID tenantId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

    private UUID portfolioId;
    private String portfolioName;
    private UUID programId;
    private String programName;
    private UUID projectId;
    private String projectName;
    private UUID subProjectId;
    private UUID riskCategoryId;
    private String riskCategoryName;
    private String riskFactor;
    private String mitigationPlan;
    private String causes;

    private UUID attachmentDocumentId;
    private String attachmentFileName;
    private String attachmentFileType;
    private Long attachmentFileSize;
}