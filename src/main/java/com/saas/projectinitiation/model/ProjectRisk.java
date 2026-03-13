package com.saas.projectinitiation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "project_risks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRisk extends Base {

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

    @Column(name = "sub_project_id")
    private UUID subProjectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "risk_category_id", nullable = false)
    private RiskCategory riskCategory;

    @Column(name = "risk_category_id", insertable = false, updatable = false)
    private UUID riskCategoryId;

    @Column(name = "risk_factor", nullable = false, columnDefinition = "TEXT")
    private String riskFactor;

    @Column(name = "mitigation_plan", columnDefinition = "TEXT")
    private String mitigationPlan;

    @Column(name = "causes", columnDefinition = "TEXT")
    private String causes;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_document_id", foreignKey = @ForeignKey(name = "fk_project_risk_attachment_document"))
    private Document attachmentDocument;
}