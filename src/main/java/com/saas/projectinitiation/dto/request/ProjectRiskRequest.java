package com.saas.projectinitiation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ProjectRiskRequest {

    @NotNull private UUID portfolioId;
    private UUID programId;
    @NotNull private UUID projectId;
    private UUID subProjectId;
    @NotNull private UUID riskCategoryId;
    private String causes;

    @NotBlank private String riskFactor;
    private String mitigationPlan;
    private String attachmentAction; // "remove" to clear attachment
}