package cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions;

import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.MessageBoundary;

public interface IKafkaMessageHandlerWorkflow {
    void HandleMessage(MessageBoundary messageBoundary);
}
