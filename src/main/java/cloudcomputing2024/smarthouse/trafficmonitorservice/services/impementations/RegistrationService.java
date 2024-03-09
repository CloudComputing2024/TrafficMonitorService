package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities.ServiceTopicDefinitionEntity;
import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.ServiceTopicDefinitionRepository;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.AlertDefinitionBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.ExternalReferenceBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.MessageBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.ServiceTopicDefinitionBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.IRegistrationService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
public class RegistrationService implements IRegistrationService {
    private final ServiceTopicDefinitionRepository registrationRepository;

    public RegistrationService(ServiceTopicDefinitionRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public Mono<ServiceTopicDefinitionBoundary> registerService(MessageBoundary message) {
        if (message == null){
            return Mono.empty();
        }
        ExternalReferenceBoundary[] externalReferences = message.getExternalReferences();
        String serviceName = "";
        for (ExternalReferenceBoundary externalReference : externalReferences) {
            if ("serviceName".equals(externalReference.getService())) {
                serviceName = externalReference.getExternalServiceId();
                break;
            }
        }
        if (serviceName.isEmpty()){
            return Mono.empty();
        }
        //TODO handle alertDefinitions, missing in boundary
        ServiceTopicDefinitionEntity serviceTopicDefinition = new ServiceTopicDefinitionEntity(serviceName,
                (Integer) message.getMessageDetails().get("maxRequestsPerMinute"),
                (Integer) message.getMessageDetails().get("maxRequestSizeIntBytes"),
                null
                );

        Mono<ServiceTopicDefinitionEntity> insertedEntityMono = this.registrationRepository.insert(serviceTopicDefinition);

        return insertedEntityMono.map(this::mapToBoundary);
    }

    @Override
    public Flux<ServiceTopicDefinitionBoundary> getAllRegistrations(MessageBoundary message) {
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
        var alerts = entity.alertDefinitionBoundaries();
        if (alerts != null){
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
