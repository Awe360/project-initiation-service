package com.saas.projectinitiation.dto.eventDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeActivityStatusDto {
    private UUID projectId;
    private UUID subProjectId;
    private UUID tenantId;
    private UUID activityId;
    private String oldStatus;
    private String newStatus;
    private String changedBy;
    private String comment;
    private LocalDateTime changedAt;
}
