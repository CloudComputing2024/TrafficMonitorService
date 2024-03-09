package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.MessageBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.IMessageSizeValidator;
import org.springframework.stereotype.Component;

@Component
public class MessageSizeValidator implements IMessageSizeValidator {
    @Override
    public boolean IsSizeValid(MessageBoundary messageBoundary) {
        return false;
    }
}
