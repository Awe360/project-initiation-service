package com.saas.projectinitiation.dto.request;

import com.saas.projectinitiation.enums.Currency;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinancialDetailsRequest {

    @NotNull(message = "Financer name is required")
    private String financerName;

    private String loanNumber;

    private BigDecimal overheadEstimatedCost;

    private BigDecimal localPortion;

    private BigDecimal foreignCost;

    @NotNull(message = "Currency is required")
    private Currency currency;

    @NotNull(message = "Total estimated cost is required")
    private BigDecimal totalEstimatedCost;
}