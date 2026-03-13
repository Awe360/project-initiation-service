package com.saas.projectinitiation.utility;

import com.saas.projectinitiation.enums.ResourceName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PermissionEvaluator {

    private final PermissionUtil permissionUtil;

    public void getAllResourcesPermission(UUID tenantId) {
        boolean isAdmin = permissionUtil.isAdmin();
        if (!isAdmin) {
            checkPermission(tenantId, ResourceName.GET_ALL_RESOURCES);
        }
    }

    public void getResourcesByRoleNamePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_RESOURCES_BY_ROLE_NAME);
    }

    public void getResourceByIdPermission(UUID tenantId) {
        boolean isAdmin = permissionUtil.isAdmin();
        if (!isAdmin) {
            checkPermission(tenantId, ResourceName.GET_RESOURCE_BY_ID);
        }
    }

    public void getResourceByNamePermission(UUID tenantId) {
        boolean isAdmin = permissionUtil.isAdmin();
        if (!isAdmin) {
            permissionUtil.isTenantUser(tenantId);
        }
    }

    public void grantResourceAccessToRolePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GRANT_RESOURCE_ACCESS_TO_ROLE);
    }

    public void revokeResourceAccessFromRolePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.REVOKE_RESOURCE_ACCESS_FROM_ROLE);
    }

    public void addActivityPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_ACTIVITY);
    }

    public void getAllActivitiesPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_ACTIVITIES);
    }

    public void getActivitiesByProjectPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ACTIVITIES_BY_PROJECT);
    }

    public void getActivitiesByMilestonePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ACTIVITIES_BY_MILESTONE);
    }

    public void copyActivitiesPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.COPY_ACTIVITIES);
    }

    public void getActivityByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ACTIVITY_BY_ID);
    }

    public void updateActivityPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_ACTIVITY);
    }

    public void deleteActivityPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_ACTIVITY);
    }

    public void addAssignPriorityPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_ASSIGN_PRIORITY);
    }

    public void getAllAssignPrioritiesPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_ASSIGN_PRIORITIES);
    }

    public void getAssignPriorityByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ASSIGN_PRIORITY_BY_ID);
    }

    public void updateAssignPriorityPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_ASSIGN_PRIORITY);
    }

    public void deleteAssignPriorityPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_ASSIGN_PRIORITY);
    }

    public void addContractPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_CONTRACT);
    }

    public void getAllContractsPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_CONTRACTS);
    }

    public void getContractByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_CONTRACT_BY_ID);
    }

    public void updateContractPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_CONTRACT);
    }

    public void deleteContractPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_CONTRACT);
    }

    public void downloadContractAttachmentPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DOWNLOAD_CONTRACT_ATTACHMENT);
    }

    public void addContractAmendmentPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_CONTRACT_AMENDMENT);
    }

    public void getAllContractAmendmentsPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_CONTRACT_AMENDMENTS);
    }

    public void getContractAmendmentByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_CONTRACT_AMENDMENT_BY_ID);
    }

    public void updateContractAmendmentPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_CONTRACT_AMENDMENT);
    }

    public void deleteContractAmendmentPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_CONTRACT_AMENDMENT);
    }

    public void addContractTypePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_CONTRACT_TYPE);
    }

    public void getAllContractTypesPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_CONTRACT_TYPES);
    }

    public void getContractTypeByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_CONTRACT_TYPE_BY_ID);
    }

    public void updateContractTypePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_CONTRACT_TYPE);
    }

    public void deleteContractTypePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_CONTRACT_TYPE);
    }

    public void addKeyPersonnelPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_KEY_PERSONNEL);
    }

    public void getAllKeyPersonnelPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_KEY_PERSONNEL);
    }

    public void getKeyPersonnelByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_KEY_PERSONNEL_BY_ID);
    }

    public void updateKeyPersonnelPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_KEY_PERSONNEL);
    }

    public void deleteKeyPersonnelPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_KEY_PERSONNEL);
    }

    public void addProjectPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_PROJECT);
    }

    public void getAllProjectsPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_PROJECTS);
    }

    public void getProjectByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_PROJECT_BY_ID);
    }

    public void getProjectsByProgramPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_PROJECTS_BY_PROGRAM);
    }

    public void updateProjectPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_PROJECT);
    }

    public void deleteProjectPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_PROJECT);
    }

    public void downloadProjectAttachmentPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DOWNLOAD_PROJECT_ATTACHMENT);
    }

    public void addMilestonePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_MILESTONE);
    }

    public void copyMilestonesPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.COPY_MILESTONES);
    }

    public void getAllMilestonesPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_MILESTONES);
    }

    public void getMilestonesByProjectPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_MILESTONES_BY_PROJECT);
    }

    public void getMilestoneByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_MILESTONE_BY_ID);
    }

    public void updateMilestonePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_MILESTONE);
    }

    public void deleteMilestonePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_MILESTONE);
    }

    public void addPortfolioPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_PORTFOLIO);
    }

    public void getAllPortfoliosPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_PORTFOLIOS);
    }

    public void getPortfolioByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_PORTFOLIO_BY_ID);
    }

    public void getPortfolioByNamePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_PORTFOLIO_BY_NAME);
    }

    public void updatePortfolioPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_PORTFOLIO);
    }

    public void deletePortfolioPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_PORTFOLIO);
    }

    public void addProgramPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_PROGRAM);
    }

    public void getAllProgramsPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_PROGRAMS);
    }

    public void getProgramsByPortfolioPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_PROGRAMS_BY_PORTFOLIO);
    }

    public void getProgramByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_PROGRAM_BY_ID);
    }

    public void updateProgramPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_PROGRAM);
    }

    public void deleteProgramPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_PROGRAM);
    }

    public void addProjectRiskPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_PROJECT_RISK);
    }

    public void getAllProjectRisksPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_PROJECT_RISKS);
    }

    public void getProjectRiskByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_PROJECT_RISK_BY_ID);
    }

    public void updateProjectRiskPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_PROJECT_RISK);
    }

    public void deleteProjectRiskPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_PROJECT_RISK);
    }

    public void downloadProjectRiskAttachmentPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DOWNLOAD_PROJECT_RISK_ATTACHMENT);
    }

    public void addProjectTemplatePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_PROJECT_TEMPLATE);
    }

    public void getAllProjectTemplatesPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_PROJECT_TEMPLATES);
    }

    public void getProjectTemplateByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_PROJECT_TEMPLATE_BY_ID);
    }

    public void updateProjectTemplatePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_PROJECT_TEMPLATE);
    }

    public void deleteProjectTemplatePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_PROJECT_TEMPLATE);
    }

    public void downloadProjectTemplateAttachmentPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DOWNLOAD_PROJECT_TEMPLATE_ATTACHMENT);
    }

    public void addProjectTypePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_PROJECT_TYPE);
    }

    public void getAllProjectTypesPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_PROJECT_TYPES);
    }

    public void getProjectTypeByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_PROJECT_TYPE_BY_ID);
    }

    public void updateProjectTypePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_PROJECT_TYPE);
    }

    public void deleteProjectTypePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_PROJECT_TYPE);
    }

    public void addRiskCategoryPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_RISK_CATEGORY);
    }

    public void getAllRiskCategoriesPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_RISK_CATEGORIES);
    }

    public void getRiskCategoryByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_RISK_CATEGORY_BY_ID);
    }

    public void updateRiskCategoryPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_RISK_CATEGORY);
    }

    public void deleteRiskCategoryPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_RISK_CATEGORY);
    }

    public void addSubProjectPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_SUB_PROJECT);
    }

    public void getAllSubProjectsPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_SUB_PROJECTS);
    }

    public void getSubProjectByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_SUB_PROJECT_BY_ID);
    }

    public void updateSubProjectPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_SUB_PROJECT);
    }

    public void deleteSubProjectPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_SUB_PROJECT);
    }

    public void addTemplateNamePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_TEMPLATE_NAME);
    }

    public void getAllTemplateNamesPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_TEMPLATE_NAMES);
    }

    public void getTemplateNameByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_TEMPLATE_NAME_BY_ID);
    }

    public void updateTemplateNamePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_TEMPLATE_NAME);
    }

    public void deleteTemplateNamePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_TEMPLATE_NAME);
    }

    private void checkPermission(UUID tenantId, ResourceName resourceName) {
        boolean hasPermission = permissionUtil.hasPermission(tenantId, resourceName.getValue());
        if (!hasPermission) {
            throw new AccessDeniedException("Access Denied");
        }
    }
}