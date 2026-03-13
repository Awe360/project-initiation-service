package com.saas.projectinitiation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyPersonnelRequest {
    @NotNull(message = "Portfolio ID is required")
    private UUID portfolioId;
    @NotNull(message = "Program ID is required")
    private UUID programId;
    @NotNull(message = "Project ID is required")
    private UUID projectId;
    @NotNull(message = "Stakeholder ID is required")
    private UUID stakeholderId;
}

