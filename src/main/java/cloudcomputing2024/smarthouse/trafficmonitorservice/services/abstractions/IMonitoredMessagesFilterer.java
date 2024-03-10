package cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities.ServiceTopicDefinitionEntity;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.MessageBoundary;
import reactor.core.publisher.Flux;

public interface IMonitoredMessagesFilterer {
    Flux<ServiceTopicDefinitionEntity> getMonitoredServices(MessageBoundary messageBoundary);
}
