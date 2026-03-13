
package com.saas.projectinitiation.controller;

import com.saas.projectinitiation.dto.request.CopyMilestonesRequest;
import com.saas.projectinitiation.dto.request.MilestoneRequest;
import com.saas.projectinitiation.dto.response.MilestoneResponse;
import com.saas.projectinitiation.service.MilestoneService;
import com.saas.projectinitiation.utility.PermissionEvaluator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-initiation/milestone/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "milestone")
public class MilestoneController {

    private final MilestoneService service;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping
    public ResponseEntity<MilestoneResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody MilestoneRequest request) {
        permissionEvaluator.addMilestonePermission(tenantId);
        return ResponseEntity.status(201).body(service.create(tenantId, request));
    }

    @PostMapping("/copy-selected")
    public ResponseEntity<List<MilestoneResponse>> copySelected(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody CopyMilestonesRequest request) {
        permissionEvaluator.addMilestonePermission(tenantId);
        return ResponseEntity.status(201).body(service.copySelected(tenantId, request));
    }



    @GetMapping
    public ResponseEntity<List<MilestoneResponse>> getAll(
            @PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllMilestonesPermission(tenantId);
        return ResponseEntity.ok(service.getAll(tenantId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<MilestoneResponse>> getByProject(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID projectId) {
        permissionEvaluator.getMilestonesByProjectPermission(tenantId);
        return ResponseEntity.ok(service.getByProject(tenantId, projectId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MilestoneResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getMilestoneByIdPermission(tenantId);
        return ResponseEntity.ok(service.getById(tenantId, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MilestoneResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestBody MilestoneRequest request) {
        permissionEvaluator.updateMilestonePermission(tenantId);
        return ResponseEntity.ok(service.update(tenantId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.deleteMilestonePermission(tenantId);
        service.delete(tenantId, id);
        return ResponseEntity.noContent().build();
    }
}