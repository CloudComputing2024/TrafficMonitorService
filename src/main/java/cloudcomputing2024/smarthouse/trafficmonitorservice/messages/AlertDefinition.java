package cloudcomputing2024.smarthouse.trafficmonitorservice.messages;

import cloudcomputing2024.smarthouse.trafficmonitorservice.notifications.NotificationType;
import org.springframework.data.annotation.Id;

import java.util.Map;

public record AlertDefinition(NotificationType notificationType, Map<String, String> parameters) {
}