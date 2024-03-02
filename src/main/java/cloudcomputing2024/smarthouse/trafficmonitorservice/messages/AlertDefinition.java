package cloudcomputing2024.smarthouse.trafficmonitorservice.messages;

import cloudcomputing2024.smarthouse.trafficmonitorservice.notificationService.NotificationType;

import java.util.Map;

public record AlertDefinition(String id,String serviceName,NotificationType notificationType, Map<String, String> parameters) {
}