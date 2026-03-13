package com.saas.projectinitiation.service;

import com.saas.projectinitiation.dto.request.ProjectTypeRequest;
import com.saas.projectinitiation.dto.response.ProjectTypeResponse;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.mapper.ProjectTypeMapper;
import com.saas.projectinitiation.model.ProjectType;
import com.saas.projectinitiation.repository.ProjectTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectTypeService {

    private final ProjectTypeRepository projectTypeRepository;
    private final ProjectTypeMapper projectTypeMapper;

    @Transactional
    public ProjectTypeResponse createProjectType(UUID tenantId, ProjectTypeRequest request) {
        ProjectType projectType = projectTypeMapper.mapToEntity(request, tenantId);
        projectType = projectTypeRepository.save(projectType);
        return projectTypeMapper.mapToDto(projectType);
    }

    public List<ProjectTypeResponse> getAllProjectTypes(UUID tenantId) {
        return projectTypeRepository.findByTenantId(tenantId).stream()
                .map(projectTypeMapper::mapToDto)
                .toList();
    }

    public ProjectTypeResponse getProjectTypeById(UUID tenantId, UUID id) {
        ProjectType projectType = projectTypeRepository.findById(id)
                .filter(pt -> pt.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Project type not found with id '" + id + "'"));
        return projectTypeMapper.mapToDto(projectType);
    }

    @Transactional
    public ProjectTypeResponse updateProjectType(UUID tenantId, UUID id, ProjectTypeRequest request) {
        ProjectType projectType = projectTypeRepository.findById(id)
                .filter(pt -> pt.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Project type not found with id '" + id + "'"));

        projectType.setProjectType(request.getProjectType());
        projectType = projectTypeRepository.save(projectType);
        return projectTypeMapper.mapToDto(projectType);
    }

    @Transactional
    public void deleteProjectType(UUID tenantId, UUID id) {
        ProjectType projectType = projectTypeRepository.findById(id)
                .filter(pt -> pt.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Project type not found with id '" + id + "'"));
        projectTypeRepository.delete(projectType);
    }
}





