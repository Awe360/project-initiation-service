package com.saas.projectinitiation.enums;

import lombok.Getter;

@Getter
public enum ResourceName {

    /* Resource */
    GET_ALL_RESOURCES("Get All Resources"),
    GET_RESOURCES_BY_ROLE_NAME("Get Resources by Role"),
    GET_RESOURCE_BY_ID("Get Resource Details"),
    GET_RESOURCE_BY_NAME("Get Resource by Name"),
    GRANT_RESOURCE_ACCESS_TO_ROLE("Grant Resource Access to Role"),
    REVOKE_RESOURCE_ACCESS_FROM_ROLE("Revoke Resource Access from Role"),

    /* Activity */
    ADD_ACTIVITY("Add Activity"),
    GET_ALL_ACTIVITIES("Get All Activities"),
    GET_ACTIVITIES_BY_PROJECT("Get Activities by Project"),
    GET_ACTIVITIES_BY_MILESTONE("Get Activities by Milestone"),
    COPY_ACTIVITIES("Copy Activities"),
    GET_ACTIVITY_BY_ID("Get Activity Details"),
    UPDATE_ACTIVITY("Update Activity"),
    DELETE_ACTIVITY("Delete Activity"),

    /* Assign Priority */
    ADD_ASSIGN_PRIORITY("Add Assign Priority"),
    GET_ALL_ASSIGN_PRIORITIES("Get All Assign Priorities"),
    GET_ASSIGN_PRIORITY_BY_ID("Get Assign Priority Details"),
    UPDATE_ASSIGN_PRIORITY("Update Assign Priority"),
    DELETE_ASSIGN_PRIORITY("Delete Assign Priority"),

    /* Contract */
    ADD_CONTRACT("Add Contract"),
    GET_ALL_CONTRACTS("Get All Contracts"),
    GET_CONTRACT_BY_ID("Get Contract Details"),
    UPDATE_CONTRACT("Update Contract"),
    DELETE_CONTRACT("Delete Contract"),
    DOWNLOAD_CONTRACT_ATTACHMENT("Download Contract Attachment"),

    /* Contract Amendment */
    ADD_CONTRACT_AMENDMENT("Add Contract Amendment"),
    GET_ALL_CONTRACT_AMENDMENTS("Get All Contract Amendments"),
    GET_CONTRACT_AMENDMENT_BY_ID("Get Contract Amendment Details"),
    UPDATE_CONTRACT_AMENDMENT("Update Contract Amendment"),
    DELETE_CONTRACT_AMENDMENT("Delete Contract Amendment"),

    /* Contract Type */
    ADD_CONTRACT_TYPE("Add Contract Type"),
    GET_ALL_CONTRACT_TYPES("Get All Contract Types"),
    GET_CONTRACT_TYPE_BY_ID("Get Contract Type Details"),
    UPDATE_CONTRACT_TYPE("Update Contract Type"),
    DELETE_CONTRACT_TYPE("Delete Contract Type"),

    /* Key Personnel */
    ADD_KEY_PERSONNEL("Add Key Personnel"),
    GET_ALL_KEY_PERSONNEL("Get All Key Personnel"),
    GET_KEY_PERSONNEL_BY_ID("Get Key Personnel Details"),
    UPDATE_KEY_PERSONNEL("Update Key Personnel"),
    DELETE_KEY_PERSONNEL("Delete Key Personnel"),

    /* Project */
    ADD_PROJECT("Add Project"),
    GET_ALL_PROJECTS("Get All Projects"),
    GET_PROJECT_BY_ID("Get Project Details"),
    GET_PROJECTS_BY_PROGRAM("Get Projects by Program"),
    UPDATE_PROJECT("Update Project"),
    DELETE_PROJECT("Delete Project"),
    DOWNLOAD_PROJECT_ATTACHMENT("Download Project Attachment"),

