package com.saas.projectinitiation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "project_sequence")
@Getter
@Setter
public class ProjectSequence  {

    @Id
    private Integer id = 1;

    @Column(name = "last_number", nullable = false)
    private Long lastNumber = 0L;

}