
package com.saas.projectinitiation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CopyMilestonesRequest {
    @NotNull
    private UUID sourceProjectId;
    @NotNull
    private UUID destinationProjectId;
    @NotEmpty
    private List<UUID> milestoneIds;
}