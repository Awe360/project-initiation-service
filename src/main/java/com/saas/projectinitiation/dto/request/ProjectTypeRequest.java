package com.saas.projectinitiation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTypeRequest {
    @NotBlank(message = "Project type is required")
    private String projectType;
}

