package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.Kafka;

import cloudcomputing2024.smarthouse.trafficmonitorservice.alerts.TrafficExceededCause;
import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.ServiceTopicMessageCounterService;
import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.ServiceTopicDefinition;
import cloudcomputing2024.smarthouse.trafficmonitorservice.notifications.IServiceTrafficNotificationService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceTopicDefinitionKafkaSubscriber implements ServiceTopicDefinitionSubscriber {
    private final Logger logger = LoggerFactory.getLogger(ServiceTopicDefinitionKafkaSubscriber.class);
    private final IServiceTrafficNotificationService notificationService;
    private final ServiceTopicMessageCounterService serviceTopicMessageCounterService;
    private final ConcurrentKafkaListenerContainerFactory<String, String> consumersFactory;
    private final Map<String, ConcurrentMessageListenerContainer<String, String>> containers = new HashMap<>();

    public ServiceTopicDefinitionKafkaSubscriber(IServiceTrafficNotificationService notificationService, ConcurrentKafkaListenerContainerFactory<String, String> consumersFactory, ServiceTopicMessageCounterService serviceTopicMessageCounterService) {
        this.consumersFactory = consumersFactory;
        this.notificationService = notificationService;
        this.serviceTopicMessageCounterService = serviceTopicMessageCounterService;
    }

    public void subscribe(ServiceTopicDefinition definition) {
        var container = consumersFactory.createContainer(definition.topic());
        container.setupMessageListener((MessageListener<String, String>) record -> HandleServiceTopicMessage(definition, record));

        container.start();
        containers.put(definition.topic(), container);
    }

    public void unsubscribe(ServiceTopicDefinition definition) {
        var container = containers.get(definition.topic());

        if (container == null) {
            return;
        }

        container.stop();
        containers.remove(definition.topic());
    }

    private void HandleServiceTopicMessage(ServiceTopicDefinition definition, ConsumerRecord<String, String> record) {
        logger.info("Got message for topic '{}' in service '{}'", definition.topic(), definition.serviceName());
        
        Mono.just(definition)
                .filter(d -> record.serializedValueSize() > definition.maxRequestSizeIntBytes())
                .map(d -> notificationService.sendTrafficExceededNotifications(definition, TrafficExceededCause.Size))
                .then(serviceTopicMessageCounterService.incrementCounter(definition.serviceName(), definition.topic()))
                .block();
    }
}
