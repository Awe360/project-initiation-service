package com.saas.projectinitiation.dto.response;

import com.saas.projectinitiation.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignPriorityResponse extends BaseResponse {
    private UUID id;
    private UUID portfolioId;
    private String portfolioName;
    private UUID programId;
    private String programName;
    private UUID projectId;
    private String projectName;
    private BigDecimal projectEstimateCost;
    private String projectDuration;
    private String currentPhase;
    private Priority priority;
    private String remark;
}

