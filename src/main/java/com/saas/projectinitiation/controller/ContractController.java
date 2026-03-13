package com.saas.projectinitiation.controller;

import com.saas.projectinitiation.dto.request.ContractRequest;
import com.saas.projectinitiation.dto.response.ContractResponse;
import com.saas.projectinitiation.dto.response.DocumentMetadata;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.service.ContractService;
import com.saas.projectinitiation.service.DocumentService;
import com.saas.projectinitiation.utility.PermissionEvaluator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project-initiation/contracts/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "contracts")
public class ContractController {

    private final ContractService service;
    private final DocumentService documentService;
    private final PermissionEvaluator permissionEvaluator;

    @GetMapping("/next-contract-number")
    public ResponseEntity<String> getNextContractNumberPreview(
            @PathVariable("tenant-id") UUID tenantId) {
        String predictedNumber = service.getNextContractNumberPreview();
        return ResponseEntity.ok(predictedNumber);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ContractResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestPart("request") ContractRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        permissionEvaluator.addContractPermission(tenantId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(tenantId, request, file));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ContractResponse>> getAllByProject(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID projectId) {
        permissionEvaluator.getAllContractsPermission(tenantId);
        return ResponseEntity.ok(service.getAllByProject(tenantId, projectId));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ContractResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getContractByIdPermission(tenantId);
        return ResponseEntity.ok(service.getById(tenantId, id));
    }

    @GetMapping("/by-name/{contractName}")
    public ResponseEntity<?> getByContractName(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable String contractName) {
        permissionEvaluator.getContractByIdPermission(tenantId);
        return ResponseEntity.ok(service.getByContractName(tenantId, contractName));
    }

    @GetMapping
    public ResponseEntity<List<ContractResponse>> getAll(
            @PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllContractsPermission(tenantId);
        return ResponseEntity.ok(service.getAll(tenantId));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ContractResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestPart("request") ContractRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        permissionEvaluator.updateContractPermission(tenantId);
        return ResponseEntity.ok(service.update(tenantId, id, request, file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.deleteContractPermission(tenantId);
        service.delete(tenantId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) throws IOException {
        permissionEvaluator.downloadContractAttachmentPermission(tenantId);

        UUID documentId = service.getAttachmentDocumentId(tenantId, id);
        if (documentId == null) {
            throw new ResourceNotFoundException("No attachment was uploaded for this contract.");
        }

        byte[] fileBytes = documentService.download(tenantId, documentId);
        DocumentMetadata metadata = documentService.getDocumentMetadata(tenantId, documentId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + metadata.fileName() + "\"")
                .contentType(MediaType.parseMediaType(metadata.fileType()))
                .body(fileBytes);
    }
}