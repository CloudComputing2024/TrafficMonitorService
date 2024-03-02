package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.Kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProducerConsumerComponent {
    @KafkaListener(topics = "test",groupId = "SmartHouse")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
    }
}
