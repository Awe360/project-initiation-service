
package com.saas.projectinitiation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "milestones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Milestone extends Base {

    @Column(name = "milestone_name", nullable = false)
    private String milestoneName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "copied_from_milestone_id")
    private UUID copiedFromMilestoneId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private MainProject project;

    @Column(name = "sub_project_id")
    private UUID subProjectId;

    @OneToMany(mappedBy = "milestone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Activity> activities = new ArrayList<>();
}