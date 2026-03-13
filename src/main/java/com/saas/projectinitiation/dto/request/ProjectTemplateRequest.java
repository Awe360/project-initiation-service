
package com.saas.projectinitiation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ProjectTemplateRequest {

    @NotNull private UUID templateNameId;
    private String attachmentAction; // "remove" to clear attachment
}