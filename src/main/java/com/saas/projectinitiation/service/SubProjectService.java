package com.saas.projectinitiation.service;

import com.saas.projectinitiation.dto.request.SubProjectRequest;
import com.saas.projectinitiation.dto.response.SubProjectResponse;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.mapper.SubProjectMapper;
import com.saas.projectinitiation.model.MainProject;
import com.saas.projectinitiation.model.ProjectType;
import com.saas.projectinitiation.model.SubProject;
import com.saas.projectinitiation.repository.MainProjectRepository;
import com.saas.projectinitiation.repository.ProjectTypeRepository;
import com.saas.projectinitiation.repository.SubProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SubProjectService {

    private final SubProjectRepository repository;
    private final MainProjectRepository mainProjectRepository;
    private final ProjectTypeRepository projectTypeRepository;
    private final SubProjectMapper mapper;

    public List<SubProjectResponse> create(UUID tenantId, SubProjectRequest request) {
        return request.getSubProjects().stream()
                .map(item -> {
                    MainProject project = mainProjectRepository.findById(item.getProjectId())
                            .filter(p -> p.getTenantId().equals(tenantId))
                            .orElseThrow(() -> new ResourceNotFoundException("Main Project not found"));

                    ProjectType projectType = projectTypeRepository.findById(item.getProjectTypeId())
                            .filter(pt -> pt.getTenantId().equals(tenantId))
                            .orElseThrow(() -> new ResourceNotFoundException("Project Type not found"));

                    SubProject entity = mapper.toEntity(tenantId, item, project, projectType);
                    entity = repository.save(entity);
                    return mapper.toResponse(entity);
                })
                .collect(Collectors.toList());
    }

    public SubProjectResponse update(UUID tenantId, UUID id, SubProjectRequest.SubProjectItem item) {
        SubProject entity = repository.findById(id)
                .filter(s -> tenantId.equals(s.getTenantId()))
                .orElseThrow(() -> new ResourceNotFoundException("Sub Project not found"));

        mapper.updateEntity(entity, item);
        entity = repository.save(entity);

        return mapper.toResponse(entity);
    }

    public List<SubProjectResponse> getAllByProject(UUID tenantId, UUID projectId) {
        MainProject project = mainProjectRepository.findById(projectId)
                .filter(p -> p.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Main Project not found"));

        return repository.findByProjectId(projectId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public SubProjectResponse getById(UUID tenantId, UUID id) {
        SubProject entity = repository.findById(id)
                .filter(s -> tenantId.equals(s.getTenantId()))
                .orElseThrow(() -> new ResourceNotFoundException("Sub Project not found"));

        return mapper.toResponse(entity);
    }

    public void delete(UUID tenantId, UUID id) {
        SubProject entity = repository.findById(id)
                .filter(s -> tenantId.equals(s.getTenantId()))
                .orElseThrow(() -> new ResourceNotFoundException("Sub Project not found"));

        repository.delete(entity);
    }
}