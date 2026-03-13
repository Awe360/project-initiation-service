package com.saas.projectinitiation.repository;

import com.saas.projectinitiation.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContractRepository extends JpaRepository<Contract, UUID> {

    List<Contract> findByTenantIdAndProjectId(UUID tenantId, UUID projectId);

    List<Contract> findByTenantId(UUID tenantId);

    Optional<Contract> findByContractNumberAndTenantId(String contractNumber, UUID tenantId);
}