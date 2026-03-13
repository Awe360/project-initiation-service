package com.saas.projectinitiation.controller;

import com.saas.projectinitiation.dto.request.PortfolioRequest;
import com.saas.projectinitiation.dto.response.PortfolioResponse;
import com.saas.projectinitiation.service.PortfolioService;
import com.saas.projectinitiation.utility.PermissionEvaluator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-initiation/portfolio/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "portfolio")
public class PortfolioController {

    private final PortfolioService service;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping
    public ResponseEntity<PortfolioResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody PortfolioRequest request) {
        permissionEvaluator.addPortfolioPermission(tenantId);
        return ResponseEntity.status(201).body(service.create(tenantId, request));
    }

    @GetMapping
    public ResponseEntity<List<PortfolioResponse>> getAll(@PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllPortfoliosPermission(tenantId);
        return ResponseEntity.ok(service.getAll(tenantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PortfolioResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getPortfolioByIdPermission(tenantId);
        return ResponseEntity.ok(service.getById(tenantId, id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PortfolioResponse> getByName(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable String name) {
        permissionEvaluator.getPortfolioByNamePermission(tenantId);
        return ResponseEntity.ok(service.getByName(tenantId, name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PortfolioResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestBody PortfolioRequest request) {
        permissionEvaluator.updatePortfolioPermission(tenantId);
        return ResponseEntity.ok(service.update(tenantId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.deletePortfolioPermission(tenantId);
        service.delete(tenantId, id);
        return ResponseEntity.noContent().build();
    }
}


