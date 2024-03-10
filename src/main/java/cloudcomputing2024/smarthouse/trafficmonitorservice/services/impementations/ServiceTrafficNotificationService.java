package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.TrafficExceededAlert;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.TrafficExceededCause;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities.AlertDefinitionEntity;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities.ServiceTopicDefinitionEntity;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.IServiceTrafficNotificationService;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.INotificationStrategyProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServiceTrafficNotificationService implements IServiceTrafficNotificationService {
    private final Logger logger = LoggerFactory.getLogger(ServiceTrafficNotificationService.class);
    private final INotificationStrategyProvider notificationStrategyProvider;

    public ServiceTrafficNotificationService(INotificationStrategyProvider notificationStrategyProvider) {
        this.notificationStrategyProvider = notificationStrategyProvider;
    }

    @Override
    public Mono<Void> sendTrafficExceededNotifications(ServiceTopicDefinitionEntity serviceTopicDefinition, TrafficExceededCause cause) {
        logger.warn("Traffic exceeded for service '{}' because of {}", serviceTopicDefinition.serviceName(), cause.name().toLowerCase());
        var message = new TrafficExceededAlert(serviceTopicDefinition.serviceName(), cause);

        if (serviceTopicDefinition.alertDefinitions() == null) {
            return Mono.empty();
        }

        return Flux.fromIterable(serviceTopicDefinition.alertDefinitions())
                .flatMap(alert -> sendAlertNotification(alert, message))
                .then();
    }

    private Mono<Void> sendAlertNotification(AlertDefinitionEntity alertDefinition, TrafficExceededAlert alertMessage) {
        var notification = notificationStrategyProvider.getStrategy(alertDefinition.notificationType());
        return notification.notify(alertDefinition, alertMessage);
    }
}
