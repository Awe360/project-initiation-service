package com.saas.projectinitiation.mapper;

import com.saas.projectinitiation.dto.request.ContractTypeRequest;
import com.saas.projectinitiation.dto.response.ContractTypeResponse;
import com.saas.projectinitiation.model.ContractType;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ContractTypeMapper {

    public ContractType mapToEntity(ContractTypeRequest request, UUID tenantId) {
        ContractType contractType = new ContractType();
        contractType.setContractType(request.getContractType());
        contractType.setDescription(request.getDescription());
        contractType.setTenantId(tenantId);
        return contractType;
    }

    public ContractTypeResponse mapToDto(ContractType contractType) {
        ContractTypeResponse response = new ContractTypeResponse();
        response.setId(contractType.getId());
        response.setContractType(contractType.getContractType());
        response.setDescription(contractType.getDescription());
        response.setTenantId(contractType.getTenantId());
        response.setCreatedAt(contractType.getCreatedAt());
        response.setCreatedBy(contractType.getCreatedBy());
        response.setUpdatedAt(contractType.getUpdatedAt());
        response.setUpdatedBy(contractType.getUpdatedBy());
        return response;
    }
}

