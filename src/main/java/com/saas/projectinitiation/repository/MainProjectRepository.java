package com.saas.projectinitiation.repository;

import com.saas.projectinitiation.model.MainProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MainProjectRepository extends JpaRepository<MainProject, UUID> {
    List<MainProject> findByTenantId(UUID tenantId);
    List<MainProject> findByTenantIdAndProgramId(UUID tenantId, UUID programId);
    Optional<MainProject> findByTenantIdAndId(UUID tenantId, UUID id);
}