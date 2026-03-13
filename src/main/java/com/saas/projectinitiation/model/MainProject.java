package com.saas.projectinitiation.model;

import com.saas.projectinitiation.enums.ProjectCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "projects",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id", "project_name"}),
                @UniqueConstraint(columnNames = {"tenant_id", "project_number"})
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainProject extends Base {

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "project_number", nullable = false)
    private String projectNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_category", nullable = false)
    private ProjectCategory projectCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;

    @Column(name = "program_id", insertable = false, updatable = false)
    private UUID programId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    @Column(name = "portfolio_id", insertable = false, updatable = false)
    private UUID portfolioId;

    @Column(name="execution_status", nullable = false)
    private String executionStatus = "Not Started";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_type_id", nullable = false)
    private ProjectType projectType;

    @Column(name = "project_type_id", insertable = false, updatable = false)
    private UUID projectTypeId;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "total_contract_amount", precision = 19, scale = 2)
    private BigDecimal totalContractAmount;

    @Column(name = "department_id")
    private UUID departmentId;

    @Column(name = "project_manager_id")
    private UUID projectManagerId;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "completion_date", nullable = false)
    private LocalDate completionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_type_id")
    private ContractType contractType;

    @Column(name = "contract_type_id", insertable = false, updatable = false)
    private UUID contractTypeId;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "estimated_cost", precision = 19, scale = 2)
    private BigDecimal estimatedCost;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_document_id")
    private Document attachmentDocument;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FinancialDetails> financialDetails = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubProject> subProjects = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contract> contracts = new ArrayList<>();
}