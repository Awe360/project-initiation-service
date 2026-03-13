package com.saas.projectinitiation.mapper;

import com.saas.projectinitiation.dto.response.ProjectTemplateResponse;
import com.saas.projectinitiation.model.Document;
import com.saas.projectinitiation.model.ProjectTemplate;
import com.saas.projectinitiation.model.TemplateName;
import com.saas.projectinitiation.utility.SecurityUtil;
import com.saas.projectinitiation.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProjectTemplateMapper {

    private final ValidationUtil validationUtil;
    private final SecurityUtil securityUtil;

    public ProjectTemplate toEntity(UUID tenantId, UUID templateNameId, Document document) {
        TemplateName templateName = validationUtil.findTemplateNameById(tenantId, templateNameId);

        ProjectTemplate entity = new ProjectTemplate();
        entity.setTenantId(tenantId);
        entity.setTemplateName(templateName);
        entity.setAttachmentDocument(document);

        return entity;
    }

    public void updateEntityFromRequest(UUID tenantId, ProjectTemplate entity, UUID templateNameId) {
        TemplateName templateName = validationUtil.findTemplateNameById(tenantId, templateNameId);

        entity.setTemplateName(templateName);
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setUpdatedBy(securityUtil.getName());
    }

    public ProjectTemplateResponse toResponse(ProjectTemplate entity) {
        ProjectTemplateResponse res = new ProjectTemplateResponse();
        res.setId(entity.getId());
        res.setTenantId(entity.getTenantId());
        res.setCreatedAt(entity.getCreatedAt());
        res.setUpdatedAt(entity.getUpdatedAt());
        res.setCreatedBy(entity.getCreatedBy());
        res.setUpdatedBy(entity.getUpdatedBy());

        res.setTemplateNameId(entity.getTemplateNameId());
        res.setTemplateName(entity.getTemplateName().getName());
        res.setTemplateDescription(entity.getTemplateName().getDescription());

        if (entity.getAttachmentDocument() != null) {
            Document doc = entity.getAttachmentDocument();
            res.setAttachmentDocumentId(doc.getId());
            res.setAttachmentFileName(doc.getFileName());
            res.setAttachmentFileType(doc.getFileType());
            res.setAttachmentFileSize(doc.getFileSize());
        }

        return res;
    }
}