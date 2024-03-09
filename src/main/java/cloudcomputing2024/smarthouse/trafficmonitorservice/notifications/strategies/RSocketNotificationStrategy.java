package cloudcomputing2024.smarthouse.trafficmonitorservice.notifications.strategies;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.AlertDefinition;
import cloudcomputing2024.smarthouse.trafficmonitorservice.alerts.TrafficExceededAlert;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RSocketNotificationStrategy implements NotificationStrategy {
    @Override
    public Mono<Void> Notify(AlertDefinition alertDefinition, TrafficExceededAlert alert) {
        throw new UnsupportedOperationException("RSocketNotificationStrategy is not implemented yet");
    }
}
