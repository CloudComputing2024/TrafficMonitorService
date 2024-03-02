package cloudcomputing2024.smarthouse.trafficmonitorservice.messages;

import org.springframework.data.annotation.Id;

import java.util.List;

public record ServiceTopicDefinition(@Id String serviceName,
                                     String topic,
                                     int maxRequestsPerMinute,
                                     int maxRequestSizeIntBytes,
                                     List<AlertDefinition> alertDefinitions) {
}