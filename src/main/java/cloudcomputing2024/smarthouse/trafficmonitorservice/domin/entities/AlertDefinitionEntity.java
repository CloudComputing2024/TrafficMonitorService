package cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.NotificationType;

import java.util.Map;

public record AlertDefinitionEntity(NotificationType notificationType, Map<String, String> parameters) {
}