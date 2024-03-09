package cloudcomputing2024.smarthouse.trafficmonitorservice.notifications.strategies;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.AlertDefinition;
import cloudcomputing2024.smarthouse.trafficmonitorservice.alerts.TrafficExceededAlert;
import reactor.core.publisher.Mono;

public interface NotificationStrategy {
    Mono<Void> Notify(AlertDefinition alertDefinition, TrafficExceededAlert alert);
}
