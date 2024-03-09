package cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities;

import com.mongodb.lang.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public record ServiceTopicDefinitionEntity(@Id String serviceName, int maxRequestsPerMinute, int maxRequestSizeIntBytes,
                                           @Nullable List<AlertDefinitionEntity> alertDefinitions) {
}