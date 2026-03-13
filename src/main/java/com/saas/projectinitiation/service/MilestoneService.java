
package com.saas.projectinitiation.service;

import com.saas.projectinitiation.dto.request.CopyMilestonesRequest;
import com.saas.projectinitiation.dto.request.MilestoneRequest;
import com.saas.projectinitiation.dto.response.MilestoneResponse;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.mapper.MilestoneMapper;
import com.saas.projectinitiation.model.MainProject;
import com.saas.projectinitiation.model.Milestone;
import com.saas.projectinitiation.repository.MainProjectRepository;
import com.saas.projectinitiation.repository.MilestoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MilestoneService {

    private final MilestoneRepository repository;
    private final MainProjectRepository projectRepository;
    private final MilestoneMapper mapper;

    public MilestoneResponse create(UUID tenantId, MilestoneRequest request) {
        MainProject project = projectRepository.findByTenantIdAndId(tenantId, request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        Milestone entity = mapper.toEntity(tenantId, request, project);
        return mapper.toResponse(repository.save(entity));
    }

    public List<MilestoneResponse> copySelected(UUID tenantId, CopyMilestonesRequest request) {
        MainProject destination = projectRepository.findByTenantIdAndId(tenantId, request.getDestinationProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Destination project not found"));

        List<Milestone> sources = repository.findByTenantIdAndProjectIdAndIdIn(
                tenantId, request.getSourceProjectId(), request.getMilestoneIds());

        List<Milestone> copies = new ArrayList<>();

        for (Milestone original : sources) {
            Milestone copy = new Milestone();
            copy.setTenantId(tenantId);
            copy.setMilestoneName(original.getMilestoneName());
            copy.setDescription(original.getDescription());
            copy.setProject(destination);
            copy.setSubProjectId(null);
            copy.setCopiedFromMilestoneId(original.getId());
            copies.add(copy);
        }

        return repository.saveAll(copies).stream()
                .map(mapper::toResponse)
                .toList();
    }

    public MilestoneResponse update(UUID tenantId, UUID id, MilestoneRequest request) {
        Milestone existing = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Milestone not found"));

        MainProject project = projectRepository.findByTenantIdAndId(tenantId, request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        existing.setMilestoneName(request.getMilestoneName());
        existing.setDescription(request.getDescription());
        existing.setProject(project);
        existing.setSubProjectId(request.getSubProjectId());

        Milestone updated = repository.save(existing);
        return mapper.toResponse(updated);
    }

    public List<MilestoneResponse> getAll(UUID tenantId) {
        return repository.findByTenantId(tenantId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<MilestoneResponse> getByProject(UUID tenantId, UUID projectId) {
        return repository.findByTenantIdAndProjectId(tenantId, projectId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    public MilestoneResponse getById(UUID tenantId, UUID id) {
        return repository.findByTenantIdAndId(tenantId, id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Milestone not found"));
    }

    public void delete(UUID tenantId, UUID id) {
        Milestone milestone = repository.findByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Milestone not found"));
        repository.delete(milestone);
    }
}