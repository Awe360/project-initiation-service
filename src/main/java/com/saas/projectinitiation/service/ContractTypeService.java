package com.saas.projectinitiation.service;

import com.saas.projectinitiation.dto.request.ContractTypeRequest;
import com.saas.projectinitiation.dto.response.ContractTypeResponse;
import com.saas.projectinitiation.exception.ResourceNotFoundException;
import com.saas.projectinitiation.mapper.ContractTypeMapper;
import com.saas.projectinitiation.model.ContractType;
import com.saas.projectinitiation.repository.ContractTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContractTypeService {

    private final ContractTypeRepository contractTypeRepository;
    private final ContractTypeMapper contractTypeMapper;

    @Transactional
    public ContractTypeResponse createContractType(UUID tenantId, ContractTypeRequest request) {
        ContractType contractType = contractTypeMapper.mapToEntity(request, tenantId);
        contractType = contractTypeRepository.save(contractType);
        return contractTypeMapper.mapToDto(contractType);
    }

    public List<ContractTypeResponse> getAllContractTypes(UUID tenantId) {
        return contractTypeRepository.findByTenantId(tenantId).stream()
                .map(contractTypeMapper::mapToDto)
                .toList();
    }

    public ContractTypeResponse getContractTypeById(UUID tenantId, UUID id) {
        ContractType contractType = contractTypeRepository.findById(id)
                .filter(ct -> ct.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Contract type not found with id '" + id + "'"));
        return contractTypeMapper.mapToDto(contractType);
    }

    @Transactional
    public ContractTypeResponse updateContractType(UUID tenantId, UUID id, ContractTypeRequest request) {
        ContractType contractType = contractTypeRepository.findById(id)
                .filter(ct -> ct.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Contract type not found with id '" + id + "'"));

        contractType.setContractType(request.getContractType());
        contractType.setDescription(request.getDescription());

        contractType = contractTypeRepository.save(contractType);
        return contractTypeMapper.mapToDto(contractType);
    }

    @Transactional
    public void deleteContractType(UUID tenantId, UUID id) {
        ContractType contractType = contractTypeRepository.findById(id)
                .filter(ct -> ct.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException("Contract type not found with id '" + id + "'"));
        contractTypeRepository.delete(contractType);
    }
}





