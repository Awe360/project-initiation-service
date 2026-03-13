package com.saas.projectinitiation.controller;

import com.saas.projectinitiation.dto.request.ContractTypeRequest;
import com.saas.projectinitiation.dto.response.ContractTypeResponse;
import com.saas.projectinitiation.service.ContractTypeService;
import com.saas.projectinitiation.utility.PermissionEvaluator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-initiation/contract-type/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "contract-type")
public class ContractTypeController {

    private final ContractTypeService service;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping
    public ResponseEntity<ContractTypeResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestBody ContractTypeRequest request) {
        permissionEvaluator.addContractTypePermission(tenantId);
        return ResponseEntity.status(201).body(service.createContractType(tenantId, request));
    }

    @GetMapping
    public ResponseEntity<List<ContractTypeResponse>> getAll(@PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllContractTypesPermission(tenantId);
        return ResponseEntity.ok(service.getAllContractTypes(tenantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractTypeResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getContractTypeByIdPermission(tenantId);
        return ResponseEntity.ok(service.getContractTypeById(tenantId, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContractTypeResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestBody ContractTypeRequest request) {
        permissionEvaluator.updateContractTypePermission(tenantId);
        return ResponseEntity.ok(service.updateContractType(tenantId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.deleteContractTypePermission(tenantId);
        service.deleteContractType(tenantId, id);
        return ResponseEntity.noContent().build();
    }
}
