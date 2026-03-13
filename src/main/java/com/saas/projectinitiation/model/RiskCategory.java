package com.saas.projectinitiation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "risk_categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiskCategory extends Base {

    @Column(nullable = false)
    private String typeName;

    @Column(columnDefinition = "TEXT")
    private String description;
}

