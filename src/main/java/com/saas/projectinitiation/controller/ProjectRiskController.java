package com.saas.projectinitiation.controller;

import com.saas.projectinitiation.dto.request.ProjectRiskRequest;
import com.saas.projectinitiation.dto.response.DocumentMetadata;
import com.saas.projectinitiation.dto.response.ProjectRiskResponse;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.service.DocumentService;
import com.saas.projectinitiation.service.ProjectRiskService;
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
@RequestMapping("/api/project-initiation/project-risk/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "project-risk")
public class ProjectRiskController {

    private final ProjectRiskService service;
    private final DocumentService documentService;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProjectRiskResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestPart("request") ProjectRiskRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        permissionEvaluator.addProjectRiskPermission(tenantId);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(tenantId, request, file));
    }

    @GetMapping
    public ResponseEntity<List<ProjectRiskResponse>> getAll(@PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllProjectRisksPermission(tenantId);
        return ResponseEntity.ok(service.getAll(tenantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectRiskResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getProjectRiskByIdPermission(tenantId);
        return ResponseEntity.ok(service.getById(tenantId, id));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProjectRiskResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestPart("request") ProjectRiskRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        permissionEvaluator.updateProjectRiskPermission(tenantId);
        return ResponseEntity.ok(service.update(tenantId, id, request, file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.deleteProjectRiskPermission(tenantId);
        service.delete(tenantId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) throws IOException {

        permissionEvaluator.downloadProjectRiskAttachmentPermission(tenantId);

        UUID documentId = service.getAttachmentDocumentId(tenantId, id);
        if (documentId == null) {
            throw new ResourceNotFoundException("No attachment was uploaded for this project risk.");
        }

        byte[] fileBytes = documentService.download(tenantId, documentId);
        DocumentMetadata metadata = documentService.getDocumentMetadata(tenantId, documentId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + metadata.fileName() + "\"")
                .contentType(MediaType.parseMediaType(metadata.fileType()))
                .body(fileBytes);
    }
}