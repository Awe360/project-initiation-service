package com.saas.projectinitiation.dto.response;

import com.saas.projectinitiation.enums.ContractStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class ContractResponse extends BaseResponse {

    private String contractName;
    private String contractNumber;
    private UUID projectId;
    private String clientName;
    private UUID contractTypeId;
    private BigDecimal totalAmount;
    private BigDecimal advancePayment;
    private LocalDate commencementDate;
    private LocalDate expireDate;
    private String contractPeriod;
    private LocalDate contractSigningDate;
    private ContractStatus status;
    private String projectName;

    private UUID attachmentDocumentId;
    private String attachmentFileName;
    private String attachmentFileType;
    private Long attachmentFileSize;
}