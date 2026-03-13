package com.saas.projectinitiation.repository;

import com.saas.projectinitiation.model.ContractAmendment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContractAmendmentRepository extends JpaRepository<ContractAmendment, UUID> {

    List<ContractAmendment> findByContractId(UUID contractId);

    Optional<ContractAmendment> findByIdAndContractTenantId(UUID id, UUID tenantId);
    @Query("SELECT ca FROM ContractAmendment ca WHERE ca.contractId IN :contractIds")
    List<ContractAmendment> findAllByContractIdIn(@Param("contractIds") List<UUID> contractIds);
}