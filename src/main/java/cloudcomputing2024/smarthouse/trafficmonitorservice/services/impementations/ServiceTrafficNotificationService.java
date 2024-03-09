package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.TrafficExceededAlert;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.TrafficExceededCause;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities.AlertDefinitionEntity;
import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.ServiceTopicDefinitionRepository;
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
    private final ServiceTopicDefinitionRepository serviceTopicDefinitionRepository;
    public ServiceTrafficNotificationService(INotificationStrategyProvider notificationStrategyProvider, ServiceTopicDefinitionRepository serviceTopicDefinitionRepository) {
        this.notificationStrategyProvider = notificationStrategyProvider;
        this.serviceTopicDefinitionRepository = serviceTopicDefinitionRepository;
    }

    @Override
    public Flux<Void> sendTrafficExceededNotifications(String serviceName, TrafficExceededCause cause) {
        logger.warn("Traffic exceeded for service '{}' because of {}", serviceName, cause.name().toLowerCase());
        var serviceTopicDefinition = serviceTopicDefinitionRepository.findByServiceName(serviceName);

        return serviceTopicDefinition.flatMap(topicDefinition -> {
            var message = new TrafficExceededAlert(topicDefinition.serviceName(), cause);

            if (topicDefinition.alertDefinitions() == null) {
                return Flux.empty();
            }

            return Flux
                    .fromIterable(topicDefinition.alertDefinitions())
                    .flatMap(alert -> sendAlertNotification(alert, message));

        });
    }

    private Mono<Void> sendAlertNotification(AlertDefinitionEntity alertDefinition, TrafficExceededAlert alertMessage) {
        var notification = notificationStrategyProvider.getStrategy(alertDefinition.notificationType());
        return notification.Notify(alertDefinition, alertMessage);
    }
}
