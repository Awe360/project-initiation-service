package com.saas.projectinitiation.repository;

import com.saas.projectinitiation.model.RiskCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RiskCategoryRepository extends JpaRepository<RiskCategory, UUID> {
    List<RiskCategory> findByTenantId(UUID tenantId);
    Optional<RiskCategory>findByTenantIdAndId(UUID tenantId, UUID id);
}

