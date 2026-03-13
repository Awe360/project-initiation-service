
package com.saas.projectinitiation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MilestoneResponse extends BaseResponse {
    private String milestoneName;
    private UUID subProjectId;
    private UUID projectId;
    private String projectName;
    private String description;
    private UUID copiedFromMilestoneId;
}