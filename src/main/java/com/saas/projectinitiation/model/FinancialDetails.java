package com.saas.projectinitiation.model;

import com.saas.projectinitiation.enums.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "financial_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialDetails extends Base {

    @Column(nullable = false)
    private String financerName;

    @Column(name = "loan_number")
    private String loanNumber;

    @Column(name = "overhead_estimated_cost", precision = 19, scale = 2)
    private BigDecimal overheadEstimatedCost;

    @Column(name = "local_portion", precision = 19, scale = 2)
    private BigDecimal localPortion;

    @Column(name = "foreign_cost", precision = 19, scale = 2)
    private BigDecimal foreignCost;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @Column(name = "total_estimated_cost", nullable = false, precision = 19, scale = 2)
    private BigDecimal totalEstimatedCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private MainProject project;

    @Column(name = "project_id", insertable = false, updatable = false)
    private UUID projectId;
}