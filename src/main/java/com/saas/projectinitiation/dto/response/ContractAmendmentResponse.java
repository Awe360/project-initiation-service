
package com.saas.projectinitiation.dto.response;

import com.saas.projectinitiation.enums.AmendmentType;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ContractAmendmentResponse extends BaseResponse {
    private UUID contractId;
    private String contractName;
    private AmendmentType amendmentType;
    private LocalDate amendmentDate;
    private LocalDate amendedCommencementDate;
    private LocalDate amendedExpireDate;
    private String amendedContractPeriod;
    private String amendmentReason;
    private Long amendmentCost;
    private String projectName;
    private UUID projectId;

    private UUID attachmentDocumentId;
    private String attachmentFileName;
    private String attachmentFileType;
    private Long attachmentFileSize;
}