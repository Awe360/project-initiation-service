package com.saas.projectinitiation.event;

import com.saas.projectinitiation.dto.eventDto.ChangeProjectStatusDto;
import com.saas.projectinitiation.service.MainProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChangeProjectStatusEventConsumer {

    private final MainProjectService mainProjectService;

    @RabbitListener(queues = "${rabbitmq.change-project-status-queue}")
    @Transactional
    public void consumeChangeProjectStatusEvent(ChangeProjectStatusDto dto) {
        UUID tenantId = dto.getTenantId();
        UUID projectId = dto.getProjectId();

        log.info("Received change project status event for tenant: {}, project: {}, new status: {}",
                tenantId, projectId, dto.getNewStatus());

//        try {
//            mainProjectService.updateProjectExecutionStatus(tenantId, projectId, dto.getNewStatus());
//            log.info("Successfully updated project status for projectId: {} to {}", projectId, dto.getNewStatus());
//        } catch (IllegalArgumentException e) {
//            log.warn("Invalid change project status request: {}", e.getMessage());
//        } catch (Exception e) {
//            log.error("Failed to update project status for projectId: {}", projectId, e);
//            throw e;
//        }
    }
}