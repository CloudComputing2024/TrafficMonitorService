package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.ServiceTopicDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, ServiceTopicDefinition> kafkaTemplate;

    public void send(ServiceTopicDefinition message) {
        kafkaTemplate.send("ServiceDefinitionTopic", message.serviceName(), message);
    }
}
