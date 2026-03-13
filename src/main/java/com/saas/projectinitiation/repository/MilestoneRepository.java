
package com.saas.projectinitiation.repository;

import com.saas.projectinitiation.model.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MilestoneRepository extends JpaRepository<Milestone, UUID> {
    List<Milestone> findByTenantId(UUID tenantId);
    Optional<Milestone> findByTenantIdAndId(UUID tenantId, UUID id);
    List<Milestone> findByTenantIdAndProjectId(UUID tenantId, UUID projectId);
    List<Milestone> findByTenantIdAndProjectIdAndIdIn(UUID tenantId, UUID projectId, List<UUID> ids);
}