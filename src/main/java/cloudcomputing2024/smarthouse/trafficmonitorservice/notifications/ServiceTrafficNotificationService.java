package cloudcomputing2024.smarthouse.trafficmonitorservice.notifications;

import cloudcomputing2024.smarthouse.trafficmonitorservice.alerts.TrafficExceededCause;
import cloudcomputing2024.smarthouse.trafficmonitorservice.alerts.TrafficExceededAlert;
import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.ServiceTopicDefinition;
import cloudcomputing2024.smarthouse.trafficmonitorservice.notifications.strategies.INotificationStrategyProvider;
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
    public void sendTrafficExceededNotifications(ServiceTopicDefinition definition, TrafficExceededCause cause) {
        logger.warn("Traffic exceeded for topic '{}' in service '{}' in message {}", definition.serviceName(), cause.name().toLowerCase());
        var message = new TrafficExceededAlert(definition.serviceName(), cause);

        if (definition.alertDefinitions() == null) {
            return;
        }

        for (var alert : definition.alertDefinitions()) {
            var notification = notificationStrategyProvider.getStrategy(alert.notificationType());
            notification.Notify(alert, message);
        }
    }
}
