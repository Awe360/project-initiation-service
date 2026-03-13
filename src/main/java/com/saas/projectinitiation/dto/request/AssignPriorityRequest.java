package com.saas.projectinitiation.dto.request;

import com.saas.projectinitiation.enums.Priority;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignPriorityRequest {
    @NotNull(message = "Portfolio ID is required")
    private UUID portfolioId;
    private UUID programId;
    @NotNull(message = "Project ID is required")
    private UUID projectId;
    private BigDecimal projectEstimateCost;
    private String projectDuration;
    private String currentPhase;
    @NotNull(message = "Priority is required")
    private Priority priority;
    private String remark;
}

