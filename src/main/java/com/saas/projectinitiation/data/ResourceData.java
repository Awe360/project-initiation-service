package com.saas.projectinitiation.data;

import com.saas.projectinitiation.dto.eventDto.ResourceEvent;
import com.saas.projectinitiation.enums.ResourceName;
import com.saas.projectinitiation.mapper.ResourceMapper;
import com.saas.projectinitiation.model.Resource;
import com.saas.projectinitiation.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ResourceData {

    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;

    public void saveResource(ResourceEvent eventResponse) {

        Set<Resource> resources = new HashSet<>();

        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_RESOURCES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_RESOURCES_BY_ROLE_NAME.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_RESOURCE_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_RESOURCE_BY_NAME.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GRANT_RESOURCE_ACCESS_TO_ROLE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.REVOKE_RESOURCE_ACCESS_FROM_ROLE.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_ACTIVITY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_ACTIVITIES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ACTIVITIES_BY_PROJECT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ACTIVITIES_BY_MILESTONE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.COPY_ACTIVITIES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ACTIVITY_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_ACTIVITY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_ACTIVITY.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_ASSIGN_PRIORITY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_ASSIGN_PRIORITIES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ASSIGN_PRIORITY_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_ASSIGN_PRIORITY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_ASSIGN_PRIORITY.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_CONTRACT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_CONTRACTS.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_CONTRACT_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_CONTRACT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_CONTRACT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DOWNLOAD_CONTRACT_ATTACHMENT.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_CONTRACT_AMENDMENT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_CONTRACT_AMENDMENTS.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_CONTRACT_AMENDMENT_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_CONTRACT_AMENDMENT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_CONTRACT_AMENDMENT.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_CONTRACT_TYPE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_CONTRACT_TYPES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_CONTRACT_TYPE_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_CONTRACT_TYPE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_CONTRACT_TYPE.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_KEY_PERSONNEL.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_KEY_PERSONNEL.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_KEY_PERSONNEL_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_KEY_PERSONNEL.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_KEY_PERSONNEL.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_PROJECT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_PROJECTS.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_PROJECT_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_PROJECTS_BY_PROGRAM.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_PROJECT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_PROJECT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DOWNLOAD_PROJECT_ATTACHMENT.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_MILESTONE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.COPY_MILESTONES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_MILESTONES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_MILESTONES_BY_PROJECT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_MILESTONE_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_MILESTONE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_MILESTONE.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_PORTFOLIO.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_PORTFOLIOS.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_PORTFOLIO_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_PORTFOLIO_BY_NAME.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_PORTFOLIO.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_PORTFOLIO.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_PROGRAM.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_PROGRAMS.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_PROGRAMS_BY_PORTFOLIO.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_PROGRAM_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_PROGRAM.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_PROGRAM.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_PROJECT_RISK.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_PROJECT_RISKS.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_PROJECT_RISK_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_PROJECT_RISK.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_PROJECT_RISK.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DOWNLOAD_PROJECT_RISK_ATTACHMENT.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_PROJECT_TEMPLATE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_PROJECT_TEMPLATES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_PROJECT_TEMPLATE_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_PROJECT_TEMPLATE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_PROJECT_TEMPLATE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DOWNLOAD_PROJECT_TEMPLATE_ATTACHMENT.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_PROJECT_TYPE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_PROJECT_TYPES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_PROJECT_TYPE_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_PROJECT_TYPE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_PROJECT_TYPE.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_RISK_CATEGORY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_RISK_CATEGORIES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_RISK_CATEGORY_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_RISK_CATEGORY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_RISK_CATEGORY.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_SUB_PROJECT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_SUB_PROJECTS.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_SUB_PROJECT_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_SUB_PROJECT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_SUB_PROJECT.getValue(), null, eventResponse));

        resources.add(resourceMapper.mapToEntity(ResourceName.ADD_TEMPLATE_NAME.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_ALL_TEMPLATE_NAMES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.GET_TEMPLATE_NAME_BY_ID.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.UPDATE_TEMPLATE_NAME.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName.DELETE_TEMPLATE_NAME.getValue(), null, eventResponse));

        List<Resource> existedResources = resourceRepository.findByTenantId(eventResponse.getTenantId());
        resourceRepository.deleteAll(existedResources);
        resourceRepository.saveAll(resources);
    }
}