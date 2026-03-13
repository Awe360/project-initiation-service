package com.saas.projectinitiation.dto.response;

import com.saas.projectinitiation.enums.ProjectCategory;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class MainProjectResponse extends BaseResponse {

    private String projectName;
    private String projectNumber;
    private ProjectCategory projectCategory;
    private UUID portfolioId;
    private UUID programId;
    private UUID projectTypeId;
    private String clientName;
    private BigDecimal totalContractAmount;
    private UUID departmentId;
    private UUID projectManagerId;
    private LocalDate startDate;
    private LocalDate completionDate;
    private UUID contractTypeId;
    private String description;
    private BigDecimal estimatedCost;

    private UUID attachmentDocumentId;
    private String attachmentFileName;
    private String attachmentFileType;
    private Long attachmentFileSize;

    private List<FinancialDetailsResponse> financialDetails;
}

