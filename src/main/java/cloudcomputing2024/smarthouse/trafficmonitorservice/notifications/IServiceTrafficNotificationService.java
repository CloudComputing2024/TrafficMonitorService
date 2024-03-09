package cloudcomputing2024.smarthouse.trafficmonitorservice.notifications;

import cloudcomputing2024.smarthouse.trafficmonitorservice.alerts.TrafficExceededCause;
import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.ServiceTopicDefinition;
import reactor.core.publisher.Flux;

public interface IServiceTrafficNotificationService {
    Flux<Void> sendTrafficExceededNotifications(ServiceTopicDefinition definition, TrafficExceededCause cause);
}
