package cloudcomputing2024.smarthouse.trafficmonitorservice.notificationService;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.AlertDefinition;

public interface INotificationStrategy {
    void Notify(AlertDefinition alertDefinition,String jsonContent);
}
