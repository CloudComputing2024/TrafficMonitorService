package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.Kafka;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.ServiceTopicDefinition;

public interface ServiceTopicDefinitionSubscriber {
    void subscribe(ServiceTopicDefinition definition);

    void unsubscribe(ServiceTopicDefinition definition);
}
