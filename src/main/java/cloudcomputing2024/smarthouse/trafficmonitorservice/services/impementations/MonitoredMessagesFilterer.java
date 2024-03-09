package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.MessageBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.IMonitoredMessagesFilterer;
import org.springframework.stereotype.Component;

@Component
public class MonitoredMessagesFilterer implements IMonitoredMessagesFilterer {
    @Override
    public boolean IsMessageInMonitoring(MessageBoundary messageBoundary) {
        return false;
    }
}
