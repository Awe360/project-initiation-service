package com.saas.projectinitiation.model;

import com.saas.projectinitiation.enums.AmendmentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "contract_amendments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractAmendment extends Base {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    @Column(name = "contract_id", insertable = false, updatable = false)
    private UUID contractId;

    @Enumerated(EnumType.STRING)
    @Column(name = "amendment_type", nullable = false)
    private AmendmentType amendmentType;

    @Column(name = "amendment_date")
    private LocalDate amendmentDate;

    @Column(name = "amended_commencement_date")
    private LocalDate amendedCommencementDate;

    @Column(name = "amended_expire_date")
    private LocalDate amendedExpireDate;

    @Column(name = "amended_contract_period")
    private String amendedContractPeriod;

    @Column(name = "amendment_reason", columnDefinition = "TEXT")
    private String amendmentReason;

    @Column(name = "amendment_cost")
    private Long amendmentCost;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_document_id")
    private Document attachmentDocument;

}