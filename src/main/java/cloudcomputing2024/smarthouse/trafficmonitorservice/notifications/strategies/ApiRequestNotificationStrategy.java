package cloudcomputing2024.smarthouse.trafficmonitorservice.notifications.strategies;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.AlertDefinition;
import cloudcomputing2024.smarthouse.trafficmonitorservice.alerts.TrafficExceededAlert;
import org.springframework.stereotype.Service;

@Service
public class ApiRequestNotificationStrategy implements NotificationStrategy {
    @Override
    public void Notify(AlertDefinition alertDefinition, TrafficExceededAlert alert) {
        throw new UnsupportedOperationException("ApiRequestNotificationStrategy is not implemented yet");
    }
}
