package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities.AlertDefinitionEntity;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.TrafficExceededAlert;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.NotificationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class KafkaTopicNotificationStrategy implements NotificationStrategy {
    private final Logger logger = LoggerFactory.getLogger(KafkaTopicNotificationStrategy.class);
    private static final String TopicParameter = "topic";
    private static final String MessageParameter = "message";
    private final KafkaTemplate<String, String> publisher;

    public KafkaTopicNotificationStrategy(KafkaTemplate<String, String> publisher) {
        this.publisher = publisher;
    }

    @Override
    public Mono<Void> notify(AlertDefinitionEntity definition, TrafficExceededAlert alert) {
        var topic = definition.parameters().get(TopicParameter);

        if (topic == null) {
            throw new IllegalArgumentException("Topic parameter is missing");
        }

        return Mono.fromRunnable(() -> notifyInternal(definition, alert));
    }

    private void notifyInternal(AlertDefinitionEntity definition, TrafficExceededAlert alert) {
        try {
            var topic = definition.parameters().get(TopicParameter).toString();
            var message = definition.parameters().get(MessageParameter).toString();
            
            logger.info("Sending notification to topic: {} because of alert: {}", topic, alert);
            publisher.send(topic, message);
        } catch (Exception e) {
            logger.error("Failed to send notification", e);
        }
    }
}
