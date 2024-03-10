package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities.ServiceTopicDefinitionEntity;
import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.ServiceTopicDefinitionRepository;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.MessageBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.IMonitoredMessagesFilterer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class MonitoredMessagesFilterer implements IMonitoredMessagesFilterer {
    private final ServiceTopicDefinitionRepository serviceTopicDefinitionRepository;

    public MonitoredMessagesFilterer(ServiceTopicDefinitionRepository serviceTopicDefinitionRepository) {
        this.serviceTopicDefinitionRepository = serviceTopicDefinitionRepository;
    }

    @Override
    public Flux<ServiceTopicDefinitionEntity> getMonitoredServices(MessageBoundary messageBoundary) {
        return Flux.fromArray(messageBoundary.getExternalReferences())
                .flatMap(reference -> serviceTopicDefinitionRepository.findById(reference.getService()));
    }
}
