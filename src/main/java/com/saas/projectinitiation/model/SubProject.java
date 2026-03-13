package com.saas.projectinitiation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sub_projects",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id", "sub_project_name"})
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubProject extends Base {

    @Column(name = "sub_project_name", nullable = false)
    private String subProjectName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private MainProject project;

    @Column(name = "project_id", insertable = false, updatable = false)
    private UUID projectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_type_id", nullable = false)
    private ProjectType projectType;

    @Column(name = "project_type_id", insertable = false, updatable = false)
    private UUID projectTypeId;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    private String location;

    @Column(name = "local_estimated_cost", precision = 19, scale = 2)
    private BigDecimal localEstimatedCost;

    @Column(name = "foreign_estimated_cost", precision = 19, scale = 2)
    private BigDecimal foreignEstimatedCost;

    @Column(name = "estimated_cost", nullable = false, precision = 19, scale = 2)
    private BigDecimal estimatedCost;

    @Column(columnDefinition = "TEXT")
    private String description;
}