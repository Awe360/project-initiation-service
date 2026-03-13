package com.saas.projectinitiation.repository;

import com.saas.projectinitiation.model.ProjectType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectTypeRepository extends JpaRepository<ProjectType, UUID> {
    List<ProjectType> findByTenantId(UUID tenantId);
    Optional<ProjectType> findByTenantIdAndId(UUID tenantId, UUID id);
}

