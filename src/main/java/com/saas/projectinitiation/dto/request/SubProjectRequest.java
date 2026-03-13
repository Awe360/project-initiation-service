package com.saas.projectinitiation.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class SubProjectRequest {

    @NotEmpty(message = "At least one sub-project is required")
    @Valid
    private List<SubProjectItem> subProjects;

    @Data
    public static class SubProjectItem {

        private UUID projectId;

        private String subProjectName;

        private UUID projectTypeId;

        private LocalDate startDate;

        private LocalDate endDate;

        private String location;

        private BigDecimal localEstimatedCost;

        private BigDecimal foreignEstimatedCost;

        private BigDecimal estimatedCost;

        private String description;
    }
}