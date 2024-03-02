package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumerService {

    @Autowired
    private KafkaListenerEndpointRegistry registry;

    @Autowired
    private ConcurrentKafkaListenerContainerFactory<String, String> factory;
    private List<ConcurrentMessageListenerContainer<String, String>> containers = new ArrayList<>();

    public void addTopic(String topic) {
        ConcurrentMessageListenerContainer<String, String> container = factory.createContainer(topic);
        container.setupMessageListener((MessageListener<String, String>) record -> {
            System.out.println("Received Message: " + record.value() + " from topic: " + record.topic());
        });
        container.start();
        containers.add(container);
    }
}
