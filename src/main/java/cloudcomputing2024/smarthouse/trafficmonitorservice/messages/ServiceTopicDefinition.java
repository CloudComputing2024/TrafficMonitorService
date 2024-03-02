package cloudcomputing2024.smarthouse.trafficmonitorservice.messages;

import java.util.List;

public record ServiceTopicDefinition(String serviceName,
                                     String topic,
                                     int maxRequestsPerMinute,
                                     int maxRequestSizeIntBytes,
                                     List<AlertDefinition> alertDefinitions) {
}