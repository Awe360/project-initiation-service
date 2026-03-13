package com.saas.projectinitiation.controller;

import com.saas.projectinitiation.dto.request.AssignPriorityRequest;
import com.saas.projectinitiation.dto.response.AssignPriorityResponse;
import com.saas.projectinitiation.service.AssignPriorityService;
import com.saas.projectinitiation.utility.PermissionEvaluator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-initiation/assign-priority/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "assign-priority")
public class AssignPriorityController {

    private final AssignPriorityService service;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping
    public ResponseEntity<AssignPriorityResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody AssignPriorityRequest request) {
        permissionEvaluator.addAssignPriorityPermission(tenantId);
        return ResponseEntity.status(201).body(service.createAssignPriority(tenantId, request));
    }

    @GetMapping
    public ResponseEntity<List<AssignPriorityResponse>> getAll(@PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllAssignPrioritiesPermission(tenantId);
        return ResponseEntity.ok(service.getAllAssignPriorities(tenantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignPriorityResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getAssignPriorityByIdPermission(tenantId);
        return ResponseEntity.ok(service.getAssignPriorityById(tenantId, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssignPriorityResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestBody AssignPriorityRequest request) {
        permissionEvaluator.updateAssignPriorityPermission(tenantId);
        return ResponseEntity.ok(service.updateAssignPriority(tenantId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.deleteAssignPriorityPermission(tenantId);
        service.deleteAssignPriority(tenantId, id);
        return ResponseEntity.noContent().build();
    }
}
