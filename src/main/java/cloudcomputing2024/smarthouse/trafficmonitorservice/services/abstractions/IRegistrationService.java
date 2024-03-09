package cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions;

import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.MessageBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.ServiceTopicDefinitionBoundary;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IRegistrationService {

    Mono<ServiceTopicDefinitionBoundary> registerService(MessageBoundary message);

    Flux<ServiceTopicDefinitionBoundary> getAllRegistrations(MessageBoundary message);

    Mono<Void> deleteAll();
}
