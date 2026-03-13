package com.saas.projectinitiation.mapper;

import com.saas.projectinitiation.model.Document;
import com.saas.projectinitiation.utility.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DocumentMapper {


    public Document toDocumentEntity(UUID tenantId, MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        String contentType = file.getContentType();
        boolean isPdf = MediaType.APPLICATION_PDF_VALUE.equals(contentType);
        boolean isDocx = "application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(contentType);
        boolean isImage = contentType != null && contentType.startsWith("image/");

        if (!(isPdf || isDocx || isImage)) {
            throw new IllegalArgumentException("Only PDF, DOCX, and image files are allowed!");
        }

        Document doc = new Document();
        doc.setTenantId(tenantId);
        doc.setFileName(file.getOriginalFilename());
        doc.setFileType(contentType);
        doc.setFileSize(file.getSize());
        doc.setFileBytes(FileUtil.compressFile(file.getBytes()));

        return doc;
    }
}