package com.saas.projectinitiation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioResponse extends BaseResponse {
    private String name;
    private String description;
    private String status;
}

