package cloudcomputing2024.smarthouse.trafficmonitorservice.notificationService;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.AlertDefinition;

public interface INotificationService {
    void SendNotification(AlertDefinition alertDefinition, String jsonContent);
}
