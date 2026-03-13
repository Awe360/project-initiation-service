package com.saas.projectinitiation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractTypeRequest {
    @NotBlank(message = "Contract type is required")
    private String contractType;
    private String description;
}

