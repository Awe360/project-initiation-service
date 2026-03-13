package com.saas.projectinitiation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "activities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity extends Base {

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "milestone_id", nullable = false)
    private Milestone milestone;

    @Column(name = "milestone_id", insertable = false, updatable = false)
    private UUID milestoneId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private MainProject project;

    @Column(name = "project_id", insertable = false, updatable = false)
    private UUID projectId;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "duration")
    private Integer duration; // in days

    @Column(name="execution_status")
    private String executionStatus;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_sub_activity")
    private Boolean isSubActivity = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_activity_id")
    private Activity parentActivity;

    @Column(name = "parent_activity_id", insertable = false, updatable = false)
    private UUID parentActivityId;

    @OneToMany(mappedBy = "parentActivity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Activity> subActivities = new ArrayList<>();

    @PrePersist
    @PreUpdate
    private void calculateDuration() {
        if (startDate != null && endDate != null) {
            this.duration = (int) ChronoUnit.DAYS.between(startDate, endDate);
        }
    }
}

