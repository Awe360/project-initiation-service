package com.saas.projectinitiation.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class SubProjectResponse extends BaseResponse {

    private String subProjectName;
    private UUID projectId;
    private UUID projectTypeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private BigDecimal localEstimatedCost;
    private BigDecimal foreignEstimatedCost;
    private BigDecimal estimatedCost;
    private String description;
}