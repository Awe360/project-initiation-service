package com.saas.projectinitiation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponse extends BaseResponse {
    private String name;
    private UUID milestoneId;
    private String milestoneName;
    private UUID projectId;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer duration;
    private String description;
    private Boolean isSubActivity;
    private UUID parentActivityId;
}

