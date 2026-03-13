package com.saas.projectinitiation.repository;

import com.saas.projectinitiation.model.AssignPriority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AssignPriorityRepository extends JpaRepository<AssignPriority, UUID> {
    List<AssignPriority> findByTenantId(UUID tenantId);
    Optional<AssignPriority> findByTenantIdAndProjectId(UUID tenantId, UUID projectId);
}

