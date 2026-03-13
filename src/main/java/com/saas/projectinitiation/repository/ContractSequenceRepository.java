package com.saas.projectinitiation.repository;

import com.saas.projectinitiation.model.ContractSequence;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface ContractSequenceRepository extends JpaRepository<ContractSequence, Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT cs FROM ContractSequence cs WHERE cs.id = 1")
    ContractSequence lockAndGetSequence();
}