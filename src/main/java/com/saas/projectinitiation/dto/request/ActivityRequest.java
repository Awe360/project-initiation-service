package com.saas.projectinitiation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequest {
    @NotBlank(message = "Activity name is required")
    private String name;
    @NotNull(message = "Milestone ID is required")
    private UUID milestoneId;
    @NotNull(message = "Project ID is required")
    private UUID projectId;
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    @NotNull(message = "End date is required")
    private LocalDate endDate;
    private String description;
    private Boolean isSubActivity;
    private UUID parentActivityId;
}

