
package com.saas.projectinitiation.dto.request;

import com.saas.projectinitiation.enums.AmendmentType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ContractAmendmentRequest {
    @NotNull
    private UUID contractId;

    @NotNull
    private AmendmentType amendmentType;

    private LocalDate amendedCommencementDate;
    private LocalDate amendedExpireDate;
    private String amendedContractPeriod;
    private String amendmentReason;
    private Long amendmentCost;
    private String attachmentAction; // "remove" to clear attachment
}