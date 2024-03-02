package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.ServiceTopicDefinition;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "ServiceDefinitionTopic", groupId = "my-group")
    public void listen(ServiceTopicDefinition message) {
        System.out.println("Received message: " + message);
        // Process the received message as needed
    }
}
