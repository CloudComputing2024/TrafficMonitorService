package cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.TrafficExceededCause;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities.ServiceTopicDefinitionEntity;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.ServiceTopicDefinitionBoundary;
import reactor.core.publisher.Flux;

public interface IServiceTrafficNotificationService {
    Flux<Void> sendTrafficExceededNotifications(String serviceName, TrafficExceededCause cause);
}
