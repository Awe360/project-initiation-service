package com.saas.projectinitiation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(
        name = "template_names",
        uniqueConstraints = @UniqueConstraint(columnNames = {"tenant_id", "name"})
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateName extends Base {

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}