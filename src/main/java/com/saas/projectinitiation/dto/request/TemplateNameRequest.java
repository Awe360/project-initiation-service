package com.saas.projectinitiation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TemplateNameRequest {

    @NotBlank
    private String name;

    private String description;
}