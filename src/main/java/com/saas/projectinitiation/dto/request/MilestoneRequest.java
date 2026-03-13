
package com.saas.projectinitiation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MilestoneRequest {
    @NotBlank(message = "Milestone name is required")
    private String milestoneName;
    @NotNull(message = "Project ID is required")
    private UUID projectId;
    private UUID subProjectId;
    private String description;
}