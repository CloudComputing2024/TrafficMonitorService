package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.AlertDefinitionBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.TrafficExceededAlert;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.NotificationStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaTopicNotificationStrategy implements NotificationStrategy {
    private static final String TopicParameter = "topic";
    private final Logger logger = LoggerFactory.getLogger(KafkaTopicNotificationStrategy.class);
    private final KafkaTemplate<String, String> publisher;
    private final ObjectMapper objectMapper;

    public KafkaTopicNotificationStrategy(KafkaTemplate<String, String> publisher, ObjectMapper objectMapper) {
        this.publisher = publisher;
        this.objectMapper = objectMapper;
    }

    @Override
    public void Notify(AlertDefinitionBoundary alertDefinitionBoundary, TrafficExceededAlert alert) {
        var topic = alertDefinitionBoundary.parameters().get(TopicParameter);

        if (topic == null) {
            throw new IllegalArgumentException("Topic parameter is missing");
        }
        
        try {
            var message = objectMapper.writeValueAsString(alert);
            publisher.send(topic, message);
        } catch (Exception e) {
            logger.error("Failed to send notification to topic: {}", topic);
        }
    }
}
