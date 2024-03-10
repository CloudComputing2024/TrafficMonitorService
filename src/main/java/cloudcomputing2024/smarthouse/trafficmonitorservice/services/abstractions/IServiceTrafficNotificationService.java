package cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.TrafficExceededCause;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities.ServiceTopicDefinitionEntity;
import reactor.core.publisher.Mono;

public interface IServiceTrafficNotificationService {
    Mono<Void> sendTrafficExceededNotifications(ServiceTopicDefinitionEntity serviceTopicDefinition, TrafficExceededCause cause);
}
