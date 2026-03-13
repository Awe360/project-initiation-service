
package com.saas.projectinitiation.repository;

import com.saas.projectinitiation.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, UUID> {
    Optional<Document> findByTenantIdAndId(UUID tenantId, UUID id);

}