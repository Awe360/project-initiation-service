package com.saas.projectinitiation.dto.request;

import com.saas.projectinitiation.enums.ContractStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class ContractRequest {

    @NotBlank
    private String contractName;


    @NotNull(message = "Contract number is required")
    private String contractNumber;


    @NotNull
    private UUID projectId;

    private String clientName;

    @NotNull
    private UUID contractTypeId;

    private BigDecimal totalAmount;

    private BigDecimal advancePayment;

    @NotNull
    private LocalDate commencementDate;

    @NotNull
    private LocalDate expireDate;

    private String contractPeriod;

    @NotNull
    private LocalDate contractSigningDate;

    @NotNull
    private ContractStatus status;

    private String attachmentAction;
}