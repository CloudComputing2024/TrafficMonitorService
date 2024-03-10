package cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.NotificationType;

import java.util.Map;

public record AlertDefinitionBoundary(NotificationType notificationType, Map<String, Object> parameters) {
}