package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.MessageBoundary;
import org.springframework.stereotype.Component;

@Component
public class ServiceTopicDefinitionFilterer implements cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.IServiceTopicDefinitionFilterer {
    @Override
    public boolean IsMessageIsRegistrationMessage(MessageBoundary messageBoundary) {
        return false;
    }
}
