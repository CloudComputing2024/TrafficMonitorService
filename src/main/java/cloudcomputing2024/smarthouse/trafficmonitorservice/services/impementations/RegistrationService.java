package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities.AlertDefinitionEntity;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities.ServiceTopicDefinitionEntity;
import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.ServiceTopicDefinitionRepository;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.AlertDefinitionBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.MessageBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.ServiceTopicDefinitionBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.IRegistrationService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RegistrationService implements IRegistrationService {
    private final ServiceTopicDefinitionRepository registrationRepository;

    public RegistrationService(ServiceTopicDefinitionRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public Flux<ServiceTopicDefinitionBoundary> registerServices(MessageBoundary message, List<AlertDefinitionBoundary> alertDefinitions) {
        var maxRequestsPerMinute = (Integer) message.getMessageDetails().get("maxRequestsPerMinute");
        var maxRequestSizeIntBytes = (Integer) message.getMessageDetails().get("maxRequestSizeIntBytes");

        var alertEntities = alertDefinitions
                .stream()
                .map(AlertDefinitionEntity::new)
                .toList();

        var serviceEntities = Arrays
                .stream(message.getExternalReferences())
                .map(reference -> new ServiceTopicDefinitionEntity(reference.getService(), maxRequestsPerMinute, maxRequestSizeIntBytes, alertEntities))
                .toList();

        return registrationRepository
                .insert(serviceEntities)
                .map(this::mapToBoundary);
    }

    @Override
    public Flux<ServiceTopicDefinitionBoundary> getAllRegistrations() {
        return this.registrationRepository
                .findAll()
                .map(this::mapToBoundary);
    }

    @Override
    public Mono<Void> deleteAll() {
        return this.registrationRepository
                .deleteAll();
    }

    private ServiceTopicDefinitionBoundary mapToBoundary(ServiceTopicDefinitionEntity entity) {
        List<AlertDefinitionBoundary> alertDefinitionBoundaries = Collections.emptyList();
        var alerts = entity.alertDefinitions();

        if (alerts != null) {
            alertDefinitionBoundaries = alerts
                    .stream()
                    .map(alertEntity -> new AlertDefinitionBoundary(alertEntity.notificationType(), alertEntity.parameters()))
                    .toList();

        }

        return new ServiceTopicDefinitionBoundary(entity.serviceName(),
                entity.maxRequestsPerMinute(),
                entity.maxRequestSizeIntBytes(),
                alertDefinitionBoundaries);
    }

}
