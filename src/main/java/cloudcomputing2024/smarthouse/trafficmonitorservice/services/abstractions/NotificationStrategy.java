package cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities.AlertDefinitionEntity;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.AlertDefinitionBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.TrafficExceededAlert;
import reactor.core.publisher.Mono;

public interface NotificationStrategy {
    public Mono<Void> Notify(AlertDefinitionEntity alertDefinition, TrafficExceededAlert alert);
}
