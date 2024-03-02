package cloudcomputing2024.smarthouse.trafficmonitorservice.notificationService;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.AlertDefinition;

public class NotificationService implements  INotificationService
{
    @Override
    public void SendNotification(AlertDefinition alertDefinition, String jsonContent) {
        NotificationType.valueOf(alertDefinition.id())
                .getStrategy()
                .Notify(alertDefinition, jsonContent);
    }
}
