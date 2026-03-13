package com.saas.projectinitiation.repository;

import com.saas.projectinitiation.model.FinancialDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FinancialDetailsRepository extends JpaRepository<FinancialDetails, UUID> {
    Optional<FinancialDetails> findByTenantIdAndProjectId(UUID tenantId, UUID projectId);
}

