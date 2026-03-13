package com.saas.projectinitiation.model;

import com.saas.projectinitiation.enums.Priority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "assign_priorities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignPriority extends Base {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    @Column(name = "portfolio_id", insertable = false, updatable = false)
    private UUID portfolioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    private Program program;

    @Column(name = "program_id", insertable = false, updatable = false)
    private UUID programId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private MainProject project;

    @Column(name = "project_id", insertable = false, updatable = false)
    private UUID projectId;

    @Column(name = "project_estimate_cost", precision = 19, scale = 2)
    private BigDecimal projectEstimateCost;

    @Column(name = "project_duration")
    private String projectDuration; // in days

    @Column(name = "current_phase")
    private String currentPhase;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @Column(columnDefinition = "TEXT")
    private String remark;
}

