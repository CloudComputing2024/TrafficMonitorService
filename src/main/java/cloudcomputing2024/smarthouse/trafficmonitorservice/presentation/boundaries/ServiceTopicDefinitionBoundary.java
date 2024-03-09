package cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries;

import com.mongodb.lang.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

public record ServiceTopicDefinitionBoundary(String serviceName, int maxRequestsPerMinute, int maxRequestSizeIntBytes,
                                             List<AlertDefinitionBoundary> alertDefinitionBoundaries) {
}