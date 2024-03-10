package cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions;

import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.AlertDefinitionBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.MessageBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.ServiceTopicDefinitionBoundary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IRegistrationService {
    Flux<ServiceTopicDefinitionBoundary> registerServices(MessageBoundary message, List<AlertDefinitionBoundary> alertDefinitions);
    
    Flux<ServiceTopicDefinitionBoundary> getAllRegistrations();

    Mono<Void> deleteAll();
}
