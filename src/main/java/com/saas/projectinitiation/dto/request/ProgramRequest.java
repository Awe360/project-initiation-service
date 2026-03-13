package com.saas.projectinitiation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramRequest {
    @NotBlank(message = "Program name is required")
    private String name;
    @NotNull(message = "Portfolio ID is required")
    private UUID portfolioId;
    private String remark;
}

