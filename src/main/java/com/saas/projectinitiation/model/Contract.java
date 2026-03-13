package com.saas.projectinitiation.model;

import com.saas.projectinitiation.enums.ContractStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "contracts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract extends Base {

    @Column(name = "contract_name", nullable = false)
    private String contractName;

    @Column(name = "contract_number", nullable = false, unique = true)
    private String contractNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private MainProject project;

    @Column(name = "client_name")
    private String clientName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_type_id", nullable = false)
    private ContractType contractType;

    @Column(name = "contract_type_id", insertable = false, updatable = false)
    private UUID contractTypeId;

    @Column(name = "total_amount", precision = 19, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "advance_payment", precision = 19, scale = 2)
    private BigDecimal advancePayment;

    @Column(name = "commencement_date", nullable = false)
    private LocalDate commencementDate;

    @Column(name = "expire_date", nullable = false)
    private LocalDate expireDate;

    @Column(name = "contract_period")
    private String contractPeriod; // in days 3 days,1 day

    @Column(name = "contract_signing_date", nullable = false)
    private LocalDate contractSigningDate;

    @Enumerated(EnumType.STRING)
    private ContractStatus status;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_document_id", foreignKey = @ForeignKey(name = "fk_contract_attachment_document"))
    private Document attachmentDocument;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContractAmendment> contractAmendments = new ArrayList<>();

}

