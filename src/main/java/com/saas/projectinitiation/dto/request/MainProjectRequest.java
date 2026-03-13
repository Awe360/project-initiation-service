package com.saas.projectinitiation.dto.request;

import com.saas.projectinitiation.enums.ProjectCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class MainProjectRequest {

    @NotNull(message = "Project category is required")
    private ProjectCategory projectCategory;

    @NotNull(message = "Project number is required")
    private String projectNumber;

    @NotNull(message = "Portfolio is required")
    private UUID portfolioId;

    @NotNull(message = "Program is required")
    private UUID programId;

    @NotNull(message = "Project name is required")
    private String projectName;

    private String clientName;

    private BigDecimal totalContractAmount;

    private UUID departmentId;

    private UUID projectManagerId;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "Completion date is required")
    private LocalDate completionDate;

    private UUID contractTypeId;

    @NotNull(message = "Project type is required")
    private UUID projectTypeId;

    private String description;

    private String attachmentAction;

    private List<FinancialDetailsRequest> financialDetails;
}