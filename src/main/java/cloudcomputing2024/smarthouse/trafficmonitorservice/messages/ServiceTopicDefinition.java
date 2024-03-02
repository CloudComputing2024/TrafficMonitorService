package cloudcomputing2024.smarthouse.trafficmonitorservice.messages;

public record ServiceTopicDefinition(String serviceName, int maxRequestsPerMinute, int maxRequestSizeIntBytes) {
}