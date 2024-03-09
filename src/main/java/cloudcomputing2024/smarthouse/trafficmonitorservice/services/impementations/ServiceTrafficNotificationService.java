package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.TrafficExceededCause;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.TrafficExceededAlert;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities.ServiceTopicDefinitionEntity;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.ServiceTopicDefinitionBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.IServiceTrafficNotificationService;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.INotificationStrategyProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ServiceTrafficNotificationService implements IServiceTrafficNotificationService {
    private final Logger logger = LoggerFactory.getLogger(ServiceTrafficNotificationService.class);
    private final INotificationStrategyProvider notificationStrategyProvider;

    public ServiceTrafficNotificationService(INotificationStrategyProvider notificationStrategyProvider) {
        this.notificationStrategyProvider = notificationStrategyProvider;
    }

    @Override
    public void sendTrafficExceededNotifications(String serviceName, TrafficExceededCause cause) {
//        logger.warn("Traffic exceeded for topic '{}' in service '{}' in message {}", definition.serviceName(), cause.name().toLowerCase());
//        var message = new TrafficExceededAlert(definition.serviceName(), cause);
//
//        if (definition.alertDefinitionBoundaries() == null) {
//            return;
//        }
//
//        for (var alert : definition.alertDefinitionBoundaries()) {
//            var notification = notificationStrategyProvider.getStrategy(alert.notificationType());
//            notification.Notify(alert, message);
//        }
    }
}
