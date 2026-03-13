package com.saas.projectinitiation.controller;

import com.saas.projectinitiation.dto.request.ActivityRequest;
import com.saas.projectinitiation.dto.request.CopyActivitiesRequest;
import com.saas.projectinitiation.dto.response.ActivityResponse;
import com.saas.projectinitiation.service.ActivityService;
import com.saas.projectinitiation.utility.PermissionEvaluator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-initiation/activity/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "activity")
public class ActivityController {

    private final ActivityService service;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping
    public ResponseEntity<ActivityResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody ActivityRequest request) {
        permissionEvaluator.addActivityPermission(tenantId);
        return ResponseEntity.status(201).body(service.create(tenantId, request));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getAll(@PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllActivitiesPermission(tenantId);
        return ResponseEntity.ok(service.getAll(tenantId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ActivityResponse>> getByProject(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID projectId) {
        permissionEvaluator.getActivitiesByProjectPermission(tenantId);
        return ResponseEntity.ok(service.getByProject(tenantId, projectId));
    }

    @GetMapping("/milestone/{milestoneId}")
    public ResponseEntity<List<ActivityResponse>> getByMilestone(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID milestoneId) {
        permissionEvaluator.getActivitiesByMilestonePermission(tenantId);
        return ResponseEntity.ok(service.getByMilestone(tenantId, milestoneId));
    }

    @PostMapping("/copy-selected")
    public ResponseEntity<List<ActivityResponse>> copySelected(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody CopyActivitiesRequest request) {
//        permissionEvaluator.copyActivitiesPermission(tenantId);
        List<ActivityResponse> copiedActivities = service.copySelected(tenantId, request);
        return ResponseEntity.status(201).body(copiedActivities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getActivityByIdPermission(tenantId);
        return ResponseEntity.ok(service.getById(tenantId, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestBody ActivityRequest request) {
        permissionEvaluator.updateActivityPermission(tenantId);
        return ResponseEntity.ok(service.update(tenantId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.deleteActivityPermission(tenantId);
        service.delete(tenantId, id);
        return ResponseEntity.noContent().build();
    }
}


