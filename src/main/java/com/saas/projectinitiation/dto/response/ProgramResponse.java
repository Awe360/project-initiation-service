package com.saas.projectinitiation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramResponse extends BaseResponse {
    private String name;
    private UUID portfolioId;
    private String portfolioName;
    private String remark;
    private String status;
}

