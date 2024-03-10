package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.NotificationType;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.TrafficExceededCause;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities.ServiceTopicDefinitionEntity;
import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.IServiceTopicMessageCounterService;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.AlertDefinitionBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.MessageBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.*;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.*;

@Component
public class KafkaMessageHandlerWorkflow implements IKafkaMessageHandlerWorkflow {
    private final ServiceTopicDefinitionFilterer serviceTopicDefinitionFilterer;
    private final IMonitoredMessagesFilterer monitoredMessagesFilterer;
    private final IRegistrationService registrationService;
    private final IMessageSizeValidator messageSizeValidator;
    private final IServiceTrafficNotificationService notificationService;
    private final IServiceTopicMessageCounterService messageCounterService;

    public KafkaMessageHandlerWorkflow(ServiceTopicDefinitionFilterer serviceTopicDefinitionFilterer, IMonitoredMessagesFilterer monitoredMessagesFilterer, IRegistrationService registrationService, IMessageSizeValidator messageSizeValidator, IServiceTrafficNotificationService notificationService, IServiceTopicMessageCounterService messageCounterService) {
        this.serviceTopicDefinitionFilterer = serviceTopicDefinitionFilterer;
        this.monitoredMessagesFilterer = monitoredMessagesFilterer;
        this.registrationService = registrationService;
        this.messageSizeValidator = messageSizeValidator;
        this.notificationService = notificationService;
        this.messageCounterService = messageCounterService;
    }

    @Override
    public void HandleMessage(MessageBoundary messageBoundary) {
        if (serviceTopicDefinitionFilterer.IsMessageIsRegistrationMessage(messageBoundary)) {
            var alertDefinitionBoundary = retrieveAlertDefinitions(messageBoundary);
            registrationService.registerServices(messageBoundary, alertDefinitionBoundary).then().block();
        } else {
            monitoredMessagesFilterer.getMonitoredServices(messageBoundary)
                    .flatMap(service -> handleMonitoredMessage(messageBoundary, service))
                    .then()
                    .block();
        }

    }

    private Mono<Void> handleMonitoredMessage(MessageBoundary messageBoundary, ServiceTopicDefinitionEntity serviceTopicDefinition) {
        return messageCounterService.incrementCounter(serviceTopicDefinition.serviceName())
                .filter(counter -> !messageSizeValidator.isSizeValid(messageBoundary, serviceTopicDefinition))
                .flatMap(counter -> notificationService.sendTrafficExceededNotifications(serviceTopicDefinition, TrafficExceededCause.Size));
    }

    private List<AlertDefinitionBoundary> retrieveAlertDefinitions(MessageBoundary messageBoundary) {
        var alertDefinitions = (List<Map<String, Object>>) messageBoundary.getMessageDetails().get("alertDefinitions");
        var alertDefinitionBoundary = new ArrayList<AlertDefinitionBoundary>();

        for (var alertDefinitionMap : alertDefinitions) {
            var notificationTypeValue = alertDefinitionMap.get("notificationType").toString();
            var details = (Map<String, Object>) alertDefinitionMap.get("details");
            var notificationType = NotificationType.valueOf(notificationTypeValue);

            var boundary = new AlertDefinitionBoundary(notificationType, details);
            alertDefinitionBoundary.add(boundary);
        }
        return alertDefinitionBoundary;
    }
}
