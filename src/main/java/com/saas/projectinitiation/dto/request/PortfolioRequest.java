package com.saas.projectinitiation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioRequest {
    @NotBlank(message = "Portfolio name is required")
    private String name;
    private String description;
}

