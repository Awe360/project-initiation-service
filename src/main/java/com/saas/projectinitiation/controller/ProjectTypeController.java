package com.saas.projectinitiation.controller;

import com.saas.projectinitiation.dto.request.ProjectTypeRequest;
import com.saas.projectinitiation.dto.response.ProjectTypeResponse;
import com.saas.projectinitiation.service.ProjectTypeService;
import com.saas.projectinitiation.utility.PermissionEvaluator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-initiation/project-type/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "project-type")
public class ProjectTypeController {

    private final ProjectTypeService service;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping
    public ResponseEntity<ProjectTypeResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody ProjectTypeRequest request) {
        permissionEvaluator.addProjectTypePermission(tenantId);
        return ResponseEntity.status(201).body(service.createProjectType(tenantId, request));
    }

    @GetMapping
    public ResponseEntity<List<ProjectTypeResponse>> getAll(@PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllProjectTypesPermission(tenantId);
        return ResponseEntity.ok(service.getAllProjectTypes(tenantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectTypeResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getProjectTypeByIdPermission(tenantId);
        return ResponseEntity.ok(service.getProjectTypeById(tenantId, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectTypeResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestBody ProjectTypeRequest request) {
        permissionEvaluator.updateProjectTypePermission(tenantId);
        return ResponseEntity.ok(service.updateProjectType(tenantId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.deleteProjectTypePermission(tenantId);
        service.deleteProjectType(tenantId, id);
        return ResponseEntity.noContent().build();
    }
}
