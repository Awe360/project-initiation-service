
package com.saas.projectinitiation.repository;

import com.saas.projectinitiation.model.ProjectTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectTemplateRepository extends JpaRepository<ProjectTemplate, UUID> {

    List<ProjectTemplate> findByTenantId(UUID tenantId);

    Optional<ProjectTemplate> findByTenantIdAndId(UUID tenantId, UUID id);

    boolean existsByTenantIdAndId(UUID tenantId, UUID id);
    boolean existsByTenantIdAndTemplateNameId(UUID tenantId, UUID templateNameId);
}