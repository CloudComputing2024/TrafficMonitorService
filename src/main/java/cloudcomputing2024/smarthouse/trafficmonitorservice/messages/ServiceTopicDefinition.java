package cloudcomputing2024.smarthouse.trafficmonitorservice.messages;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
public record ServiceTopicDefinition(@Id String serviceName,
                                     String topic,
                                     int maxRequestsPerMinute,
                                     int maxRequestSizeIntBytes,
                                     List<AlertDefinition> alertDefinitions) {
}