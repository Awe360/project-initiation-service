package com.saas.projectinitiation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiskCategoryRequest {
    @NotBlank(message = "Type name is required")
    private String typeName;
    private String description;
}

