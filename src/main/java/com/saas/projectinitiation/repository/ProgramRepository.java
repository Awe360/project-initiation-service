package com.saas.projectinitiation.repository;

import com.saas.projectinitiation.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProgramRepository extends JpaRepository<Program, UUID> {
    List<Program> findByTenantId(UUID tenantId);
    Optional<Program> findByTenantIdAndId(UUID tenantId, UUID id);
    List<Program> findByTenantIdAndPortfolioId(UUID tenantId, UUID portfolioId);
    Optional<Program> findByTenantIdAndName(UUID tenantId, String name);
    boolean existsByTenantIdAndName(UUID tenantId, String name);
}

