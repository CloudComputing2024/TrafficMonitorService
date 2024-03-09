package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.TrafficExceededCause;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.IServiceTrafficNotificationService;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.INotificationStrategyProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ServiceTrafficNotificationService implements IServiceTrafficNotificationService {
    private final Logger logger = LoggerFactory.getLogger(ServiceTrafficNotificationService.class);
    private final INotificationStrategyProvider notificationStrategyProvider;

    public ServiceTrafficNotificationService(INotificationStrategyProvider notificationStrategyProvider) {
        this.notificationStrategyProvider = notificationStrategyProvider;
    }

    @Override
    public Flux<Void> sendTrafficExceededNotifications(String serviceName, TrafficExceededCause cause)
    {
//        logger.warn("Traffic exceeded for topic '{}' in service '{}' in message {}", definition.topic(), definition.serviceName(), cause.name().toLowerCase());
//        var message = new TrafficExceededAlert(definition.serviceName(), definition.topic(), cause);
//
//        if (definition.alertDefinitions() == null) {
//            return Flux.empty();
//        }
//
//        return Flux.fromIterable(definition.alertDefinitions()).flatMap(alert -> {
//            var notification = notificationStrategyProvider.getStrategy(alert.notificationType());
//            return notification.Notify(alert, message);
//        });
        return Flux.empty();
    }
}
