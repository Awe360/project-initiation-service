package com.saas.projectinitiation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyPersonnelResponse extends BaseResponse {
    private UUID id;
    private UUID portfolioId;
    private String portfolioName;
    private UUID programId;
    private String programName;
    private UUID projectId;
    private String projectName;
    private UUID stakeholderId;
}

