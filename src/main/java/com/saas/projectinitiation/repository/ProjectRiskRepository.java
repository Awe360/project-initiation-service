package com.saas.projectinitiation.repository;

import com.saas.projectinitiation.model.ProjectRisk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRiskRepository extends JpaRepository<ProjectRisk, UUID> {

    List<ProjectRisk> findByTenantId(UUID tenantId);

    List<ProjectRisk> findByTenantIdAndProjectId(UUID tenantId, UUID projectId);

    List<ProjectRisk> findByTenantIdAndPortfolioId(UUID tenantId, UUID portfolioId);

    List<ProjectRisk> findByTenantIdAndProgramId(UUID tenantId, UUID programId);

    Optional<ProjectRisk> findByTenantIdAndId(UUID tenantId, UUID id);

    boolean existsByTenantIdAndId(UUID tenantId, UUID id);
}