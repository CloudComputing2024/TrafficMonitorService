package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.NotificationType;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.TrafficExceededCause;
import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.IServiceTopicMessageCounterService;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.AlertDefinitionBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.MessageBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class KafkaMessageHandlerWorkflow implements IKafkaMessageHandlerWorkflow {
    private ServiceTopicDefinitionFilterer serviceTopicDefinitionFilterer;
    private IMonitoredMessagesFilterer monitoredMessagesFilterer;
    private IRegistrationService registrationService;
    private IMessageSizeValidator messageSizeValidator;
    private IServiceTrafficNotificationService notificationService;
    private IServiceTopicMessageCounterService messageCounterService;

    public KafkaMessageHandlerWorkflow(ServiceTopicDefinitionFilterer serviceTopicDefinitionFilterer,
                                       IMonitoredMessagesFilterer monitoredMessagesFilterer,
                                       IRegistrationService registrationService,
                                       IMessageSizeValidator messageSizeValidator,
                                       IServiceTrafficNotificationService notificationService,
                                       IServiceTopicMessageCounterService messageCounterService) {
        this.serviceTopicDefinitionFilterer = serviceTopicDefinitionFilterer;
        this.monitoredMessagesFilterer = monitoredMessagesFilterer;
        this.registrationService = registrationService;
        this.messageSizeValidator = messageSizeValidator;
        this.notificationService = notificationService;
        this.messageCounterService = messageCounterService;
    }

    @Override
    public void HandleMessage(MessageBoundary messageBoundary) {
        var serviceName = Arrays.stream(messageBoundary
                        .getExternalReferences())
                .toList()
                .get(0)
                .getService();

        if(serviceTopicDefinitionFilterer.IsMessageIsRegistrationMessage(messageBoundary)){

            List<AlertDefinitionBoundary> alertDefinitionBoundary = RetrieveAlertDefinitions(messageBoundary);
            registrationService.RegisterService(serviceName,alertDefinitionBoundary);
            return;
        };

        if(monitoredMessagesFilterer.IsMessageInMonitoring(messageBoundary)){
            if(!messageSizeValidator.IsSizeValid(messageBoundary)){
                notificationService.sendTrafficExceededNotifications(serviceName, TrafficExceededCause.Size);
            }

            messageCounterService.incrementCounter(serviceName);
        }

    }

    private List<AlertDefinitionBoundary> RetrieveAlertDefinitions(MessageBoundary messageBoundary){
        List<Map<String, Object>> alertDefinitions =
                (List<Map<String, Object>>) messageBoundary.getMessageDetails().get("alertDefinitions");

        List<AlertDefinitionBoundary> alertDefinitionBoundary = new ArrayList<>();
        for (Map<String, Object> alertDefinition : alertDefinitions) {
            String notificationType = (String) alertDefinition.get("notificationType");
            Map<String, String> parameters = (Map<String, String>) alertDefinition.get("details");

            AlertDefinitionBoundary boundary = new AlertDefinitionBoundary(NotificationType.valueOf(notificationType), parameters);
            alertDefinitionBoundary.add(boundary);
        }
        return alertDefinitionBoundary;
    }
}
