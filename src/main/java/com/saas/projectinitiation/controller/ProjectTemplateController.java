package com.saas.projectinitiation.controller;

import com.saas.projectinitiation.dto.request.ProjectTemplateRequest;
import com.saas.projectinitiation.dto.response.DocumentMetadata;
import com.saas.projectinitiation.dto.response.ProjectTemplateResponse;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.service.DocumentService;
import com.saas.projectinitiation.service.ProjectTemplateService;
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
@RequestMapping("/api/project-initiation/project-template/{tenant-id}")
@RequiredArgsConstructor
@Tag(name = "project-template")
public class ProjectTemplateController {

    private final ProjectTemplateService service;
    private final DocumentService documentService;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProjectTemplateResponse> create(
            @PathVariable("tenant-id") UUID tenantId,
            @Valid @RequestPart("request") ProjectTemplateRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        permissionEvaluator.addProjectTemplatePermission(tenantId);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(tenantId, request, file));
    }

    @GetMapping
    public ResponseEntity<List<ProjectTemplateResponse>> getAll(@PathVariable("tenant-id") UUID tenantId) {
        permissionEvaluator.getAllProjectTemplatesPermission(tenantId);
        return ResponseEntity.ok(service.getAll(tenantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectTemplateResponse> getById(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.getProjectTemplateByIdPermission(tenantId);
        return ResponseEntity.ok(service.getById(tenantId, id));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProjectTemplateResponse> update(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestPart("request") ProjectTemplateRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        permissionEvaluator.updateProjectTemplatePermission(tenantId);
        return ResponseEntity.ok(service.update(tenantId, id, request, file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) {
        permissionEvaluator.deleteProjectTemplatePermission(tenantId);
        service.delete(tenantId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(
            @PathVariable("tenant-id") UUID tenantId,
            @PathVariable UUID id) throws IOException {

        permissionEvaluator.downloadProjectTemplateAttachmentPermission(tenantId);

        UUID documentId = service.getAttachmentDocumentId(tenantId, id);
        if (documentId == null) {
            throw new ResourceNotFoundException("No attachment was uploaded for this project template.");
        }

        byte[] fileBytes = documentService.download(tenantId, documentId);
        DocumentMetadata metadata = documentService.getDocumentMetadata(tenantId, documentId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + metadata.fileName() + "\"")
                .contentType(MediaType.parseMediaType(metadata.fileType()))
                .body(fileBytes);
    }
}