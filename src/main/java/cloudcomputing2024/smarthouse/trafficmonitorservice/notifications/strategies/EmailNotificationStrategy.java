package cloudcomputing2024.smarthouse.trafficmonitorservice.notifications.strategies;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.AlertDefinition;
import cloudcomputing2024.smarthouse.trafficmonitorservice.alerts.TrafficExceededAlert;

public class EmailNotificationStrategy implements NotificationStrategy {
    @Override
    public void Notify(AlertDefinition alertDefinition, TrafficExceededAlert alert) {
        System.out.printf("EmailNotificationStrategy: Notify to %s by %s because of %s exceeded.\n", alertDefinition.serviceName(), alertDefinition.notificationType().name(), alert.cause());
    }
}
