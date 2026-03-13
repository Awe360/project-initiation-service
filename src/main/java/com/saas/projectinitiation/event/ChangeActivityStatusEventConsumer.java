package com.saas.projectinitiation.event;

import com.saas.projectinitiation.dto.eventDto.ChangeActivityStatusDto;
import com.saas.projectinitiation.service.ActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChangeActivityStatusEventConsumer {

    private final ActivityService activityService;

    @RabbitListener(queues = "${rabbitmq.change-activity-status-queue}")
    @Transactional
    public void consumeChangeActivityStatusEvent(ChangeActivityStatusDto dto) {
        UUID tenantId = dto.getTenantId();
        UUID activityId = dto.getActivityId();

        log.info("Received change activity status event for tenant: {}, activity: {}, new status: {}",
                tenantId, activityId, dto.getNewStatus());

//        try {
//            activityService.updateActivityExecutionStatus(tenantId, activityId, dto.getNewStatus());
//            log.info("Successfully updated activity status for activityId: {} to {}", activityId, dto.getNewStatus());
//        } catch (IllegalArgumentException e) {
//            log.warn("Invalid change activity status request: {}", e.getMessage());
//        } catch (Exception e) {
//            log.error("Failed to update activity status for activityId: {}", activityId, e);
//            throw e;
//        }
    }
}