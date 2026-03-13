package com.saas.projectinitiation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CopyActivitiesRequest {
    @NotNull
    private UUID sourceProjectId;
    @NotNull
    private UUID destinationProjectId;
    private UUID destinationMilestoneId;
    @NotEmpty
    private List<UUID> activityIds;
}