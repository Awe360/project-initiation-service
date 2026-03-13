
package com.saas.projectinitiation.repository;

import com.saas.projectinitiation.model.TemplateName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TemplateNameRepository extends JpaRepository<TemplateName, UUID> {

    List<TemplateName> findByTenantId(UUID tenantId);

    Optional<TemplateName> findByTenantIdAndId(UUID tenantId, UUID id);

    boolean existsByTenantIdAndNameIgnoreCase(UUID tenantId, String name);

}