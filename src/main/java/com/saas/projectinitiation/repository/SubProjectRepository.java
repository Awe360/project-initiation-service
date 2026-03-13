package com.saas.projectinitiation.repository;

import com.saas.projectinitiation.model.SubProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SubProjectRepository extends JpaRepository<SubProject, UUID> {
    List<SubProject> findByProjectId(UUID projectId);
}