package cloudcomputing2024.smarthouse.trafficmonitorservice.messages;

import cloudcomputing2024.smarthouse.trafficmonitorservice.enums.AlertType;

import java.util.Dictionary;

public record AlertDefinition(AlertType type, String id, Dictionary<String, String> parameters) {
}