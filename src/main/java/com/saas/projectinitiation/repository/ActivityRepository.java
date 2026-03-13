package com.saas.projectinitiation.repository;

import com.saas.projectinitiation.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {
    List<Activity> findByTenantId(UUID tenantId);
    Optional<Activity> findByTenantIdAndId(UUID tenantId, UUID id);
    List<Activity> findByTenantIdAndProjectId(UUID tenantId, UUID projectId);
    List<Activity> findByTenantIdAndMilestoneId(UUID tenantId, UUID milestoneId);
    List<Activity> findByTenantIdAndParentActivityId(UUID tenantId, UUID parentActivityId);

    List<Activity> findByTenantIdAndProjectIdAndIdIn(UUID tenantId, UUID sourceProjectId, List<UUID> activityIds);
}

