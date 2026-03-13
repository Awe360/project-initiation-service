package com.saas.projectinitiation.controller;

import com.saas.projectinitiation.dto.request.ContractAmendmentRequest;
import com.saas.projectinitiation.dto.response.ContractAmendmentResponse;
import com.saas.projectinitiation.dto.response.DocumentMetadata;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.service.ContractAmendmentService;
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
@RequestMapping("/api/project-initiation/amendments/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "contract-amendments")
public class ContractAmendmentController {

    private final ContractAmendmentService amendmentService;
    private final DocumentService documentService;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ContractAmendmentResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestPart("request") ContractAmendmentRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        permissionEvaluator.addContractAmendmentPermission(tenantId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(amendmentService.create(tenantId, request, file));
    }

    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<ContractAmendmentResponse>> getByContract(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID contractId) {

        permissionEvaluator.getAllContractAmendmentsPermission(tenantId);
        return ResponseEntity.ok(amendmentService.getByContractId(tenantId, contractId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractAmendmentResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {

        permissionEvaluator.getContractAmendmentByIdPermission(tenantId);
        return ResponseEntity.ok(amendmentService.getById(tenantId, id));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ContractAmendmentResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestPart("request") ContractAmendmentRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        permissionEvaluator.updateContractAmendmentPermission(tenantId);
        return ResponseEntity.ok(amendmentService.update(tenantId, id, request, file));
    }

    @GetMapping
    public ResponseEntity<List<ContractAmendmentResponse>> getAll(
            @PathVariable("tenant-id") UUID tenantId) {

        permissionEvaluator.getAllContractAmendmentsPermission(tenantId);
        return ResponseEntity.ok(amendmentService.getAll(tenantId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {

        permissionEvaluator.deleteContractAmendmentPermission(tenantId);
        amendmentService.delete(tenantId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ContractAmendmentResponse>> getByProject(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID projectId) {

        permissionEvaluator.getAllContractAmendmentsPermission(tenantId);
        return ResponseEntity.ok(amendmentService.getByProjectId(tenantId, projectId));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) throws IOException {

        permissionEvaluator.downloadContractAttachmentPermission(tenantId);

        UUID documentId = amendmentService.getAttachmentDocumentId(tenantId, id);
        if (documentId == null) {
            throw new ResourceNotFoundException("No attachment was uploaded for this amendment.");
        }

        byte[] fileBytes = documentService.download(tenantId, documentId);
        DocumentMetadata metadata = documentService.getDocumentMetadata(tenantId, documentId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + metadata.fileName() + "\"")
                .contentType(MediaType.parseMediaType(metadata.fileType()))
                .body(fileBytes);
    }
}