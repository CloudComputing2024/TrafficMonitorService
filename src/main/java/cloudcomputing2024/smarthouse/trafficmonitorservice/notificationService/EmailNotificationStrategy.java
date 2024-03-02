package cloudcomputing2024.smarthouse.trafficmonitorservice.notificationService;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.AlertDefinition;

public class EmailNotificationStrategy implements INotificationStrategy {
    @Override
    public void Notify(AlertDefinition alertDefinition, String jsonContent) {
        System.out.println("EmailNotificationStrategy: Notify to " +
                alertDefinition.serviceName() +
                "through" +
                alertDefinition.notificationType());
    }
}
