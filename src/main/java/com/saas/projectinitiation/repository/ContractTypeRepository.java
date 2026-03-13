package com.saas.projectinitiation.repository;

import com.saas.projectinitiation.model.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContractTypeRepository extends JpaRepository<ContractType, UUID> {
    List<ContractType> findByTenantId(UUID tenantId);
    Optional<ContractType> findByTenantIdAndId(UUID tenantId, UUID id);
}

