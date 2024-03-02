package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.Kafka;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class KafkaInit implements CommandLineRunner {
    private KafkaConsumerService kafkaConsumerService;

    public KafkaInit(KafkaConsumerService kafkaConsumerService) {
        this.kafkaConsumerService = kafkaConsumerService;
    }

    @Override
    public void run(String... args) throws Exception {
        kafkaConsumerService.addTopic("SprintTopic");
    }
}
