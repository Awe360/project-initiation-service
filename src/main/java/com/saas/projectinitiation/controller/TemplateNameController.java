package com.saas.projectinitiation.controller;

import com.saas.projectinitiation.dto.request.TemplateNameRequest;
import com.saas.projectinitiation.dto.response.TemplateNameResponse;
import com.saas.projectinitiation.service.TemplateNameService;
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
@RequestMapping("/api/project-initiation/template-name/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "template-name")
public class TemplateNameController {

    private final TemplateNameService service;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping
    public ResponseEntity<TemplateNameResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody TemplateNameRequest request) {

        permissionEvaluator.addTemplateNamePermission(tenantId);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(tenantId, request));
    }

    @GetMapping
    public ResponseEntity<List<TemplateNameResponse>> getAll(@PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllTemplateNamesPermission(tenantId);
        return ResponseEntity.ok(service.getAll(tenantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemplateNameResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {

        permissionEvaluator.getTemplateNameByIdPermission(tenantId);
        return ResponseEntity.ok(service.getById(tenantId, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TemplateNameResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestBody TemplateNameRequest request) {

        permissionEvaluator.updateTemplateNamePermission(tenantId);
        return ResponseEntity.ok(service.update(tenantId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {

        permissionEvaluator.deleteTemplateNamePermission(tenantId);
        service.delete(tenantId, id);
        return ResponseEntity.noContent().build();
    }
}