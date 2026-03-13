package com.saas.projectinitiation.controller;

import com.saas.projectinitiation.dto.request.ProgramRequest;
import com.saas.projectinitiation.dto.response.ProgramResponse;
import com.saas.projectinitiation.service.ProgramService;
import com.saas.projectinitiation.utility.PermissionEvaluator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-initiation/program/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "program")
public class ProgramController {

    private final ProgramService service;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping
    public ResponseEntity<ProgramResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody ProgramRequest request) {
        permissionEvaluator.addProgramPermission(tenantId);
        return ResponseEntity.status(201).body(service.create(tenantId, request));
    }

    @GetMapping
    public ResponseEntity<List<ProgramResponse>> getAll(@PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllProgramsPermission(tenantId);
        return ResponseEntity.ok(service.getAll(tenantId));
    }

    @GetMapping("/portfolio/{portfolioId}")
    public ResponseEntity<List<ProgramResponse>> getAllByPortfolio(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID portfolioId) {
        permissionEvaluator.getAllProgramsPermission(tenantId);
        return ResponseEntity.ok(service.getAllByPortfolio(tenantId, portfolioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getProgramByIdPermission(tenantId);
        return ResponseEntity.ok(service.getById(tenantId, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestBody ProgramRequest request) {
        permissionEvaluator.updateProgramPermission(tenantId);
        return ResponseEntity.ok(service.update(tenantId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.deleteProgramPermission(tenantId);
        service.delete(tenantId, id);
        return ResponseEntity.noContent().build();
    }
}


