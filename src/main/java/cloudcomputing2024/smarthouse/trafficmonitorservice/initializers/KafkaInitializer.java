package cloudcomputing2024.smarthouse.trafficmonitorservice.initializers;

import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.Kafka.ServiceTopicDefinitionSubscriber;
import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.ServiceTopicDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class KafkaInitializer implements CommandLineRunner {
    private final ServiceTopicDefinitionSubscriber serviceTopicDefinitionSubscriber;

    public KafkaInitializer(ServiceTopicDefinitionSubscriber serviceTopicDefinitionSubscriber) {
        this.serviceTopicDefinitionSubscriber = serviceTopicDefinitionSubscriber;
    }

    @Override
    public void run(String... args) {
        var definition = new ServiceTopicDefinition("Service", "SprintService", 1000, 1000, null);
        //serviceTopicDefinitionSubscriber.subscribe(definition);
    }
}
