package com.saas.projectinitiation.repository;

import com.saas.projectinitiation.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PortfolioRepository extends JpaRepository<Portfolio, UUID> {
    List<Portfolio> findByTenantId(UUID tenantId);
    Optional<Portfolio> findByTenantIdAndId(UUID tenantId, UUID id);
    Optional<Portfolio> findByTenantIdAndName(UUID tenantId, String name);
    boolean existsByTenantIdAndName(UUID tenantId, String name);
}

