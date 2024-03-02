package cloudcomputing2024.smarthouse.trafficmonitorservice.notifications;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.AlertDefinition;
import cloudcomputing2024.smarthouse.trafficmonitorservice.alerts.TrafficExceededAlert;

public interface INotificationService {
    void sendNotification(AlertDefinition alertDefinition, TrafficExceededAlert alert);
}
