package com.saas.projectinitiation.dto.response;

import com.saas.projectinitiation.enums.Currency;
import lombok.Data;

import java.math.BigDecimal;


@Data

public class FinancialDetailsResponse {
    private String financerName;
    private String loanNumber;
    private BigDecimal overheadEstimatedCost;
    private BigDecimal localPortion;
    private BigDecimal foreignCost;
    private Currency currency;
    private BigDecimal totalEstimatedCost;
}
