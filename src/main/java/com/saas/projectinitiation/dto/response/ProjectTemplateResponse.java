
package com.saas.projectinitiation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTemplateResponse  extends BaseResponse{
    private UUID id;

    private UUID templateNameId;
    private String templateName;
    private String templateDescription;

    private UUID attachmentDocumentId;
    private String attachmentFileName;
    private String attachmentFileType;
    private Long attachmentFileSize;
}