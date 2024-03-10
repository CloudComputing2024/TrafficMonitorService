package cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.NotificationType;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.AlertDefinitionBoundary;

import java.util.Map;

public record AlertDefinitionEntity(NotificationType notificationType, Map<String, Object> parameters) {
    public AlertDefinitionEntity(AlertDefinitionBoundary boundary) {
        this(boundary.notificationType(), boundary.parameters());
    }
}