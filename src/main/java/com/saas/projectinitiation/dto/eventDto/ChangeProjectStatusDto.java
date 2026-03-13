package com.saas.projectinitiation.dto.eventDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeProjectStatusDto {
 private UUID projectId;
 private UUID tenantId;
 private String previousStatus;
 private String newStatus;
 private String comment;
 private String changedBy;
 private LocalDateTime changedAt;
}