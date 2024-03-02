package cloudcomputing2024.smarthouse.trafficmonitorservice.notifications.strategies;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.AlertDefinition;
import cloudcomputing2024.smarthouse.trafficmonitorservice.alerts.TrafficExceededAlert;

public class ApiRequestNotificationStrategy implements NotificationStrategy {
    @Override
    public void Notify(AlertDefinition alertDefinition, TrafficExceededAlert alert) {
        System.out.printf("ApiRequestNotificationStrategy: Notify to %s by %s because of %s exceeded.\n",  alertDefinition.notificationType().name(), alert.cause());
    }
}
