package com.saas.projectinitiation.controller;

import com.saas.projectinitiation.dto.request.MainProjectRequest;
import com.saas.projectinitiation.dto.response.DocumentMetadata;
import com.saas.projectinitiation.dto.response.MainProjectResponse;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.service.DocumentService;
import com.saas.projectinitiation.service.MainProjectService;
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
@RequestMapping("/api/project-initiation/main-projects/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "main-projects")
public class MainProjectController {

    private final MainProjectService service;
    private final DocumentService documentService;
    private final PermissionEvaluator permissionEvaluator;

    @GetMapping("/next-project-number")
    public ResponseEntity<String> getNextProjectNumberPreview(
            @PathVariable("tenant-id") UUID tenantId) {
        String predictedNumber = service.getNextProjectNumberPreview();
        return ResponseEntity.ok(predictedNumber);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MainProjectResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestPart("request") MainProjectRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        permissionEvaluator.addProjectPermission(tenantId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(tenantId, request, file));
    }

    @GetMapping
    public ResponseEntity<List<MainProjectResponse>> getAll(
            @PathVariable("tenant-id") UUID tenantId) {

        permissionEvaluator.getAllProjectsPermission(tenantId);
        return ResponseEntity.ok(service.getAll(tenantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MainProjectResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {

        permissionEvaluator.getProjectByIdPermission(tenantId);
        return ResponseEntity.ok(service.getById(tenantId, id));
    }

    @GetMapping("/program/{programId}")
    public ResponseEntity<List<MainProjectResponse>> getByProgram(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID programId) {

        permissionEvaluator.getProjectsByProgramPermission(tenantId);
        return ResponseEntity.ok(service.getByProgram(tenantId, programId));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MainProjectResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestPart("request") MainProjectRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        permissionEvaluator.updateProjectPermission(tenantId);
        return ResponseEntity.ok(service.update(tenantId, id, request, file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {

        permissionEvaluator.deleteProjectPermission(tenantId);
        service.delete(tenantId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) throws IOException {

        permissionEvaluator.downloadProjectAttachmentPermission(tenantId);

        UUID documentId = service.getAttachmentDocumentId(tenantId, id);
        if (documentId == null) {
            throw new ResourceNotFoundException("No attachment was uploaded for this project.");
        }

        byte[] fileBytes = documentService.download(tenantId, documentId);
        DocumentMetadata metadata = documentService.getDocumentMetadata(tenantId, documentId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + metadata.fileName() + "\"")
                .contentType(MediaType.parseMediaType(metadata.fileType()))
                .body(fileBytes);
    }
}