    /* Milestone */
    ADD_MILESTONE("Add Milestone"),
    COPY_MILESTONES("Copy Milestones"),
    GET_ALL_MILESTONES("Get All Milestones"),
    GET_MILESTONES_BY_PROJECT("Get Milestones by Project"),
    GET_MILESTONE_BY_ID("Get Milestone Details"),
    UPDATE_MILESTONE("Update Milestone"),
    DELETE_MILESTONE("Delete Milestone"),

    /* Portfolio */
    ADD_PORTFOLIO("Add Portfolio"),
    GET_ALL_PORTFOLIOS("Get All Portfolios"),
    GET_PORTFOLIO_BY_ID("Get Portfolio Details"),
    GET_PORTFOLIO_BY_NAME("Get Portfolio by Name"),
    UPDATE_PORTFOLIO("Update Portfolio"),
    DELETE_PORTFOLIO("Delete Portfolio"),

    /* Program */
    ADD_PROGRAM("Add Program"),
    GET_ALL_PROGRAMS("Get All Programs"),
    GET_PROGRAMS_BY_PORTFOLIO("Get Programs by Portfolio"),
    GET_PROGRAM_BY_ID("Get Program Details"),
    UPDATE_PROGRAM("Update Program"),
    DELETE_PROGRAM("Delete Program"),

    /* Project Risk */
    ADD_PROJECT_RISK("Add Project Risk"),
    GET_ALL_PROJECT_RISKS("Get All Project Risks"),
    GET_PROJECT_RISK_BY_ID("Get Project Risk Details"),
    UPDATE_PROJECT_RISK("Update Project Risk"),
    DELETE_PROJECT_RISK("Delete Project Risk"),
    DOWNLOAD_PROJECT_RISK_ATTACHMENT("Download Project Risk Attachment"),

    /* Project Template */
    ADD_PROJECT_TEMPLATE("Add Project Template"),
    GET_ALL_PROJECT_TEMPLATES("Get All Project Templates"),
    GET_PROJECT_TEMPLATE_BY_ID("Get Project Template Details"),
    UPDATE_PROJECT_TEMPLATE("Update Project Template"),
    DELETE_PROJECT_TEMPLATE("Delete Project Template"),
    DOWNLOAD_PROJECT_TEMPLATE_ATTACHMENT("Download Project Template Attachment"),

    /* Project Type */
    ADD_PROJECT_TYPE("Add Project Type"),
    GET_ALL_PROJECT_TYPES("Get All Project Types"),
    GET_PROJECT_TYPE_BY_ID("Get Project Type Details"),
    UPDATE_PROJECT_TYPE("Update Project Type"),
    DELETE_PROJECT_TYPE("Delete Project Type"),

    /* Risk Category */
    ADD_RISK_CATEGORY("Add Risk Category"),
    GET_ALL_RISK_CATEGORIES("Get All Risk Categories"),
    GET_RISK_CATEGORY_BY_ID("Get Risk Category Details"),
    UPDATE_RISK_CATEGORY("Update Risk Category"),
    DELETE_RISK_CATEGORY("Delete Risk Category"),

    /* Sub Project */
    ADD_SUB_PROJECT("Add Sub Project"),
    GET_ALL_SUB_PROJECTS("Get All Sub Projects"),
    GET_SUB_PROJECT_BY_ID("Get Sub Project Details"),
    UPDATE_SUB_PROJECT("Update Sub Project"),
    DELETE_SUB_PROJECT("Delete Sub Project"),

    /* Template Name */
    ADD_TEMPLATE_NAME("Add Template Name"),
    GET_ALL_TEMPLATE_NAMES("Get All Template Names"),
    GET_TEMPLATE_NAME_BY_ID("Get Template Name Details"),
    UPDATE_TEMPLATE_NAME("Update Template Name"),
    DELETE_TEMPLATE_NAME("Delete Template Name");

    private final String value;

    ResourceName(String value) {
        this.value = value;
    }
}