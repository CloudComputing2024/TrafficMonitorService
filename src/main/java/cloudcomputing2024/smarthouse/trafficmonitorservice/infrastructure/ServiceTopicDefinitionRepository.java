package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.ServiceTopicDefinition;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//Make reactive
@Repository
public interface ServiceTopicDefinitionRepository extends ReactiveMongoRepository<ServiceTopicDefinition, String> {
    Flux<ServiceTopicDefinition> findByServiceName(String firstName);

    Mono<Void> deleteByServiceNameAndTopic(String serviceName, String topic);
}

