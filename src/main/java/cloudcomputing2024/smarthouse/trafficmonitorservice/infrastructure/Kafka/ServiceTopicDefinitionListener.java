package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.Kafka;

import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.ServiceTopicDefinitionRepository;
import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.ServiceTopicDefinition;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ServiceTopicDefinitionListener {
    private static final String ListenerGroupId = "SmartHouse";
    private static final String ListenerTopic = "service-topic-definition";

    private final Logger logger = LoggerFactory.getLogger(ServiceTopicDefinitionListener.class);
    private final ServiceTopicDefinitionSubscriber serviceTopicDefinitionSubscriber;
    private final ServiceTopicDefinitionRepository serviceTopicDefinitionRepository;
    private final ObjectMapper objectMapper;

    public ServiceTopicDefinitionListener(ServiceTopicDefinitionSubscriber serviceTopicDefinitionSubscriber, ServiceTopicDefinitionRepository serviceTopicDefinitionRepository, ObjectMapper objectMapper) {
        this.serviceTopicDefinitionSubscriber = serviceTopicDefinitionSubscriber;
        this.serviceTopicDefinitionRepository = serviceTopicDefinitionRepository;
        this.objectMapper = objectMapper;
    }


    @KafkaListener(topics = ListenerTopic, groupId = ListenerGroupId)
    public void handleTopicDefinition(String message) {
        logger.info("Received service topic definition request: {}", message);

        try {
            var definition = objectMapper.readValue(message, ServiceTopicDefinition.class);
            serviceTopicDefinitionSubscriber.subscribe(definition);
            serviceTopicDefinitionRepository.save(definition);
        } catch (Exception e) {
            logger.error("Failed to handle service topic definition: {}", message);
        }
    }
}
