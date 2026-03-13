
package com.saas.projectinitiation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "project_templates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTemplate extends Base {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_name_id", nullable = false)
    private TemplateName templateName;

    @Column(name = "template_name_id", insertable = false, updatable = false)
    private UUID templateNameId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_document_id", foreignKey = @ForeignKey(name = "fk_project_template_attachment_document"))
    private Document attachmentDocument;
}