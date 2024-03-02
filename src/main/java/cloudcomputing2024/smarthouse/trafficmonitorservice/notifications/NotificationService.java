package cloudcomputing2024.smarthouse.trafficmonitorservice.notifications;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.AlertDefinition;
import cloudcomputing2024.smarthouse.trafficmonitorservice.alerts.TrafficExceededAlert;

public class NotificationService implements INotificationService {
    @Override
    public void sendNotification(AlertDefinition alertDefinition, TrafficExceededAlert alert) {
        alertDefinition.notificationType().getStrategy().Notify(alertDefinition, alert);
    }
}
