package com.saas.projectinitiation.controller;

import com.saas.projectinitiation.dto.request.KeyPersonnelRequest;
import com.saas.projectinitiation.dto.response.KeyPersonnelResponse;
import com.saas.projectinitiation.service.KeyPersonnelService;
import com.saas.projectinitiation.utility.PermissionEvaluator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-initiation/key-personnel/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "key-personnel")
public class KeyPersonnelController {

    private final KeyPersonnelService service;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping
    public ResponseEntity<KeyPersonnelResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody KeyPersonnelRequest request) {
        permissionEvaluator.addKeyPersonnelPermission(tenantId);
        return ResponseEntity.status(201).body(service.createKeyPersonnel(tenantId, request));
    }

    @GetMapping
    public ResponseEntity<List<KeyPersonnelResponse>> getAll(@PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllKeyPersonnelPermission(tenantId);
        return ResponseEntity.ok(service.getAllKeyPersonnel(tenantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KeyPersonnelResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getKeyPersonnelByIdPermission(tenantId);
        return ResponseEntity.ok(service.getKeyPersonnelById(tenantId, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KeyPersonnelResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestBody KeyPersonnelRequest request) {
        permissionEvaluator.updateKeyPersonnelPermission(tenantId);
        return ResponseEntity.ok(service.updateKeyPersonnel(tenantId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.deleteKeyPersonnelPermission(tenantId);
        service.deleteKeyPersonnel(tenantId, id);
        return ResponseEntity.noContent().build();
    }
}
