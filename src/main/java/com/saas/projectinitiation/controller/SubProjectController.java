package com.saas.projectinitiation.controller;

import com.saas.projectinitiation.dto.request.SubProjectRequest;
import com.saas.projectinitiation.dto.response.SubProjectResponse;
import com.saas.projectinitiation.service.SubProjectService;
import com.saas.projectinitiation.utility.PermissionEvaluator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-initiation/sub-projects/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "sub-projects")
public class SubProjectController {

    private final SubProjectService service;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping
    public ResponseEntity<List<SubProjectResponse>> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody SubProjectRequest request) {

        permissionEvaluator.addSubProjectPermission(tenantId);
        List<SubProjectResponse> created = service.create(tenantId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubProjectResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestBody SubProjectRequest.SubProjectItem item) {

        permissionEvaluator.updateSubProjectPermission(tenantId);
        return ResponseEntity.ok(service.update(tenantId, id, item));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<SubProjectResponse>> getAllByProject(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID projectId) {

        permissionEvaluator.getAllSubProjectsPermission(tenantId);
        return ResponseEntity.ok(service.getAllByProject(tenantId, projectId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubProjectResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {

        permissionEvaluator.getSubProjectByIdPermission(tenantId);
        return ResponseEntity.ok(service.getById(tenantId, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {

        permissionEvaluator.deleteSubProjectPermission(tenantId);
        service.delete(tenantId, id);
        return ResponseEntity.noContent().build();
    }
}