package com.saas.projectinitiation.controller;

import com.saas.projectinitiation.dto.request.RiskCategoryRequest;
import com.saas.projectinitiation.dto.response.RiskCategoryResponse;
import com.saas.projectinitiation.service.RiskCategoryService;
import com.saas.projectinitiation.utility.PermissionEvaluator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-initiation/risk-category/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "risk-category")
public class RiskCategoryController {

    private final RiskCategoryService service;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping
    public ResponseEntity<RiskCategoryResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody RiskCategoryRequest request) {
        permissionEvaluator.addRiskCategoryPermission(tenantId);
        return ResponseEntity.status(201).body(service.createRiskCategory(tenantId, request));
    }

    @GetMapping
    public ResponseEntity<List<RiskCategoryResponse>> getAll(@PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllRiskCategoriesPermission(tenantId);
        return ResponseEntity.ok(service.getAllRiskCategories(tenantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RiskCategoryResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getRiskCategoryByIdPermission(tenantId);
        return ResponseEntity.ok(service.getRiskCategoryById(tenantId, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RiskCategoryResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestBody RiskCategoryRequest request) {
        permissionEvaluator.updateRiskCategoryPermission(tenantId);
        return ResponseEntity.ok(service.updateRiskCategory(tenantId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.deleteRiskCategoryPermission(tenantId);
        service.deleteRiskCategory(tenantId, id);
        return ResponseEntity.noContent().build();
    }
}
