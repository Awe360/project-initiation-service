package com.saas.projectinitiation.model;

import com.saas.projectinitiation.dto.eventDto.ResourceEvent;
import com.saas.projectinitiation.utility.ResourceEventContext;
import com.saas.projectinitiation.utility.SecurityUtil;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BaseEntityListener {

    private final SecurityUtil securityUtil;

    @PrePersist
    public void setCreatedByAndUpdatedBy(Base base) {
        ResourceEvent resourceEvent = ResourceEventContext.get();
        if(resourceEvent != null && resourceEvent.getCreatedBy() != null) {
            base.setCreatedBy(resourceEvent.getCreatedBy());
        } else {
            String name = securityUtil.getName();
            base.setCreatedBy(name != null ? name : "unknown");
        }
    }

    @PreUpdate
    public void setUpdatedBy(Base base) {
        String name = securityUtil.getName();
        base.setUpdatedBy(name != null ? name : "unknown");
    }
}
