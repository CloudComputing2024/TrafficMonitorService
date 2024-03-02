package cloudcomputing2024.smarthouse.trafficmonitorservice.notificationService;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.AlertDefinition;

public class KafkaTopicNotificationStrategy implements INotificationStrategy {
    @Override
    public void Notify(AlertDefinition alertDefinition, String jsonContent) {
        System.out.println("KafkaTopicNotificationStrategy: Notify to " +
                alertDefinition.serviceName() +
                "through"
                + alertDefinition.notificationType());

    }
}
