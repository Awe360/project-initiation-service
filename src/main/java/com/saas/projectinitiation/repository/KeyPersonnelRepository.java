package com.saas.projectinitiation.repository;

import com.saas.projectinitiation.model.KeyPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface KeyPersonnelRepository extends JpaRepository<KeyPersonnel, UUID> {
    List<KeyPersonnel> findByTenantId(UUID tenantId);
    List<KeyPersonnel> findByTenantIdAndProjectId(UUID tenantId, UUID projectId);
    List<KeyPersonnel> findByTenantIdAndPortfolioId(UUID tenantId, UUID portfolioId);
    List<KeyPersonnel> findByTenantIdAndProgramId(UUID tenantId, UUID programId);
}

